package com.example.mohit.watchlist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin/admin-dashboard";
    }

    @GetMapping("/admin/users")
    public String users() {
        return "admin/users";
    }

    @GetMapping("/admin/movies")
    public String movies() {
        return "admin/movies";
    }

    @GetMapping("/admin/feedback")
    public String feedback() {
        return "admin/feedback";
    }

    @GetMapping("/admin/activities")
    public String activities() {
        return "admin/activities";
    }
}