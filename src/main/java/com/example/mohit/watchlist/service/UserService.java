package com.example.mohit.watchlist.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.mohit.watchlist.dto.SignupRequest;
import com.example.mohit.watchlist.entity.User;
import com.example.mohit.watchlist.repository.UserRepo;

@Service
public class UserService {

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepository,
                       PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(SignupRequest request) {

        User user = new User();

        user.setFullName(request.getFullName());

        user.setEmail(request.getEmail());

        // Encrypt password using BCrypt
        user.setPassword(
            passwordEncoder.encode(request.getPassword())
        );

        // Every registered account is a normal USER
        user.setRole("ROLE_USER");

        return userRepository.save(user);
    }
}