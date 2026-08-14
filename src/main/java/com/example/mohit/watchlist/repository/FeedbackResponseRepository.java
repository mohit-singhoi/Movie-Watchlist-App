package com.example.mohit.watchlist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mohit.watchlist.entity.Feedback;
import com.example.mohit.watchlist.entity.FeedbackResponse;

public interface FeedbackResponseRepository
        extends JpaRepository<FeedbackResponse, Long> {

    List<FeedbackResponse> findByFeedbackOrderByRespondedAtDesc(
            Feedback feedback);
}