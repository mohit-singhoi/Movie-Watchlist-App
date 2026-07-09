package com.example.mohit.watchlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mohit.watchlist.entity.Movie;



@Repository
public interface MovieRepo extends JpaRepository<Movie, Integer> {

}