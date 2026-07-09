package com.example.mohit.watchlist.controller;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.mohit.watchlist.entity.Movie;
import com.example.mohit.watchlist.service.DatabaseService;


@Controller
public class MovieController {

	@Autowired
	DatabaseService databaseService;
	
    @GetMapping("/watchlistItemForm")
    public ModelAndView showWatchListForm(@RequestParam(required = false) Integer id) {
    	
    	System.out.println(id);
        String viewName = "watchlistItem";

        Map<String, Object> model = new HashMap<>();
        
        if(id == null) {
        	model.put("watchlistItem", new Movie());
        } else {
        	model.put("watchlistItem", databaseService.getMovieById(id));
        }

//        Movie dummyMovie = new Movie();
//        dummyMovie.setTitle("Dummy Movie");
//        dummyMovie.setRating(2);
//        dummyMovie.setPriority("Medium");
//        dummyMovie.setComment("Best Movie");
//
//        model.put("watchlistItem", dummyMovie);
//        
        //model.put("watchlistItem", new Movie());


        return new ModelAndView(viewName, model);
    }
    
    // Old Version
    
//    @PostMapping("/watchlistItemForm")
//    public ModelAndView submitWatchlistForm(@Valid Movie  movie, BindingResult bindingResult) {
//    	
//    	if(bindingResult.hasErrors()) {
//    		// if errors are there, redisplay the form and let user entail detail again!
//    		return new ModelAndView("watchlistItem");
//    	}
//    	
//    	/*
//    	 if(id == null){
//    	 create new movie
//    	 } else {
//    	   update
//    	 }
//    	 */
//    	
//    	Integer id = movie.getId();
//    	if(id == null) {
//    		databaseService.create(movie);
//    	} else {
//    		databaseService.update(movie, id);
//    	}
//    	
//    	
//    	RedirectView rd = new RedirectView();
//    	rd.setUrl("/watchlist");
//    	
//    	return new ModelAndView(rd);
//
//    	
//    }
    
    
    //New Version 
    @PostMapping("/watchlistItemForm")
    public ModelAndView submitWatchlistForm(
            @Valid @ModelAttribute("watchlistItem") Movie movie,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("watchlistItem");
            mav.addObject("watchlistItem", movie);
            return mav;
        }

        try {
            // Create method internally calls OMDb API
            if (movie.getId() == null) {
                databaseService.create(movie);
            } else {
                databaseService.update(movie, movie.getId());
            }

        } catch (Exception e) {

            // If OMDb API fails, still save the movie
            System.out.println("IMDb movie not found. Saving manually...");

            movie.setSource("N/A");

            if (movie.getId() == null) {
                databaseService.save(movie);      // or repository.save(movie)
            } else {
                databaseService.update(movie, movie.getId());
            }
        }

        return new ModelAndView(new RedirectView("/watchlist"));
    }
    
    
    
    @GetMapping("/watchlist")
    public ModelAndView getWatchlist() {
		// TODO Auto-generated method stub

    	String viewName = "watchlist";
    	Map<String, Object> model = new HashMap<>();
    	List<Movie> movieList = databaseService.getAllMovies();
    	model.put("watchlistrows", movieList);
    	model.put("noofmovies", movieList.size());
    	return new ModelAndView(viewName, model);
	}
    
    
    
    @GetMapping("/")
    public String home() {
        return "index";   // Your home html page name
    }
    
    
    
    @GetMapping("/delete")
    public RedirectView deleteMovie(@RequestParam Integer id) {

        databaseService.deleteMovieById(id);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/watchlist");

        return redirectView;
    }
}