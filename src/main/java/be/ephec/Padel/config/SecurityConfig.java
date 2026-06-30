package be.ephec.padel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            );
        return http.build();
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
 */