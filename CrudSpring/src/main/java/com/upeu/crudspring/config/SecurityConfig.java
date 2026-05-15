package com.upeu.crudspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/images/**", "/css/**", "/static/**").permitAll() // Público
                        .anyRequest().authenticated() // Todo lo demás requiere login
                )
                .formLogin((form) -> form
                        .loginPage("/login") // Nuestra página personalizada
                        .defaultSuccessUrl("/productos/listar", true) // A donde ir después de entrar
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout") // En lugar de logoutRequestMatcher
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


