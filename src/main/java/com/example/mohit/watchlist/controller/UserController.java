package com.example.mohit.watchlist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.mohit.watchlist.dto.SignupRequest;
import com.example.mohit.watchlist.service.UserService;

@Controller
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/signup")
    public String signupPage(Model model) {

        model.addAttribute("signupRequest", new SignupRequest());

        return "auth/signup";
    }


    @PostMapping("/signup")
    public String registerUser(
            @ModelAttribute("signupRequest") SignupRequest request,
            Model model) {

        try {

            userService.registerUser(request);

            return "redirect:/login";

        } catch (Exception e) {

            model.addAttribute("error", e.getMessage());
            model.addAttribute("signupRequest", request);

            return "auth/signup";
        }
    }

}