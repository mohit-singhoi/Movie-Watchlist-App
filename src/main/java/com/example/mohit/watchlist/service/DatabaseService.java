package com.example.mohit.watchlist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.annotation.Validated;

import com.example.mohit.watchlist.entity.Movie;
import com.example.mohit.watchlist.repository.MovieRepo;

import jakarta.validation.Valid;

@Validated
@Service

public class DatabaseService {
	
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
	
	// New Version 2
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

	    movieRepo.save(movie);
	}
	
	
	
	public List<Movie> getAllMovies() {
		// TODO Auto-generated method stub
		
		return movieRepo.findAll();

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

	    Movie toBeUpdated = getMovieById(id);

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
	    movieRepo.deleteById(id);
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
