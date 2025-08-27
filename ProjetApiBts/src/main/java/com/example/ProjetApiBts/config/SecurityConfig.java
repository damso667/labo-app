package com.example.ProjetApiBts.config;

// ========================== // PACKAGE: com.example.ProjetApiBts // ========================== //
// Ce bundle ajoute:
// 1) Authentification par SESSION pour Médecins et Techniciens (email + mot de passe BCrypt)
// 2) Endpoints réservés aux Médecins pour lister les patients dispo (tri alpha) et "récupérer" un patient
// - Attribution exclusive: une fois récupéré par un médecin, les autres ne le voient plus et ne peuvent plus l'assigner
// 3) Codes de retour HTTP appropriés (200, 401, 403, 404, 409)
// 4) Gestion de concurrence via verrou pessimiste lors de l'assignation //
// NB: garde les autorités telles que définies dans tes entités: ROLE_MEDECIN et ROLE_TECHNITIEN (orthographe inchangée) //
// Dépendances attendues: spring-boot-starter-web, spring-boot-starter-security, spring-boot-starter-data-jpa, lombok // ==========================

// ========================== // SecurityConfig.java // ========================== package com.example.ProjetApiBts.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor

public class SecurityConfig {

    private final AppUserDetailService userDetailsService;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();


    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider p = new DaoAuthenticationProvider();
        p.setUserDetailsService(userDetailsService); p.setPasswordEncoder(passwordEncoder());
        return p;
    }
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.authenticationProvider(daoAuthenticationProvider());
        return builder.build();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(cors ->cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/medecins/**").hasRole("MEDECIN")

                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/medecins/**").hasRole("MEDECIN")
                        .requestMatchers("/api/techniciens/**").hasRole("TECHNITIEN")
                        .requestMatchers("/api/analyses/**").hasAnyRole("MEDECIN")
                        .requestMatchers("/api/analyses/**").hasAnyRole("TECHNICIEN")
                        .requestMatchers("/api/reactifs/**").hasAnyRole("SECRETAIRE","TECHNITIEN")
                        .requestMatchers("/api/type-examens/**").hasAnyRole("MEDECIN","TECHNITIEN","SECRETAIRE")
                        .anyRequest().authenticated()
                ) .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
        corsConfiguration.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTION","PATCH"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);
        return  source;
    }

}
