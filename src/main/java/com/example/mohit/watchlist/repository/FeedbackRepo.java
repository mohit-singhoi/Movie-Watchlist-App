package com.example.mohit.watchlist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mohit.watchlist.entity.Feedback;
import com.example.mohit.watchlist.entity.User;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Long> {

    // Get feedback submitted by a specific user
    List<Feedback> findByUser(User user);

    // Delete all feedback belonging to a user
    void deleteByUser(User user);
}