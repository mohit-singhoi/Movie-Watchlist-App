package com.example.mohit.watchlist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.mohit.watchlist.service.*;
import com.example.mohit.watchlist.entity.Activity;
import com.example.mohit.watchlist.entity.Feedback;
import com.example.mohit.watchlist.entity.Movie;
import com.example.mohit.watchlist.entity.User;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

import com.example.mohit.watchlist.entity.FeedbackResponse;
import com.example.mohit.watchlist.service.FeedbackResponseService;



@Controller
public class AdminController {

    private final UserService userService;
    private final MovieServices movieServices;
    private final FeedbackService feedbackService;
    private final ActivityService activityService;
    private final FeedbackResponseService feedbackResponseService;

    public AdminController(
            UserService userService,
            MovieServices movieService,
            FeedbackService feedbackService,
            ActivityService activityService,
            FeedbackResponseService feedbackResponseService) {

        this.userService = userService;
        this.movieServices = movieService;
        this.feedbackService = feedbackService;
        this.activityService = activityService;
        this.feedbackResponseService = feedbackResponseService;
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

        model.addAttribute(
            "movies",
            movieServices.getMoviesByUser(user)
        );

        model.addAttribute(
            "feedbacks",
            feedbackService.getFeedbackByUser(user)
        );
        
        model.addAttribute(
        	    "activities",
        	    activityService.getActivitiesByUser(user)
        	);

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

        Feedback feedback =
                feedbackService.getFeedbackById(id);

        if (feedback == null) {
            return "redirect:/admin/feedback";
        }

        List<FeedbackResponse> responseHistory =
                feedbackResponseService
                        .getResponsesByFeedback(feedback);

        model.addAttribute("feedback", feedback);

        model.addAttribute(
                "responseHistory",
                responseHistory
        );

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
    
 // ==============================
 // DELETE USER All Movies
 // ==============================

    @PostMapping("/admin/users/{id}/delete-movies")
    public String deleteUserMovies(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        User user = userService.getUserById(id);

        if (user == null) {
            return "redirect:/admin/users";
        }

        movieServices.deleteMoviesByUser(user);

        redirectAttributes.addFlashAttribute(
            "successMessage",
            "All movies of " + user.getFullName() + " have been deleted successfully."
        );

        return "redirect:/admin/users/" + id;
    }
    
    // ==============================
    // DELETE USER All Feedback
    // ==============================
    
    @PostMapping("/admin/users/{id}/delete-feedback")
    public String deleteUserFeedback(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        User user = userService.getUserById(id);

        if (user == null) {
            return "redirect:/admin/users";
        }

        feedbackService.deleteFeedbackByUser(user);

        redirectAttributes.addFlashAttribute(
            "successMessage",
            "All feedback of " + user.getFullName() + " has been deleted successfully."
        );

        return "redirect:/admin/users/" + id;
    }
    
    // ==============================
    // DELETE USER All Activities 
    // ==============================
    
    @PostMapping("/admin/users/{id}/delete-activities")
    public String deleteUserActivities(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        User user = userService.getUserById(id);

        if (user == null) {
            return "redirect:/admin/users";
        }

        activityService.deleteActivitiesByUser(user);

        redirectAttributes.addFlashAttribute(
            "successMessage",
            "All activities of " + user.getFullName() + " have been deleted successfully."
        );

        return "redirect:/admin/users/" + id;
    }
    
    
    // Individual Movie Deletion
    @PostMapping("/admin/users/{userId}/movies/{movieId}/delete")
    public String deleteUserMovie(
            @PathVariable Long userId,
            @PathVariable Integer movieId,
            RedirectAttributes redirectAttributes) {

        try {

            movieServices.deleteMovieByIdForAdmin(
                movieId,
                userId
            );

            redirectAttributes.addFlashAttribute(
                "successMessage",
                "✅ Movie successfully deleted."
            );

        } catch (RuntimeException e) {

            redirectAttributes.addFlashAttribute(
                "errorMessage",
                "❌ " + e.getMessage()
            );
        }

        return "redirect:/admin/users/" + userId;
    }
    
    
    // Individual Feedback Deletion
    @PostMapping("/admin/users/{userId}/feedback/{feedbackId}/delete")
    public String deleteUserFeedbackItem(
            @PathVariable Long userId,
            @PathVariable Long feedbackId,
            RedirectAttributes redirectAttributes) {

        try {

            feedbackService.deleteFeedbackByIdForUser(
                feedbackId,
                userId
            );

            redirectAttributes.addFlashAttribute(
                "successMessage",
                "✅ Feedback successfully deleted."
            );

        } catch (RuntimeException e) {

            redirectAttributes.addFlashAttribute(
                "errorMessage",
                "❌ " + e.getMessage()
            );
        }

        return "redirect:/admin/users/" + userId;
    }
    
    
    // Individual Activities Deletion
    @PostMapping("/admin/users/{userId}/activities/{activityId}/delete")
    public String deleteUserActivity(
            @PathVariable Long userId,
            @PathVariable Long activityId,
            RedirectAttributes redirectAttributes) {

        try {

            activityService.deleteActivityByIdForUser(
                activityId,
                userId
            );

            redirectAttributes.addFlashAttribute(
                "successMessage",
                "✅ Activity successfully deleted."
            );

        } catch (RuntimeException e) {

            redirectAttributes.addFlashAttribute(
                "errorMessage",
                "❌ " + e.getMessage()
            );
        }

        return "redirect:/admin/users/" + userId;
    }
    
    
    // Delete User Completely 
    
    @PostMapping("/admin/users/{id}/delete")
    public String deleteUser(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        try {
            userService.deleteUser(id);

            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "✅ User deleted successfully."
            );

        } catch (Exception e) {
            e.printStackTrace();

            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "❌ Failed to delete user."
            );
        }

        return "redirect:/admin/users";
    }
    
}