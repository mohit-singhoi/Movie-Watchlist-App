package com.example.mohit.watchlist.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendFeedbackResponse(
            String userEmail,
            String userName,
            String adminResponse) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(userEmail);

        message.setSubject(
                "Response to Your Feedback – Movie Watchlist"
        );

        String emailBody =
                "Dear " + userName + ",\n\n"

                + "Thank you for taking the time to share your "
                + "feedback with us.\n\n"

                + "We have reviewed your feedback regarding the "
                + "Movie Watchlist application. Your feedback are "
                + "valuable to us and help us improve the overall "
                + "user experience.\n\n"

                + "Admin's Response:\n"
                + "----------------------------------------\n"
                + adminResponse
                + "\n"
                + "----------------------------------------\n\n"

                + "If you have any further questions or suggestions, "
                + "please feel free to contact us.\n\n"

                + "Best regards,\n"
                + "Support Team";

        message.setText(emailBody);

        mailSender.send(message);
    }
}