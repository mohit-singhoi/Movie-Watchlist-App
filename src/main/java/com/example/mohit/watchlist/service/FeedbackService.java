package com.example.mohit.watchlist.service;

import org.springframework.stereotype.Service;

import com.example.mohit.watchlist.entity.Feedback;
import com.example.mohit.watchlist.repository.FeedbackRepo;

@Service
public class FeedbackService {

    private final FeedbackRepo feedbackRepo;

    public FeedbackService(FeedbackRepo feedbackRepo) {
        this.feedbackRepo = feedbackRepo;
    }

    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepo.save(feedback);
    }
}