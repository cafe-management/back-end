package com.example.case_module6.controller;

import com.example.case_module6.model.Notification;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class PaymentNotificationController {

    @MessageMapping("/paymentNotification")
    @SendTo("/topic/notifications")
    public Notification processPaymentNotification(String messageJson) {
        Notification notification = new Notification();
        notification.setContent(messageJson);
        notification.setDateNote(LocalDateTime.now());
        return notification;
    }
}
