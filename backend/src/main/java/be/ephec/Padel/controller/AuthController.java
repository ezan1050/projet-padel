package be.ephec.padel.controller;

import be.ephec.padel.model.Membre;
import be.ephec.padel.repository.MembreRepository;
import be.ephec.padel.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final MembreRepository membreRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(MembreRepository membreRepository, JwtService jwtService, PasswordEncoder passwordEncoder) 
    {
        this.membreRepository = membreRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    // POST /api/auth/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> identifiants) {
        String matricule = identifiants.get("matricule");
        String motDePasse = identifiants.get("motDePasse");

        Optional<Membre> membreOpt = membreRepository.findByMatricule(matricule);

        // Le matricule n'existe pas
        if (membreOpt.isEmpty()) {
            return ResponseEntity.status(401).body("Matricule ou mot de passe incorrect");
        }

        Membre membre = membreOpt.get();

        // Le mot de passe ne correspond pas
       if (!passwordEncoder.matches(motDePasse, membre.getMotDePasse()))  {
            return ResponseEntity.status(401).body("Matricule ou mot de passe incorrect");
        }
        /**
         * Le pourquoi : passwordEncoder.matches(motDePasse, membre.getMotDePasse()) prend le mot de passe tapé (en clair) et l'empreinte stockée, 
         * refait l'empreinte du mot de passe tapé, et compare les deux. Renvoie true si ça correspond. 
         * C'est la vérification "sans jamais lire le mot de passe stocké".
         */

        // Tout est bon : on fabrique et renvoie le jeton
        String token = jwtService.genererToken(membre.getMatricule());
       return ResponseEntity.ok(Map.of(
            "token", token,
            "role", membre.getRole().toString(),
            "nom", membre.getPrenom() + " " + membre.getNom()
        ));
    }
}

/**
 * Ce que fait ce code (le pourquoi)

@RequestBody Map<String, String> identifiants → on reçoit un petit JSON {"matricule": "...", "motDePasse": "..."}. La Map est une façon simple de récupérer ces deux valeurs sans créer une classe dédiée.
On cherche le membre par matricule. S'il n'existe pas (isEmpty()) → on renvoie une erreur 401 (non autorisé). Note qu'on dit "matricule OU mot de passe incorrect" sans préciser lequel — c'est une bonne pratique de sécurité (ne pas aider un attaquant à deviner).
On compare le mot de passe. Le !...equals(...) : si le mot de passe fourni ne correspond pas à celui en base → erreur 401. (Pour l'instant on compare en clair ; on ajoutera le chiffrement BCrypt juste après que ça marche.)
Si tout est bon → on appelle jwtService.genererToken(...) (la machine à bracelets !) et on renvoie le jeton + le rôle du membre. Le frontend gardera ce jeton pour ses prochaines requêtes.
ResponseEntity → c'est une façon de renvoyer une réponse en contrôlant le code HTTP (200 si ok, 401 si refusé). Plus précis que de renvoyer juste un objet.
 */