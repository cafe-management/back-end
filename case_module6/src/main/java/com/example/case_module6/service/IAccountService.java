package com.example.case_module6.service;

import com.example.case_module6.model.Account;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

public interface IAccountService {
    boolean validateLogin(String username, String password);
    boolean changePassword(String userName, String oldPassword, String newPassword, String oldPasswordRaw);
    String getRoleIdByUsername(String username);
    Map<String, Object> forgotPassword(String emailOrUsername);
    Map<String, Object> verifyOtp(String emailOrUsername, String otp);
    Map<String,Object> newPassword(String emailOrUsername, String password);
}
