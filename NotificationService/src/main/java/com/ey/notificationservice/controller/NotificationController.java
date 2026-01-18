package com.ey.notificationservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.notificationservice.dto.request.NotificationRequest;
import com.ey.notificationservice.entity.Notification;
import com.ey.notificationservice.service.NotificationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
	  private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);
	 
	  private final NotificationService service;
	 
	  public NotificationController(NotificationService service) {
	      this.service = service;
	  }
	  
	   @PostMapping("/send")
	    public ResponseEntity<Notification> send(@Valid @RequestBody NotificationRequest request) {
	 
	        logger.info("Sending notification to {}", request.getRecipient());
	 
	        return ResponseEntity
	                .status(HttpStatus.CREATED)
	                .body(service.sendNotification(request));
	    }
	   
	   @GetMapping("/all")
	    public ResponseEntity<List<Notification>> getAll() {
	        return ResponseEntity.ok(service.getAllNotifications());
	    }
	   
	   @GetMapping("/{id}")
	    public ResponseEntity<Notification> getById(@PathVariable Long id) {
	 
	        return ResponseEntity.ok(service.getNotificationById(id));
	    }
	 

}
