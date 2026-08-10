package com.example.mohit.watchlist.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.mohit.watchlist.entity.Movie;
import com.example.mohit.watchlist.entity.User;
import com.example.mohit.watchlist.repository.MovieRepo;

@Service
public class DashboardService {

    private final MovieRepo movieRepo;

    public DashboardService(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    // =========================================================
    // TOTAL MOVIES
    // =========================================================

    public int getTotalMovies(User user) {

        if (user == null) {
            return 0;
        }

        List<Movie> movies = movieRepo.findByUser(user);

        if (movies == null) {
            return 0;
        }

        return movies.size();
    }


    // =========================================================
    // AVERAGE RATING
    // =========================================================

    public double getAverageRating(User user) {

        if (user == null) {
            return 0.0;
        }

        List<Movie> movies = movieRepo.findByUser(user);

        if (movies == null || movies.isEmpty()) {
            return 0.0;
        }

        double totalRating = 0.0;
        int ratingCount = 0;

        for (Movie movie : movies) {

            if (movie == null) {
                continue;
            }

            if (movie.getRating() != null) {

                totalRating += movie.getRating();
                ratingCount++;
            }
        }

        if (ratingCount == 0) {
            return 0.0;
        }

        double average = totalRating / ratingCount;

        // Round to one decimal place
        return Math.round(average * 10.0) / 10.0;
    }


    // =========================================================
    // HIGH PRIORITY MOVIES
    // =========================================================

    public long getHighPriorityMovies(User user) {

        if (user == null) {
            return 0;
        }

        List<Movie> movies = movieRepo.findByUser(user);

        if (movies == null || movies.isEmpty()) {
            return 0;
        }

        return movies.stream()

                .filter(movie -> movie != null)

                .filter(movie -> movie.getPriority() != null)

                .filter(movie -> {

                    String priority = movie.getPriority()
                            .trim()
                            .toUpperCase();

                    return priority.equals("H")
                            || priority.equals("HIGH");
                })

                .count();
    }


    // =========================================================
    // REVIEWS
    // =========================================================

    public long getReviewCount(User user) {

        if (user == null) {
            return 0;
        }

        List<Movie> movies = movieRepo.findByUser(user);

        if (movies == null || movies.isEmpty()) {
            return 0;
        }

        return movies.stream()

                .filter(movie -> movie != null)

                .filter(movie -> movie.getComment() != null)

                .filter(movie -> !movie.getComment()
                        .trim()
                        .isEmpty())

                .count();
    }


    // =========================================================
    // RECENT MOVIES
    // =========================================================

    public List<Movie> getRecentMovies(User user) {

        if (user == null) {
            return List.of();
        }

        List<Movie> movies = movieRepo.findByUser(user);

        if (movies == null || movies.isEmpty()) {
            return List.of();
        }

        /*
         * Show maximum 5 movies.
         *
         * If your Movie entity has an ID generated in increasing order,
         * sorting by ID descending will normally show the newest movies first.
         */
        return movies.stream()

                .filter(movie -> movie != null)

                .sorted(
                        Comparator.comparing(
                                Movie::getId,
                                Comparator.nullsLast(Comparator.reverseOrder())
                        )
                )

                .limit(5)

                .toList();
    }
}