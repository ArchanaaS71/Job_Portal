package com.ey.notificationservice.service;

import java.util.List;

import com.ey.notificationservice.dto.request.NotificationRequest;
import com.ey.notificationservice.entity.Notification;

import jakarta.validation.Valid;

public interface NotificationService {

	Notification sendNotification(@Valid NotificationRequest request);

	List<Notification> getAllNotifications();

	Notification getNotificationById(Long id);

}
