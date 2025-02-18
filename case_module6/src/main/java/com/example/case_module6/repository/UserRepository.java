package com.example.case_module6.repository;

import com.example.case_module6.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
        Page<User> findAll(Pageable page);
        boolean existsByAccount_UserName(String username);
        boolean existsByEmail(String email);
        User findByAccount_UserName(String username);
}
