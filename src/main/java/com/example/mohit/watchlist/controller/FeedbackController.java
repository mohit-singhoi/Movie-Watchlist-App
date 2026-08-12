package com.example.mohit.watchlist.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mohit.watchlist.entity.Activity;
import com.example.mohit.watchlist.entity.Feedback;
import com.example.mohit.watchlist.entity.User;
import com.example.mohit.watchlist.security.CustomUserDetails;
import com.example.mohit.watchlist.service.ActivityService;
import com.example.mohit.watchlist.service.FeedbackService;

@Controller
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final ActivityService activityService;

    public FeedbackController(FeedbackService feedbackService, ActivityService activityService) {
        this.feedbackService = feedbackService;
        this.activityService = activityService;
    }

    // Show feedback page
    @GetMapping("/feedback")
    public String showFeedbackForm(Model model) {

        model.addAttribute("feedback", new Feedback());

        return "feedback";
    }

    // Save feedback
    @PostMapping("/feedback")
    public String submitFeedback(
            @ModelAttribute("feedback") Feedback feedback,
            Authentication authentication,
            RedirectAttributes redirectAttributes) {

        CustomUserDetails customUser =
                (CustomUserDetails) authentication.getPrincipal();

        User user = customUser.getUser();

        // Attach logged-in user
        feedback.setUser(user);

        // Save feedback
        feedbackService.saveFeedback(feedback);
        activityService.saveActivity(
        	    new Activity(
        	        "💬",
        	        "New Feedback Submitted",
        	        "A user submitted new feedback."
        	    )
        	);

        // Show success popup after redirect
        redirectAttributes.addFlashAttribute("success", true);

        return "redirect:/feedback";
    }

    
}