package com.example.case_module6.repository;


import com.example.case_module6.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FeedBackRepository extends JpaRepository<Feedback,Long> {
    List<Feedback>findByDateFeedbackBetween(LocalDateTime start, LocalDateTime end);
}