package com.example.case_module6.service;

import com.example.case_module6.model.Account;

public interface IAccountService {
    boolean validateLogin(String username, String password);
    boolean changePassword(String oldPassword, String newPassword);
}
