package com.example.case_module6.repository;

import com.example.case_module6.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarttemRepository extends JpaRepository<CartItem, Long> {
}
