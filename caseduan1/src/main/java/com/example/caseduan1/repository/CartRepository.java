package com.example.caseduan1.repository;

import com.example.caseduan1.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {


    List<Cart> findByUserId(Long userId);
}
