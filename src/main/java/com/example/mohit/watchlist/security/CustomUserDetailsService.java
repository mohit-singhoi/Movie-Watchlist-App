package com.example.mohit.watchlist.security;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.example.mohit.watchlist.entity.User;
import com.example.mohit.watchlist.repository.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    public CustomUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        return new CustomUserDetails(user);
    }
}