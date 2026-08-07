package com.example.mohit.watchlist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.mohit.watchlist.entity.User;
import com.example.mohit.watchlist.security.CustomUserDetails;

import com.example.mohit.watchlist.entity.Movie;
import com.example.mohit.watchlist.repository.MovieRepo;

import jakarta.validation.Valid;

@Validated
@Service

public class MovieServices {
	
	@Autowired
	MovieRepo movieRepo;
	
	@Autowired
	RatingService ratingService;
	
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
		return movieRepo.findById(id).get();
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

	  //  Movie toBeUpdated = getMovieById(id)

		Movie toBeUpdated = getMovieById(id);

		Authentication authentication =
		        SecurityContextHolder.getContext().getAuthentication();

		CustomUserDetails customUser =
		        (CustomUserDetails) authentication.getPrincipal();

		if (!toBeUpdated.getUser().getId()
		        .equals(customUser.getUser().getId())) {

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
	        toBeUpdated.setPriority(normalizePriority(movie.getPriority()));
	    }

	    movieRepo.save(toBeUpdated);
	}
	
	
	
	public void deleteMovieById(Integer id) {
	  
		//  movieRepo.deleteById(id);
		
		Movie movie = getMovieById(id);

		Authentication authentication =
		        SecurityContextHolder.getContext().getAuthentication();

		CustomUserDetails customUser =
		        (CustomUserDetails) authentication.getPrincipal();

		if (!movie.getUser().getId()
		        .equals(customUser.getUser().getId())) {

		    throw new RuntimeException("Access Denied");
		}

		movieRepo.delete(movie);
	}
	
	
	public void save(@Valid Movie movie) {
		// TODO Auto-generated method stub
		
		movieRepo.save(movie);
		
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

}
