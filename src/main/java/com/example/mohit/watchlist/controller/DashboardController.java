package com.example.mohit.watchlist.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.mohit.watchlist.entity.Movie;
import com.example.mohit.watchlist.entity.User;
import com.example.mohit.watchlist.service.DashboardService;

@Controller
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Authentication authentication, Model model) {

        // Get logged-in user's principal
        Object principal = authentication.getPrincipal();

        // Get User object from your custom UserDetails
        User user = ((com.example.mohit.watchlist.security.CustomUserDetails) principal).getUser();

        // Get user's dashboard data
        long totalMovies = dashboardService.getTotalMovies(user);

        List<Movie> recentMovies =
                dashboardService.getRecentMovies(user);

        // Send data to dashboard.html
        model.addAttribute("user", user);
        model.addAttribute("totalMovies", totalMovies);
        model.addAttribute("recentMovies", recentMovies);

        return "dashboard";
    }
}