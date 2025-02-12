package com.example.case_module6.service.implement;

import com.example.case_module6.model.Feedback;
import com.example.case_module6.repository.FeedBackRepository;
import com.example.case_module6.service.IFeedbackservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FeedbackService implements IFeedbackservice {
    @Autowired
    private FeedBackRepository feedBackRepository;
    @Override
    public List<Feedback> getFeedback() {
        return feedBackRepository.findAll();
    }
}
