package com.example.mohit.watchlist.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final SecurityContextRepository securityContextRepository =
            new HttpSessionSecurityContextRepository();

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    // ==========================================
    // LOGIN SELECTION PAGE
    // ==========================================

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }


    // ==========================================
    // USER LOGIN PAGE
    // ==========================================

    @GetMapping("/userlogin")
    public String userLoginPage() {
        return "auth/userlogin";
    }


    // ==========================================
    // ADMIN LOGIN PAGE
    // ==========================================

    @GetMapping("/adminlogin")
    public String adminLoginPage() {
        return "auth/adminlogin";
    }


    // ==========================================
    // ADMIN LOGIN PROCESSING
    // ==========================================

    @PostMapping("/adminlogin")
    public String adminLogin(
            @RequestParam String email,
            @RequestParam String password,
            HttpServletRequest request) {

        try {

            // Create authentication request
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(
                            email,
                            password
                    );


            // Authenticate using Spring Security
            Authentication authentication =
                    authenticationManager.authenticate(token);


            // Check ADMIN role
            boolean isAdmin =
                    authentication.getAuthorities()
                            .stream()
                            .anyMatch(authority ->
                                    authority.getAuthority()
                                            .equals("ROLE_ADMIN")
                            );


            // If user is not ADMIN
            if (!isAdmin) {

                return "redirect:/adminlogin?error";
            }


            // Create Security Context
            SecurityContext context =
                    SecurityContextHolder.createEmptyContext();

            context.setAuthentication(authentication);

            SecurityContextHolder.setContext(context);


            // Save Security Context in session
            securityContextRepository.saveContext(
                    context,
                    request,
                    null
            );


            // Admin successfully logged in
            return "redirect:/admin/dashboard";


        } catch (Exception e) {

            // Invalid email/password
            return "redirect:/adminlogin?error";
        }
    }
}