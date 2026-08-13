package com.example.mohit.watchlist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.mohit.watchlist.entity.Feedback;
import com.example.mohit.watchlist.entity.User;
import com.example.mohit.watchlist.repository.FeedbackRepo;

@Service
public class FeedbackService {

    private final FeedbackRepo feedbackRepo;

    public FeedbackService(FeedbackRepo feedbackRepo) {
        this.feedbackRepo = feedbackRepo;
    }

    // Save feedback from user
    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepo.save(feedback);
    }

    // Get all feedback for admin
    public List<Feedback> getAllFeedback() {
        return feedbackRepo.findAll();
    }

    // Total feedback for admin dashboard
    public long getTotalFeedback() {
        return feedbackRepo.count();
    }

    // Get feedback by ID
    public Feedback getFeedbackById(Long id) {
        return feedbackRepo.findById(id).orElse(null);
    }

    // Get feedback submitted by a specific user
    public List<Feedback> getFeedbackByUser(User user) {
        return feedbackRepo.findByUser(user);
    }
    
 // Delete all feedback submitted by a specific user
    public void deleteFeedbackByUser(User user) {
        feedbackRepo.deleteAll(feedbackRepo.findByUser(user));
    }
    
    public void deleteFeedbackByIdForUser(Long feedbackId, Long userId) {

        Feedback feedback = feedbackRepo.findById(feedbackId)
                .orElse(null);

        if (feedback == null) {
            throw new RuntimeException("Feedback not found");
        }

        if (feedback.getUser() == null ||
            !feedback.getUser().getId().equals(userId)) {

            throw new RuntimeException(
                "Feedback does not belong to this user"
            );
        }

        feedbackRepo.delete(feedback);
    }
}