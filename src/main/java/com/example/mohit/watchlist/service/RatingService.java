package com.example.mohit.watchlist.service;

import org.apache.catalina.util.URLEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import tools.jackson.databind.node.ObjectNode;

@Service
public class RatingService {
	
	//String apiUrl = "http://www.omdbapi.com/?i=tt3896198&apikey=e2540296";
	
	String apiUrl = "http://www.omdbapi.com/?apikey=e2540296&t=";
		    

	
	@SuppressWarnings("deprecation")

	public String getMovieRating(String title) {

	    try {

	        RestTemplate template = new RestTemplate();

	        String encodedTitle = java.net.URLEncoder.encode(
	                title,
	                java.nio.charset.StandardCharsets.UTF_8);

	        ResponseEntity<ObjectNode> response =
	                template.getForEntity(apiUrl + encodedTitle, ObjectNode.class);

	        ObjectNode jsonObject = response.getBody();

	        if (jsonObject == null) {
	            return null;
	        }

	        // Movie not found
	        if ("False".equalsIgnoreCase(jsonObject.path("Response").asText())) {
	            return null;
	        }

	        String rating = jsonObject.path("imdbRating").asText();

	        if (rating == null || rating.trim().isEmpty() || rating.equalsIgnoreCase("N/A")) {
	            return null;
	        }

	        return rating;

	    } catch (Exception e) {

	        System.out.println("OMDb Error : " + e.getMessage());

	        return null;
	    }
	}

}
