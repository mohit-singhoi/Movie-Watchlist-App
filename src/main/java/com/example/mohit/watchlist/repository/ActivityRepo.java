package com.example.mohit.watchlist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mohit.watchlist.entity.Activity;
import com.example.mohit.watchlist.entity.User;

@Repository
public interface ActivityRepo extends JpaRepository<Activity, Long> {

    // All activities for admin
    List<Activity> findAllByOrderByCreatedAtDesc();

    // Activities of a specific user
    List<Activity> findByUserOrderByCreatedAtDesc(User user);

    // Delete all activities belonging to a user
    void deleteByUser(User user);
}