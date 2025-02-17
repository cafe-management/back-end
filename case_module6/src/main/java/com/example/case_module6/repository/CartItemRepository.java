package com.example.case_module6.repository;

import com.example.case_module6.model.CartItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query("SELECT c.drink, SUM(c.quantity) AS totalQuantity " +
            "FROM CartItem c " +
            "GROUP BY c.drink " +
            "ORDER BY totalQuantity DESC")
    List<Object[]> findTopProducts(Pageable pageable);
}
