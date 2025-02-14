package com.example.case_module6.repository;

import com.example.case_module6.dto.BestSellingDrinkDTO;
import com.example.case_module6.model.CartItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query("SELECT new com.example.case_module6.dto.BestSellingDrinkDTO(d.id, d.nameDrinks, d.price, d.imgDrinks, SUM(ci.quantity)) " +
            "FROM CartItem ci JOIN ci.drink d " +
            "GROUP BY d.id ORDER BY SUM(ci.quantity) DESC")
    List<BestSellingDrinkDTO> findTopBestSellingDrinks(Pageable pageable);
}
