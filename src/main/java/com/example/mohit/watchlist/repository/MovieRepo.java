package com.example.mohit.watchlist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mohit.watchlist.entity.Movie;
import com.example.mohit.watchlist.entity.User;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Integer> {

    // Get all movies of a specific user
    List<Movie> findByUser(User user);

    // Delete all movies belonging to a user
    void deleteByUser(User user);
}