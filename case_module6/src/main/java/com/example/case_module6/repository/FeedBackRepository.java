package com.example.case_module6.repository;


import com.example.case_module6.model.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FeedBackRepository extends JpaRepository<Feedback,Long> {
    Page<Feedback> findAll(Pageable pageable);
    Page<Feedback> findByDateFeedbackBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);
}