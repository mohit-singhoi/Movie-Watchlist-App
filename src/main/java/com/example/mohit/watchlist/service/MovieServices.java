package com.example.mohit.watchlist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.mohit.watchlist.entity.User;
import com.example.mohit.watchlist.security.CustomUserDetails;
import com.example.mohit.watchlist.entity.Activity;
import com.example.mohit.watchlist.entity.Movie;
import com.example.mohit.watchlist.repository.MovieRepo;
import com.example.mohit.watchlist.service.*;

import jakarta.validation.Valid;

@Validated
@Service

public class MovieServices {
	
	@Autowired
	MovieRepo movieRepo;
	
	@Autowired
	RatingService ratingService;
	
	@Autowired
	ActivityService activityService;
	
	// Old Version
//	public void create(Movie movie) {
//		// TODO Auto-generated method stub
//		
//		String rating = ratingService.getMovieRating(movie.getTitle());
//		 
//		if(rating != null) {
//			movie.setRating(Float.parseFloat(rating));
//		}
//		
//		movieRepo.save(movie);
//
//	}
//	
//	// New Version 1
//	public void create(Movie movie) {
//
//	    String rating = ratingService.getMovieRating(movie.getTitle());
//
//	    if (rating != null) {
//
//	        float imdbRating = Float.parseFloat(rating);
//	        movie.setRating(imdbRating);
//
//	        if (imdbRating < 3) {
//	            movie.setPriority("Low");
//	        } else if (imdbRating < 7) {
//	            movie.setPriority("Medium");
//	        } else {
//	            movie.setPriority("High");
//	        }
//	    }
//
//	    movieRepo.save(movie);
//	}
//	
	
//	// New Version 2
//	public void create(Movie movie) {
//
//	    String rating = ratingService.getMovieRating(movie.getTitle());
//
//	    if (rating != null && !rating.isBlank()) {
//
//	        float imdbRating = Float.parseFloat(rating);
//
//	        movie.setRating(imdbRating);
//
//	        if (imdbRating < 3) {
//	            movie.setPriority("Low");
//	        } else if (imdbRating < 7) {
//	            movie.setPriority("Medium");
//	        } else {
//	            movie.setPriority("High");
//	        }
//
//	    } else {
//
//	        // Movie not found on IMDb
//	        movie.setPriority(normalizePriority(movie.getPriority()));
//	    }
//
//	    movieRepo.save(movie);
//	}
	
	
	//Version 3 for Multi user support 
	public void create(Movie movie) {

	    String rating = ratingService.getMovieRating(movie.getTitle());

	    if (rating != null && !rating.isBlank()) {

	        float imdbRating = Float.parseFloat(rating);

	        movie.setRating(imdbRating);

	        if (imdbRating < 3) {
	            movie.setPriority("Low");
	        } else if (imdbRating < 7) {
	            movie.setPriority("Medium");
	        } else {
	            movie.setPriority("High");
	        }

	    } else {

	        // Movie not found on IMDb
	        movie.setPriority(normalizePriority(movie.getPriority()));
	    }

	    // Get the currently logged-in user
	    Authentication authentication =
	            SecurityContextHolder.getContext().getAuthentication();

	    CustomUserDetails customUser =
	            (CustomUserDetails) authentication.getPrincipal();

	    User user = customUser.getUser();

	    // Associate the movie with the logged-in user
	    movie.setUser(user);

	 // Save the movie
	    movieRepo.save(movie);

	    // Save activity
	    activityService.saveActivity(
	        new Activity(
	            "🎬",
	            "Movie Added",
	            user.getFullName()
	                + " added \"" + movie.getTitle() + "\" to the watchlist.",user
	        )
	    );
	}
	
	
	//show all movie to all user
	
//	public List<Movie> getAllMovies() {
//		// TODO Auto-generated method stub
//		
//		return movieRepo.findAll();
//
//	}
	
	
	// For specific user support
	
	public List<Movie> getAllMovies() {

	    Authentication authentication =
	            SecurityContextHolder.getContext().getAuthentication();

	    CustomUserDetails customUser =
	            (CustomUserDetails) authentication.getPrincipal();

	    return movieRepo.findByUser(customUser.getUser());

	}
	
	public Movie getMovieById(Integer id) {
	    return movieRepo.findById(id).orElse(null);
	}
	
	
	// Old Version
//	public void update(Movie movie, Integer id) {
//		// TODO Auto-generated method stub
//		
//		Movie toBeUpdated = getMovieById(id);
//		toBeUpdated.setTitle(movie.getTitle());
//		toBeUpdated.setRating(movie.getRating());
//		toBeUpdated.setSource(movie.getSource());
//		toBeUpdated.setPriority(movie.getPriority());
//		toBeUpdated.setComment(movie.getComment());
//		
//		movieRepo.save(toBeUpdated);
//		
//	}
	
	
	// New version to Automate Priority and ratings.
	public void update(Movie movie, Integer id) {

	    Movie toBeUpdated = getMovieById(id);

	    Authentication authentication =
	            SecurityContextHolder.getContext().getAuthentication();

	    CustomUserDetails customUser =
	            (CustomUserDetails) authentication.getPrincipal();

	    // Get logged-in user
	    User user = customUser.getUser();

	    // Check movie ownership
	    if (!toBeUpdated.getUser().getId()
	            .equals(user.getId())) {

	        throw new RuntimeException("Access Denied");
	    }

	    toBeUpdated.setTitle(movie.getTitle());
	    toBeUpdated.setSource(movie.getSource());
	    toBeUpdated.setComment(movie.getComment());

	    String rating = ratingService.getMovieRating(movie.getTitle());

	    if (rating != null && !rating.isBlank()) {

	        float imdbRating = Float.parseFloat(rating);

	        toBeUpdated.setRating(imdbRating);

	        if (imdbRating < 3) {
	            toBeUpdated.setPriority("Low");

	        } else if (imdbRating < 7) {
	            toBeUpdated.setPriority("Medium");

	        } else {
	            toBeUpdated.setPriority("High");
	        }

	    } else {

	        // Movie not found on IMDb
	        toBeUpdated.setRating(movie.getRating());

	        toBeUpdated.setPriority(
	            normalizePriority(movie.getPriority())
	        );
	    }

	    // Save updated movie
	    movieRepo.save(toBeUpdated);

	    // Save activity
	    activityService.saveActivity(
	        new Activity(
	            "✏️",
	            "Movie Updated",
	            user.getFullName()
	                + " updated \""
	                + toBeUpdated.getTitle()
	                + "\".",
	            user
	        )
	    );
	}
	
	
	
	// Delete Movie by id
	public void deleteMovieById(Integer id) {

	    Movie movie = getMovieById(id);

	    if (movie == null) {
	        throw new RuntimeException("Movie not found");
	    }

	    Authentication authentication =
	            SecurityContextHolder.getContext().getAuthentication();

	    CustomUserDetails customUser =
	            (CustomUserDetails) authentication.getPrincipal();

	    User user = customUser.getUser();

	    // Check ownership
	    if (!movie.getUser().getId().equals(user.getId())) {
	        throw new RuntimeException("Access Denied");
	    }

	    // Store title before deleting
	    String movieTitle = movie.getTitle();

	    // Delete movie
	    movieRepo.delete(movie);

	    // Create activity and associate it with the user
	    activityService.saveActivity(
	        new Activity(
	            "🗑️",
	            "Movie Deleted",
	            user.getFullName()
	                + " deleted \"" + movieTitle + "\".",
	            user
	        )
	    );
	}
	
	private String normalizePriority(String priority) {

	    if (priority == null || priority.trim().isEmpty()) {
	        return "Low";
	    }

	    priority = priority.trim().toLowerCase();

	    switch (priority) {

	        case "l":
	        case "low":
	            return "Low";

	        case "m":
	        case "med":
	        case "medium":
	            return "Medium";

	        case "h":
	        case "high":
	            return "High";

	        default:
	            return "Low";
	    }
	}
	
	public List<Movie> getAllMovies1() {
	    return movieRepo.findAll();
	}

	public long getTotalMovies() {
	    return movieRepo.count();
	}
	
	public List<Movie> getMoviesByUser(User user) {
	    return movieRepo.findByUser(user);
	}
	
	// Delete all movies belonging to a specific user
	public void deleteMoviesByUser(User user) {
	    movieRepo.deleteAll(movieRepo.findByUser(user));
	}
		
}
