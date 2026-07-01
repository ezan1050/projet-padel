package be.ephec.padel.config;

import be.ephec.padel.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig
 {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) 
    {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception 
    {
        http
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers.frameOptions(frame -> frame.disable())) // pour la console H2
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/sites/**", "/api/terrains/**", "/api/matchs/**").permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
     {
        return new BCryptPasswordEncoder();
     }
}

    /**
 * Ce que fait ce code (le pourquoi)

@Configuration → dit à Spring : « cette classe contient de la configuration ». Spring la lit au démarrage.
@Bean sur filterChain → on définit les règles de sécurité. Décortiquons :

csrf(csrf -> csrf.disable()) → on désactive une protection (CSRF) qui n'est pas adaptée à une API REST consommée par Angular. Standard pour ce type d'appli.
anyRequest().permitAll() → « autorise TOUTES les requêtes pour l'instant ». C'est temporaire : ça rouvre tes routes le temps qu'on construise le login. On resserrera après.


@Bean sur passwordEncoder → c'est crucial pour ta grille PDW 
(qui exige "pas de mot de passe en clair"). BCryptPasswordEncoder
 est l'outil qui chiffre les mots de passe. On l'installe maintenant,
  on l'utilisera à l'étape login. Le mot "Bean" veut dire :
   un objet que Spring fabrique et garde sous la main pour le fournir là où on en a besoin (comme les repositories).

   On injecte le JwtFilter (constructeur).
.addFilterBefore(jwtFilter, ...) → dit à Spring Security : « exécute mon filtre JWT avant le traitement normal de chaque requête ».
 C'est ce qui active le contrôle du bracelet.
On garde permitAll() pour l'instant : le filtre identifie les gens, mais on ne bloque encore personne.
 C'est volontaire — on resserrera à l'étape des rôles.

 Ce que fait cette config (le pourquoi)
Chaque requestMatchers est une règle, lue de haut en bas :

/api/auth/** permitAll → le login est public (logique : il faut pouvoir se connecter sans être déjà connecté).
/h2-console/** permitAll → la console H2 reste accessible en dev.
GET sur sites/terrains/matchs permitAll → consulter ces infos est public (un visiteur peut voir les matchs). Note : c'est uniquement le GET (lecture). Créer/modifier (POST) tombera dans la règle suivante.
anyRequest().authenticated() → tout le reste exige au minimum d'être connecté (avoir un bracelet valide). Par exemple créer un membre, faire un paiement, appliquer une pénalité...

Le frameOptions.disable() est juste un réglage technique pour que la console H2 continue de s'afficher (sinon Spring Security la bloque).
 */