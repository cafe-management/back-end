package com.example.case_module6.service.implement;

import com.example.case_module6.model.Cart;
import com.example.case_module6.model.Customer;
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

    @Autowired
    private EmailService emailService;

    @Override
    public Feedback createFeedback(Feedback feedback) {
        feedback.setDateFeedback(LocalDateTime.now());
        // Nếu có tableId từ phía client, đảm bảo đã set vào feedback trước khi gọi hàm này
        Feedback savedFeedback = feedBackRepository.save(feedback);
        Customer customer = savedFeedback.getCustomer();
        String customerEmail = customer.getEmail();
        String customerName = customer.getNameCustomer();
        Notification notification = new Notification();

        // Sửa lại thông báo để hiển thị rõ rằng bàn có feedback
        String tableInfo = (savedFeedback.getTableId() != null)
                ? "Bàn: #" + savedFeedback.getTableId() + " có feedback mới."
                : "Có feedback mới.";

        notification.setContent(tableInfo);
        notification.setDateNote(LocalDateTime.now());
        notificationService.save(notification);
        messagingTemplate.convertAndSend("/topic/notifications", notification);

        if (customerEmail != null && !customerEmail.isEmpty()) {
            emailService.sendThankYouEmail(customerName, customerEmail, savedFeedback.getId());
        }

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