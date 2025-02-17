package com.example.case_module6.repository;

import com.example.case_module6.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByTable_Id(Long tableId);
}
