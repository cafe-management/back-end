package com.example.case_module6.controller;

import com.example.case_module6.model.User;
import com.example.case_module6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        Optional<User> user = userService.findByEmail(email);
        if (user.isPresent() && user.get().getAccount().getPassword().equals(password)) {
            return "Login successful";
        }
        return "Invalid email or password";
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email) {
        Optional<User> user = userService.findByEmail(email);
        if (user.isPresent()) {
            String token = userService.createPasswordResetToken(user.get());
            return "Password reset token sent to email";
        }
        return "Email not found";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        Optional<User> user = userService.findByResetToken(token);
        if (user.isPresent()) {
            User resetUser = user.get();
            resetUser.getAccount().setPassword(newPassword);
            resetUser.setResetToken(null);
            userService.save(resetUser);
            return "Password reset successful";
        }
        return "Invalid token";
    }
}