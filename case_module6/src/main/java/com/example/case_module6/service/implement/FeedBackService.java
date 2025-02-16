package com.example.case_module6.service.implement;

import com.example.case_module6.model.Feedback;
import com.example.case_module6.repository.FeedBackRepository;
import com.example.case_module6.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeedBackService implements IFeedbackService {
    @Autowired
    private FeedBackRepository feedBackRepository;

    @Override
    public Feedback createFeedback(Feedback feedback) {
        feedback.setDateFeedback(java.time.LocalDateTime.now());
        return feedBackRepository.save(feedback);
    }

    @Override
    public List<Feedback> getAllFeedback() {
        return feedBackRepository.findAll();
    }

    @Override
    public Feedback getFeedbackById(Long id) {
        return feedBackRepository.findById(id).get();
    }

    public List<Feedback> getFeedbacksByDate(LocalDateTime start, LocalDateTime end) {
        return feedBackRepository.findByDateFeedbackBetween(start, end);
    }
}
