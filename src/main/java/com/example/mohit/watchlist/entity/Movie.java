package com.example.mohit.watchlist.entity;

import com.example.mohit.watchlist.entity.validations.Priority;
import com.example.mohit.watchlist.entity.validations.Rating;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Title is Mandatory Please Enter Movie Title!")
    private String title;
    
    
//    @Min(value = 5, message = "Rating must be at least 5")
//    @Max(value = 10, message = "Rating cannot exceed 10")
    
    @Rating
    private Float rating;
    
    
    private String source;
    
    //@NotBlank(message = "Enter Movie Priority")
    
    @Priority
	private String priority;
    
    @Size(max = 50, message = "Comment cannot exceed 50 characters")
    private String comment;
    
    
    public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}