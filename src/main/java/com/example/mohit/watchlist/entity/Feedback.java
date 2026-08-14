package com.example.mohit.watchlist.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    private Integer rating;

    @Column(length = 500)
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    // =====================================================
    // FEEDBACK RESPONSE HISTORY
    // =====================================================

    @OneToMany(
        mappedBy = "feedback",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<FeedbackResponse> responses = new ArrayList<>();


    // =====================================================
    // GETTERS AND SETTERS
    // =====================================================

    public Long getId() {
        return id;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    // =====================================================
    // RESPONSE HISTORY GETTER
    // =====================================================

    public List<FeedbackResponse> getResponses() {
        return responses;
    }


    public void setResponses(List<FeedbackResponse> responses) {
        this.responses = responses;
    }


    // =====================================================
    // ADD RESPONSE
    // =====================================================

    public void addResponse(FeedbackResponse response) {

        responses.add(response);

        response.setFeedback(this);
    }


    // =====================================================
    // REMOVE RESPONSE
    // =====================================================

    public void removeResponse(FeedbackResponse response) {

        responses.remove(response);

        response.setFeedback(null);
    }
}