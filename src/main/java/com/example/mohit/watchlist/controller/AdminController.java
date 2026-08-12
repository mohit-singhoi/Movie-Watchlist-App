package com.example.mohit.watchlist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.mohit.watchlist.service.*;
import com.example.mohit.watchlist.entity.Activity;
import com.example.mohit.watchlist.entity.Feedback;
import com.example.mohit.watchlist.entity.Movie;
import com.example.mohit.watchlist.entity.User;


@Controller
public class AdminController {

    private final UserService userService;
    private final MovieServices movieServices;
    private final FeedbackService feedbackService;
    private final ActivityService activityService;

    public AdminController(
            UserService userService,
            MovieServices movieService,
            FeedbackService feedbackService,
            ActivityService activityService) {

        this.userService = userService;
        this.movieServices = movieService;
        this.feedbackService = feedbackService;
        this.activityService = activityService;
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
        
        model.addAttribute(
                "totalMovies",
                movieServices.getTotalMovies()
            );
        
        model.addAttribute(
                "totalFeedback",
                feedbackService.getTotalFeedback()
            );
        
        model.addAttribute(
        	    "totalActivities",
        	    activityService.getTotalActivities()
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
    public String movies(Model model) {

        model.addAttribute(
            "movies",
            movieServices.getAllMovies1()
        );

        model.addAttribute(
            "totalMovies",
            movieServices.getTotalMovies()
        );

        return "admin/movies";
    }
    
    
    @GetMapping("/admin/movies/{id}")
    public String viewMovie(
            @PathVariable Integer id,
            Model model) {

        Movie movie = movieServices.getMovieById(id);

        if (movie == null) {
            return "redirect:/admin/movies";
        }

        model.addAttribute("movie", movie);

        return "admin/movie-details";
    }


    // ==============================
    // FEEDBACK
    // ==============================

    @GetMapping("/admin/feedback")
    public String feedback(Model model) {

        model.addAttribute(
            "feedbacks",
            feedbackService.getAllFeedback()
        );

        model.addAttribute(
            "totalFeedback",
            feedbackService.getTotalFeedback()
        );

        return "admin/feedback";
    }
    
    @GetMapping("/admin/feedback/{id}")
    public String viewFeedback(
            @PathVariable Long id,
            Model model) {

        Feedback feedback = feedbackService.getFeedbackById(id);

        if (feedback == null) {
            return "redirect:/admin/feedback";
        }

        model.addAttribute("feedback", feedback);

        return "admin/feedback-details";
    }


    // ==============================
    // ACTIVITIES
    // ==============================

    @GetMapping("/admin/activities")
    public String activities(Model model) {

        model.addAttribute(
            "activities",
            activityService.getAllActivities()
        );

        return "admin/activities";
    }
    
    @GetMapping("/admin/activities/{id}")
    public String viewActivity(
            @PathVariable Long id,
            Model model) {

        Activity activity = activityService.getActivityById(id);

        if (activity == null) {
            return "redirect:/admin/activities";
        }

        model.addAttribute("activity", activity);

        return "admin/activity-details";
    }
    
}