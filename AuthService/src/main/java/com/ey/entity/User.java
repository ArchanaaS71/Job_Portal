package com.ey.entity;

import java.time.LocalDateTime;

import com.ey.enums.AccountStatus;
import com.ey.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String mail;
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;
	
	@Enumerated(EnumType.STRING)
	private AccountStatus status;
	
	private LocalDateTime createdAt;
	private LocalDateTime UpdatedAt;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public AccountStatus getStatus() {
		return status;
	}
	public void setStatus(AccountStatus status) {
		this.status = status;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return UpdatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		UpdatedAt = updatedAt;
	}
	public User() {
		super();
	}
	public User(Long id, String mail, String password, Role role, AccountStatus status, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.mail = mail;
		this.password = password;
		this.role = role;
		this.status = status;
		this.createdAt = createdAt;
		UpdatedAt = updatedAt;
	}
	
	
	
	
}
