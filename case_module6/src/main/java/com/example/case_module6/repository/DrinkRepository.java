package com.example.case_module6.repository;

import com.example.case_module6.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DrinkRepository extends JpaRepository<Drink, Long> {
    // Lấy danh sách đồ uống chưa bị xóa
    List<Drink> findByIsDeletedFalse();

    // Tìm kiếm theo id chỉ với những đồ uống chưa bị xóa
    Optional<Drink> findByIdAndIsDeletedFalse(Long id);

    // Lấy đồ uống theo category mà chưa bị xóa
    List<Drink> findByCategoryIdAndIsDeletedFalse(Long categoryId);

    List<Drink> findByIsNewTrueAndIsDeletedFalse();
}
