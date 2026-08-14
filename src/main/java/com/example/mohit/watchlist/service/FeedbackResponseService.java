package com.example.mohit.watchlist.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.mohit.watchlist.entity.Feedback;
import com.example.mohit.watchlist.entity.FeedbackResponse;
import com.example.mohit.watchlist.repository.FeedbackResponseRepository;

@Service
public class FeedbackResponseService {

    private final FeedbackResponseRepository repository;

    public FeedbackResponseService(
            FeedbackResponseRepository repository) {

        this.repository = repository;
    }

    // =====================================================
    // SAVE EMAIL RESPONSE
    // =====================================================

    public FeedbackResponse saveResponse(
            Feedback feedback,
            String adminEmail,
            String responseMessage,
            String emailStatus) {

        FeedbackResponse response = new FeedbackResponse();

        response.setFeedback(feedback);
        response.setAdminEmail(adminEmail);
        response.setResponseMessage(responseMessage);
        response.setEmailStatus(emailStatus);
        response.setRespondedAt(LocalDateTime.now());

        return repository.save(response);
    }

    // =====================================================
    // GET RESPONSE HISTORY
    // =====================================================

    public List<FeedbackResponse> getResponsesByFeedback(
            Feedback feedback) {

        return repository
                .findByFeedbackOrderByRespondedAtDesc(feedback);
    }
}