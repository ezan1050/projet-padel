package be.ephec.padel.config;

import be.ephec.padel.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers.frameOptions(frame -> frame.disable()))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/sites/**", "/api/terrains/**", "/api/matchs/**").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/matchs/**").hasAnyRole("ADMIN_SITE", "ADMIN_GLOBAL")
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
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
 

 ----- ajustements pour le CORS (Cross-Origin Resource Sharing) -----
 Ce qui change (deux ajouts)
1. Dans filterChain, une ligne ajoutée :
java    .cors(Customizer.withDefaults())
Elle active la gestion du CORS dans Spring Security, en utilisant la configuration qu'on définit juste en dessous.
2. Un nouveau @Bean : corsConfigurationSource()
C'est là qu'on donne les autorisations, ligne par ligne :

setAllowedOrigins(List.of("http://localhost:4200")) → « j'autorise les requêtes venant de cette adresse ». C'est le cœur du réglage : ton frontend Angular.
setAllowedMethods(...) → quelles méthodes HTTP sont permises (GET pour lire, POST pour créer, etc.). Le OPTIONS est important : le navigateur envoie une requête OPTIONS "de reconnaissance" avant les vraies requêtes, pour demander la permission.
setAllowedHeaders(List.of("*")) → tous les en-têtes sont acceptés (notamment Authorization, celui qui portera ton token JWT !).
setAllowCredentials(true) → autorise l'envoi d'infos d'authentification.
registerCorsConfiguration("/**", configuration) → applique cette règle à toutes les routes de ton API.

L'idée à retenir : on a donné au videur (le navigateur) la liste des invités autorisés. Maintenant, les requêtes venant de 4200 passeront.
 
 
 */