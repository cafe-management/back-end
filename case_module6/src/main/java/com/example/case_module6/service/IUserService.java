package com.example.case_module6.service;

import com.example.case_module6.model.User;

import java.util.Optional;

public interface IUserService extends IService<User, Long>{
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    User getUserByUsername(String username);

}
