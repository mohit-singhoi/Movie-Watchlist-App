package com.example.mohit.watchlist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth
            	    .requestMatchers(
            	            "/",
            	            "/about",
            	            "/contact",
            	            "/login",
            	            "/signup",
            	            "/Images/**",
            	            "/css/**",
            	            "/js/**")
            	    .permitAll()

            	    .requestMatchers(
            	            "/watchlist",
            	            "/watchlistItemForm",
            	            "/deleteMovie/**",
            	            "/updateMovie/**")
            	    .authenticated()

            	    .anyRequest().authenticated()
            	)

            .formLogin(login -> login
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/", true)
                    .failureUrl("/login?error")
                    .permitAll()
            )

            .logout(logout -> logout
                    .logoutSuccessUrl("/login?logout")
                    .permitAll()
            );

        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
            throws Exception {
        return configuration.getAuthenticationManager();
    }
}