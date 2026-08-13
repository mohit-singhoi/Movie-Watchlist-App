package com.example.mohit.watchlist.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mohit.watchlist.entity.Activity;
import com.example.mohit.watchlist.entity.Feedback;
import com.example.mohit.watchlist.entity.User;
import com.example.mohit.watchlist.security.CustomUserDetails;
import com.example.mohit.watchlist.service.ActivityService;
import com.example.mohit.watchlist.service.EmailService;
import com.example.mohit.watchlist.service.FeedbackService;

@Controller
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final ActivityService activityService;
    private final EmailService emailService;

    public FeedbackController(
            FeedbackService feedbackService,
            ActivityService activityService,
            EmailService emailService) {

        this.feedbackService = feedbackService;
        this.activityService = activityService;
        this.emailService = emailService;
    }

    // =====================================================
    // SHOW FEEDBACK PAGE
    // =====================================================

    @GetMapping("/feedback")
    public String showFeedbackForm(Model model) {

        model.addAttribute("feedback", new Feedback());

        return "feedback";
    }


    // =====================================================
    // SAVE FEEDBACK
    // =====================================================

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

        // Save activity
        activityService.saveActivity(
            new Activity(
                "💬",
                "New Feedback Submitted",
                user.getFullName()
                    + " submitted new feedback.",
                user
            )
        );

        // Success popup
        redirectAttributes.addFlashAttribute(
            "success",
            true
        );

        return "redirect:/feedback";
    }


    // =====================================================
    // ADMIN - RESPOND TO FEEDBACK
    // =====================================================

    @PostMapping("/admin/feedback/{id}/respond")
    public String respondToFeedback(
            @PathVariable Long id,
            @ModelAttribute("adminResponse") String adminResponse,
            RedirectAttributes redirectAttributes) {

        Feedback feedback =
                feedbackService.getFeedbackById(id);

        // Feedback not found
        if (feedback == null) {

            redirectAttributes.addFlashAttribute(
                "errorMessage",
                "Feedback not found."
            );

            return "redirect:/admin/feedback";
        }

        // Check user/email
        if (feedback.getUser() == null
                || feedback.getUser().getEmail() == null
                || feedback.getUser().getEmail().isBlank()) {

            redirectAttributes.addFlashAttribute(
                "errorMessage",
                "User email is not available."
            );

            return "redirect:/admin/feedback/" + id;
        }

        // Validate response
        if (adminResponse == null
                || adminResponse.trim().isEmpty()) {

            redirectAttributes.addFlashAttribute(
                "errorMessage",
                "Please enter a response before sending."
            );

            return "redirect:/admin/feedback/" + id;
        }

        // Send email
        emailService.sendFeedbackResponse(
            feedback.getUser().getEmail(),
            feedback.getUser().getFullName(),
            adminResponse.trim()
        );

        // Success message
        redirectAttributes.addFlashAttribute(
            "successMessage",
            "✅ Response successfully sent to "
                + feedback.getUser().getEmail()
        );

        return "redirect:/admin/feedback/" + id;
    }
}