package com.example.case_module6.controller;

import com.example.case_module6.model.Feedback;
import com.example.case_module6.service.IFeedbackservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/feedback")
public class FeedbackRestController {
    @Autowired
    private IFeedbackservice feedbackservice;
    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        return new ResponseEntity<>(feedbackservice.getFeedback(), HttpStatus.OK);
    }
}
