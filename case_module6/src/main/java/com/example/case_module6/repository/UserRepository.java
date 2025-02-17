package com.example.case_module6.repository;

import com.example.case_module6.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
        boolean existsByAccount_UserName(String username);
        boolean existsByEmail(String email);
        User findByEmail(String email);
}
