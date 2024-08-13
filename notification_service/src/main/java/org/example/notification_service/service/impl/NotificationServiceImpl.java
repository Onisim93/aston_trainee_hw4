package org.example.notification_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.notification_service.model.Notification;
import org.example.notification_service.repository.NotificationRepository;
import org.example.notification_service.service.NotificationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Transactional
    public Notification create(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification update(Notification notification) {
        return null;
    }

    @Override
    public void delete(Notification notification) {

    }

    @Override
    public List<Notification> findAll() {
        return List.of();
    }

    @Override
    public Notification findById(UUID id) {
        return null;
    }

    @Override
    public boolean isExist(String id) {
        return notificationRepository.existsById(UUID.fromString(id));
    }

}
