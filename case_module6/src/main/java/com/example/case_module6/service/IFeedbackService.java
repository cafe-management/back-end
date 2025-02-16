package com.example.case_module6.service;

import com.example.case_module6.model.Feedback;

import java.time.LocalDateTime;
import java.util.List;

public interface IFeedbackService {
    Feedback createFeedback(Feedback feedback);
    List<Feedback> getAllFeedback();
    Feedback getFeedbackById(Long id);
    List<Feedback> getFeedbacksByDate(LocalDateTime start, LocalDateTime end);
}