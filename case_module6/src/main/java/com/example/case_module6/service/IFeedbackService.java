package com.example.case_module6.service;

import com.example.case_module6.model.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface IFeedbackService {
    Feedback createFeedback(Feedback feedback);
    Page<Feedback> getAllFeedback(Pageable pageable);
    Feedback getFeedbackById(Long id);
    Page<Feedback> getFeedbacksByDate(LocalDateTime start, LocalDateTime end, Pageable pageable);
}