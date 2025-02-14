package com.example.case_module6.service;

import com.example.case_module6.model.Notification;

import java.util.List;

public interface INotificationService {
    List<Notification> getAll();
    Notification save(Notification notification);
    Notification findById(Long id);
    void delete(Long id);
}
