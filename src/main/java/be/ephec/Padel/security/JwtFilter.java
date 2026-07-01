package be.ephec.padel.security;

import be.ephec.padel.model.Membre;
import be.ephec.padel.repository.MembreRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter
 {

    private final JwtService jwtService;
    private final MembreRepository membreRepository;

    public JwtFilter(JwtService jwtService, MembreRepository membreRepository) 
    {
        this.jwtService = jwtService;
        this.membreRepository = membreRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException 
     {

        // 1. Recuperer l'en-tete Authorization
        String authHeader = request.getHeader("Authorization");

        // 2. Si pas de token, on laisse passer (les routes publiques marcheront)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Extraire le token (enlever "Bearer ")
        String token = authHeader.substring(7);

        // 4. Verifier le token
        if (jwtService.tokenValide(token)) {
            String matricule = jwtService.extraireMatricule(token);
            Optional<Membre> membreOpt = membreRepository.findByMatricule(matricule);

            if (membreOpt.isPresent()) {
                Membre membre = membreOpt.get();

                // On indique a Spring Security qui est l'utilisateur et son role
                var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + membre.getRole().name()));
                var authentication = new UsernamePasswordAuthenticationToken(
                        membre.getMatricule(), null, authorities);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // 5. Continuer la chaine
        filterChain.doFilter(request, response);
    }
}

/**
 * Ce que fait ce code (le pourquoi, étape par étape)
Ne panique pas devant la longueur, c'est logique quand on suit les numéros :

Récupérer l'en-tête Authorization de la requête (là où le token est envoyé).
Pas de token ? On laisse passer sans rien faire (filterChain.doFilter).
 C'est important : les routes publiques (login, consultation) doivent marcher même sans bracelet.
  Le filtre ne bloque pas, il identifie ceux qui ont un bracelet.
Extraire le token : substring(7) enlève les 7 caractères de "Bearer " pour ne garder que le token.
Vérifier le token avec ton JwtService.tokenValide().
 S'il est valide, on retrouve le matricule, puis le membre en base. Et là, la ligne clé : on dit à Spring Security « cette requête vient de ce membre, qui a ce rôle ». Le "ROLE_" + ... est une convention de Spring Security (il préfixe les rôles par ROLE_).
Continuer : on passe la main à la suite (la vraie route demandée).
 */