package com.ey.userservices.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PasswordResetToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String token;
	private Long userId;
	private LocalDateTime expiryTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public LocalDateTime getExpiryTime() {
		return expiryTime;
	}
	public void setExpiryTime(LocalDateTime expiryTime) {
		this.expiryTime = expiryTime;
	}
	public PasswordResetToken(Long id, String token, Long userId, LocalDateTime expiryTime) {
		super();
		this.id = id;
		this.token = token;
		this.userId = userId;
		this.expiryTime = expiryTime;
	}
	public PasswordResetToken() {
		super();
	}
	
	
}
