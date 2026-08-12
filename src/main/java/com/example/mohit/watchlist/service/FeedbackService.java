package com.example.mohit.watchlist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.mohit.watchlist.entity.Feedback;
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
    
    public Feedback getFeedbackById(Long id) {
        return feedbackRepo.findById(id).orElse(null);
    }
}