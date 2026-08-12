package com.example.mohit.watchlist.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String icon;

    private String action;

    private String description;

    private LocalDateTime createdAt;

    // Activity belongs to a user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    // ==============================
    // CONSTRUCTORS
    // ==============================

    public Activity() {
    }


    public Activity(String icon, String action, String description) {

        this.icon = icon;
        this.action = action;
        this.description = description;
        this.createdAt = LocalDateTime.now();
    }


    // Constructor with User
    public Activity(
            String icon,
            String action,
            String description,
            User user) {

        this.icon = icon;
        this.action = action;
        this.description = description;
        this.user = user;
        this.createdAt = LocalDateTime.now();
    }


    // ==============================
    // GETTERS & SETTERS
    // ==============================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}