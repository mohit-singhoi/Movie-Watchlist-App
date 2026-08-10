package com.example.mohit.watchlist.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.mohit.watchlist.entity.User;
import com.example.mohit.watchlist.repository.UserRepo;
import com.example.mohit.watchlist.service.DashboardService;

@Controller
public class DashboardController {

    private final DashboardService dashboardService;
    private final UserRepo userRepo;

    public DashboardController(DashboardService dashboardService,
                               UserRepo userRepo) {
        this.dashboardService = dashboardService;
        this.userRepo = userRepo;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model,
                                Authentication authentication) {

        // Get logged-in user's email
        String email = authentication.getName();

        // Find user from database
        User user = userRepo.findByEmail(email);

        // User not found
        if (user == null) {
            return "redirect:/login";
        }

        // ==============================
        // DASHBOARD STATISTICS
        // ==============================

        model.addAttribute(
                "totalMovies",
                dashboardService.getTotalMovies(user)
        );

        model.addAttribute(
                "averageRating",
                dashboardService.getAverageRating(user)
        );

        model.addAttribute(
                "highPriorityMovies",
                dashboardService.getHighPriorityMovies(user)
        );

        model.addAttribute(
                "reviewCount",
                dashboardService.getReviewCount(user)
        );

        // ==============================
        // RECENT MOVIES
        // ==============================

        model.addAttribute(
                "recentMovies",
                dashboardService.getRecentMovies(user)
        );

        return "dashboard";
    }
}