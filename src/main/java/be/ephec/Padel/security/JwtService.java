package be.ephec.padel.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    // La cle secrete qui sert a signer les jetons (doit rester privee)
    private final SecretKey cleSecrete = Keys.hmacShaKeyFor(
        "MaCleSecreteSuperLonguePourLeProjetPadelEphec2026!!".getBytes()
    );

    // Duree de validite du jeton : 24 heures (en millisecondes)
    private final long dureeValidite = 1000 * 60 * 60 * 24;

    // FABRIQUER un jeton pour un matricule donne
    public String genererToken(String matricule) {
        return Jwts.builder()
                .subject(matricule)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + dureeValidite))
                .signWith(cleSecrete)
                .compact();
    }

    // LIRE le matricule contenu dans un jeton
    public String extraireMatricule(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(cleSecrete)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }

    // VERIFIER qu'un jeton est valide (signature + pas expire)
    public boolean tokenValide(String token) {
        try {
            Jwts.parser()
                .verifyWith(cleSecrete)
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

/**
 * Ce que fait chaque partie (le pourquoi)

cleSecrete → c'est la "signature secrète" de la machine à bracelets.
 Elle sert à signer chaque jeton pour qu'on ne puisse pas le falsifier. 
 Personne d'autre ne connaît cette clé, donc personne ne peut fabriquer de faux bracelets.
  (Dans un vrai projet pro, on ne l'écrit pas en dur dans le code, mais pour ton projet c'est acceptable.)
dureeValidite → le bracelet expire après 24h. Passé ce délai, il faut se reconnecter. C'est une sécurité normale.
genererToken(matricule) → fabrique un bracelet pour un membre (identifié par son matricule).
 Le jeton contient : à qui il appartient (subject), quand il a été créé (issuedAt),
  quand il expire (expiration), et la signature secrète (signWith).
extraireMatricule(token) → lit le bracelet et retrouve à qui il appartient (le matricule).
tokenValide(token) → vérifie qu'un bracelet est authentique (bonne signature) et pas expiré.
 Le try/catch : si le jeton est falsifié ou expiré, la lecture échoue → on renvoie false.

Ne t'inquiète pas si la syntaxe Jwts.builder()... te paraît dense : c'est du code spécifique à la bibliothèque jjwt,
 tu n'as pas à le mémoriser. L'important est de comprendre les trois actions : fabriquer, lire, vérifier.
 */