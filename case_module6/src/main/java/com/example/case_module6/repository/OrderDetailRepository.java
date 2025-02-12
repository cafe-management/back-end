package com.example.case_module6.repository;

import com.example.case_module6.dto.BestSellingDrinkDTO;
import com.example.case_module6.model.OrderDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    @Query("SELECT new com.example.case_module6.dto.BestSellingDrinkDTO(d.nameDrinks, d.price, d.imgDrinks, SUM(od.quantity)) " +
            "FROM OrderDetail od JOIN od.drink d " +
            "GROUP BY d.id ORDER BY SUM(od.quantity) DESC")
    List<BestSellingDrinkDTO> findTop10BestSellingDrinks(Pageable pageable);
}
