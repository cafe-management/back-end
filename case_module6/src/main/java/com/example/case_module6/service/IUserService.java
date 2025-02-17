package com.example.case_module6.service;

import com.example.case_module6.model.User;

public interface IUserService extends IService<User, Long>{
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    User findByEmail(String email);
    boolean validateLogin(String email, String password);
}
