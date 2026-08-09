package com.example.mohit.watchlist.service;

import com.example.mohit.watchlist.entity.Movie;
import com.example.mohit.watchlist.entity.User;
import com.example.mohit.watchlist.repository.MovieRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    private final MovieRepo movieRepo;

    public DashboardService(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    // Get movies belonging to the logged-in user
    public List<Movie> getUserMovies(User user) {
        return movieRepo.findByUser(user);
    }

    // Total movies of the logged-in user
    public long getTotalMovies(User user) {
        return movieRepo.findByUser(user).size();
    }

    // Recent movies
    public List<Movie> getRecentMovies(User user) {

        List<Movie> movies = movieRepo.findByUser(user);

        if (movies.size() > 5) {
            return movies.subList(0, 5);
        }

        return movies;
    }
}