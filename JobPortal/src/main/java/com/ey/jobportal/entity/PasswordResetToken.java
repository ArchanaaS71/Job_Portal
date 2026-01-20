package com.ey.jobportal.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
 
@Entity
@Table(name = "password_reset_tokens")
public class PasswordResetToken {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(unique = true, nullable = false)
    private String token;
 
    @OneToOne
    private User user;
 
    private LocalDateTime expiryDate;
 
    private boolean used = false;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDateTime expiryDate) {
		this.expiryDate = expiryDate;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public PasswordResetToken(Long id, String token, User user, LocalDateTime expiryDate, boolean used) {
		super();
		this.id = id;
		this.token = token;
		this.user = user;
		this.expiryDate = expiryDate;
		this.used = used;
	}

	public PasswordResetToken() {
		super();
	}
 
    
}
 
