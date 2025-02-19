package com.example.case_module6.service.implement;

import com.example.case_module6.model.Notification;
import com.example.case_module6.repository.NotificationRepository;
import com.example.case_module6.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService implements INotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getAll() {
        return notificationRepository.findAll();
    }

    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification findById(Long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id: " + id));
    }

    @Override
    public void delete(Long id) {
        notificationRepository.deleteById(id);
    }
    @Override
    public void markAllAsSeen() {
        List<Notification> notifications = notificationRepository.findAll();
        for (Notification notification : notifications) {
            notification.setSeen(true);
            notificationRepository.save(notification);
        }
    }

}
