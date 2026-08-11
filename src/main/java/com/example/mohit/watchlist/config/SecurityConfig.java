package com.example.mohit.watchlist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.example.mohit.watchlist.security.CustomAuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {

    private final CustomAuthenticationSuccessHandler successHandler;

    public SecurityConfig(
            CustomAuthenticationSuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http

            // ==============================
            // CSRF
            // ==============================
            .csrf(csrf -> csrf.disable())


            // ==============================
            // AUTHORIZATION
            // ==============================
            .authorizeHttpRequests(auth -> auth

                // ---------- PUBLIC PAGES ----------
                .requestMatchers(
                    "/",
                    "/about",
                    "/contact",
                    "/login",
                    "/userlogin",
                    "/adminlogin",
                    "/signup",
                    "/Images/**",
                    "/css/**",
                    "/js/**"
                )
                .permitAll()


                // ---------- ADMIN ----------
                .requestMatchers(
                    "/admin/**"
                )
                .hasRole("ADMIN")


                // ---------- USER ----------
                .requestMatchers(
                    "/dashboard",
                    "/watchlist",
                    "/watchlistItemForm",
                    "/deleteMovie/**",
                    "/updateMovie/**"
                )
                .hasRole("USER")


                // ---------- EVERYTHING ELSE ----------
                .anyRequest()
                .authenticated()
            )


            // ==============================
            // LOGIN
            // ==============================
            .formLogin(login -> login

                .loginPage("/login")

                .loginProcessingUrl("/login")

                // HTML uses name="email"
                .usernameParameter("email")

                // HTML uses name="password"
                .passwordParameter("password")

                // Redirect according to ROLE
                .successHandler(successHandler)

                .failureUrl("/login?error")

                .permitAll()
            )


            // ==============================
            // LOGOUT
            // ==============================
            .logout(logout -> logout

                .logoutSuccessUrl("/login?logout")

                .permitAll()
            );


        return http.build();
    }


    // ==============================
    // AUTHENTICATION MANAGER
    // ==============================

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager();
    }
}