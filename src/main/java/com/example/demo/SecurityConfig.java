package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //Session auf STATELESS, damit keine Cookies gesetzt werden und jeder Request validiert wird
                )
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated() //Jeder Request muss authenticated werden. Ausnahmen können hinzugefügt werden
                )
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()) //Spring App als ResourceServer registrieren
                        .jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(customJwtAuthenticationConverter())) //CustomJwtAuthenticationConverter hinterlegen
                );
        return http.build();
    }

/*
 // Actuator endpoints run on separate port:
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/static/**").permitAll()
                        Diese 2 Endpoints fürs deployment permitten
    */

    @Bean
    public CustomJwtAuthenticationConverter customJwtAuthenticationConverter() {
        return new CustomJwtAuthenticationConverter();
    }
}
