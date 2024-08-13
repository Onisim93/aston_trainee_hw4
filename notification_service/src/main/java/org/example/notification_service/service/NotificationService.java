package org.example.notification_service.service;

import org.example.notification_service.model.Notification;

import java.util.List;
import java.util.UUID;

public interface NotificationService {
    Notification create(Notification notification);
    Notification update(Notification notification);
    void delete(Notification notification);
    List<Notification> findAll();
    Notification findById(UUID id);

    boolean isExist(String id);
}
