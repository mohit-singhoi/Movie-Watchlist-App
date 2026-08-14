package com.example.mohit.watchlist.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.mohit.watchlist.entity.Activity;
import com.example.mohit.watchlist.entity.Feedback;
import com.example.mohit.watchlist.entity.User;
import com.example.mohit.watchlist.security.CustomUserDetails;
import com.example.mohit.watchlist.service.ActivityService;
import com.example.mohit.watchlist.service.EmailService;
import com.example.mohit.watchlist.service.FeedbackResponseService;
import com.example.mohit.watchlist.service.FeedbackService;

@Controller
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final ActivityService activityService;
    private final EmailService emailService;
    private final FeedbackResponseService feedbackResponseService;

    public FeedbackController(
            FeedbackService feedbackService,
            ActivityService activityService,
            EmailService emailService,
            FeedbackResponseService feedbackResponseService) {

        this.feedbackService = feedbackService;
        this.activityService = activityService;
        this.emailService = emailService;
        this.feedbackResponseService = feedbackResponseService;
    }

    // =====================================================
    // USER - SHOW FEEDBACK PAGE
    // =====================================================

    @GetMapping("/feedback")
    public String showFeedbackForm(Model model) {

        model.addAttribute(
                "feedback",
                new Feedback()
        );

        return "feedback";
    }

    // =====================================================
    // USER - SUBMIT FEEDBACK
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
            @RequestParam("responseMessage") String responseMessage,
            RedirectAttributes redirectAttributes) {

        try {

            // Get feedback
            Feedback feedback =
                    feedbackService.getFeedbackById(id);

            // Check feedback and user
            if (feedback == null
                    || feedback.getUser() == null) {

                redirectAttributes.addFlashAttribute(
                        "errorMessage",
                        "❌ User information is not available."
                );

                return "redirect:/admin/feedback/" + id;
            }

            // Get user information
            String userEmail =
                    feedback.getUser().getEmail();

            String userName =
                    feedback.getUser().getFullName();

            // Validate email
            if (userEmail == null
                    || userEmail.isBlank()) {

                redirectAttributes.addFlashAttribute(
                        "errorMessage",
                        "❌ User email is not available."
                );

                return "redirect:/admin/feedback/" + id;
            }

            // Validate response message
            if (responseMessage == null
                    || responseMessage.trim().isEmpty()) {

                redirectAttributes.addFlashAttribute(
                        "errorMessage",
                        "❌ Please enter a response before sending."
                );

                return "redirect:/admin/feedback/" + id;
            }

            String cleanResponse =
                    responseMessage.trim();

            // =================================================
            // 1. SEND EMAIL
            // =================================================

            emailService.sendFeedbackResponse(
                    userEmail,
                    userName,
                    cleanResponse
            );

            // =================================================
            // 2. SAVE RESPONSE HISTORY
            // =================================================

            feedbackResponseService.saveResponse(
                    feedback,
                    "supportmoviewatchlist@gmail.com",
                    cleanResponse,
                    "SENT"
            );

            // =================================================
            // SUCCESS MESSAGE
            // =================================================

            redirectAttributes.addFlashAttribute(
                    "successMessage",
                    "✅ Response successfully sent to the User."
            );

        } catch (Exception e) {

            e.printStackTrace();

            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    "❌ Failed to send response. Please try again."
            );
        }

        return "redirect:/admin/feedback/" + id;
    }
    
}

