package com.example.case_module6.repository;

import com.example.case_module6.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrinkRepository extends JpaRepository<Drink, Long> {
    List<Drink> findByCategoryId(Long categoryId);
}
