package com.example.mohit.watchlist.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.mohit.watchlist.dto.SignupRequest;
import com.example.mohit.watchlist.entity.Activity;
import com.example.mohit.watchlist.entity.User;
import com.example.mohit.watchlist.repository.ActivityRepo;
import com.example.mohit.watchlist.repository.FeedbackRepo;
import com.example.mohit.watchlist.repository.MovieRepo;
import com.example.mohit.watchlist.repository.UserRepo;

@Service
public class UserService {

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ActivityService activityService;

    private final MovieRepo movieRepo;
    private final FeedbackRepo feedbackRepo;
    private final ActivityRepo activityRepo;


    public UserService(
            UserRepo userRepository,
            PasswordEncoder passwordEncoder,
            ActivityService activityService,
            MovieRepo movieRepo,
            FeedbackRepo feedbackRepo,
            ActivityRepo activityRepo) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.activityService = activityService;

        this.movieRepo = movieRepo;
        this.feedbackRepo = feedbackRepo;
        this.activityRepo = activityRepo;
    }


    // ==============================
    // REGISTER USER
    // ==============================

    public User registerUser(SignupRequest request) {

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());

        user.setPassword(
            passwordEncoder.encode(request.getPassword())
        );

        user.setRole("ROLE_USER");


        // Save user
        User savedUser = userRepository.save(user);


        // Save activity
        activityService.saveActivity(
        	    new Activity(
        	        "👤",
        	        "New User Registered",
        	        user.getFullName()
        	            + " registered a new account.",
        	        user
        	    )
        	);


        return savedUser;
    }


    // ==============================
    // GET ALL USERS
    // ==============================

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }


    // ==============================
    // TOTAL USERS
    // ==============================

    public long getTotalUsers() {

        return userRepository.count();
    }


    // ==============================
    // GET USER BY ID
    // ==============================

    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElse(null);
    }


    // ==============================
    // DELETE USER
    // ==============================

    @Transactional
    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException("User not found"));

        // Delete dependent records first
        activityRepo.deleteByUser(user);
        feedbackRepo.deleteByUser(user);
        movieRepo.deleteByUser(user);

        // Finally delete user
        userRepository.delete(user);
    }
}