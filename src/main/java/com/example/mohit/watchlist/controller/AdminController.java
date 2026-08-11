package com.example.mohit.watchlist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.mohit.watchlist.service.UserService;
import com.example.mohit.watchlist.entity.User;


@Controller
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    // ==============================
    // ADMIN DASHBOARD
    // ==============================

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {

        model.addAttribute(
            "totalUsers",
            userService.getTotalUsers()
        );

        return "admin/admin-dashboard";
    }


    // ==============================
    // USERS
    // ==============================
    

    @GetMapping("/admin/users")
    public String users(Model model) {

        model.addAttribute(
            "users",
            userService.getAllUsers()
        );

        return "admin/users";
    }


    // ==============================
    // VIEW SINGLE USER
    // ==============================

    @GetMapping("/admin/users/{id}")
    public String viewUser(
            @PathVariable Long id,
            Model model) {

        User user = userService.getUserById(id);

        if (user == null) {
            return "redirect:/admin/users";
        }

        model.addAttribute("user", user);

        return "admin/user-details";
    }


    // ==============================
    // MOVIES
    // ==============================

    @GetMapping("/admin/movies")
    public String movies() {
        return "admin/movies";
    }


    // ==============================
    // FEEDBACK
    // ==============================

    @GetMapping("/admin/feedback")
    public String feedback() {
        return "admin/feedback";
    }


    // ==============================
    // ACTIVITIES
    // ==============================

    @GetMapping("/admin/activities")
    public String activities() {
        return "admin/activities";
    }
}