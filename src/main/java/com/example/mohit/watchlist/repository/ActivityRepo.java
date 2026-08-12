package com.example.mohit.watchlist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mohit.watchlist.entity.Activity;

@Repository
public interface ActivityRepo extends JpaRepository<Activity, Long> {

    // Get latest activities first
    List<Activity> findAllByOrderByCreatedAtDesc();
}