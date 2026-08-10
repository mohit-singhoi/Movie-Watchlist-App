package com.example.mohit.watchlist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mohit.watchlist.entity.Movie;
import com.example.mohit.watchlist.entity.User;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Integer> {

    List<Movie> findByUser(User user);
}