package com.ey.notificationservice.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ey.notificationservice.dto.request.NotificationRequest;
import com.ey.notificationservice.entity.Notification;
import com.ey.notificationservice.enums.NotificationType;
import com.ey.notificationservice.exception.NotificationNotFoundException;
import com.ey.notificationservice.repository.NotificationRepository;

import jakarta.validation.Valid;

@Service
public class NotificationServiceImpl implements NotificationService {

	private static final Logger logger =LoggerFactory.getLogger(NotificationServiceImpl.class);
    private final NotificationRepository repository;
 
    public NotificationServiceImpl(NotificationRepository repository) {
        this.repository = repository;
    }
    @Override
    public Notification sendNotification(@Valid NotificationRequest request) {
 
        Notification notification = new Notification();
        notification.setType(request.getType());
        notification.setRecipient(request.getRecipient());
        notification.setSubject(request.getSubject());
        notification.setMessage(request.getMessage());

        if (request.getType() == NotificationType.EMAIL) {
            logger.info("Email sent to {}", request.getRecipient());
        } else {
            logger.info("SMS sent to {}", request.getRecipient());
        }
 
        notification.setSent(true);
        notification.setSentAt(LocalDateTime.now());
 
        return repository.save(notification);
    }
 
    @Override
    public List<Notification> getAllNotifications() {
        return repository.findAll();
    }
 
    @Override
    public Notification getNotificationById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new NotificationNotFoundException(
                                "Notification not found with id " + id));
    }
	
}
