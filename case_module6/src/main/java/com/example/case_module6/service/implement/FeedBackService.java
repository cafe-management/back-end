package com.example.case_module6.service.implement;

import com.example.case_module6.model.Cart;
import com.example.case_module6.model.Feedback;
import com.example.case_module6.model.Notification;
import com.example.case_module6.repository.FeedBackRepository;
import com.example.case_module6.service.IFeedbackService;
import com.example.case_module6.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeedBackService implements IFeedbackService {
    @Autowired
    private FeedBackRepository feedBackRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private INotificationService notificationService;

    @Override
    public Feedback createFeedback(Feedback feedback) {
        feedback.setDateFeedback(LocalDateTime.now());
        Feedback savedFeedback = feedBackRepository.save(feedback);
        Notification notification = new Notification();
        notification.setContent("Có Feed Back Mới : # " + savedFeedback.getId());
        notification.setDateNote(LocalDateTime.now());
        notificationService.save(notification);
        messagingTemplate.convertAndSend("/topic/notifications", notification);
        return savedFeedback;
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