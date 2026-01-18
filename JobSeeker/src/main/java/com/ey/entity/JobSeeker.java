package com.ey.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="JobSeeker_profiles")
public class JobSeeker {
	
	@Id
	@Column(name="user_id")
	private Long userId;
	
	private String fullName;
	private String phone;
	private String location;
	private String skills;
	//private Long profileResumeId;
	private Double totalExperience;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public Double getTotalExperience() {
		return totalExperience;
	}
	public void setTotalExperience(Double totalExperience) {
		this.totalExperience = totalExperience;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public JobSeeker(Long userId, String fullName, String phone, String location, String skills, Double totalExperience,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.phone = phone;
		this.location = location;
		this.skills = skills;
		this.totalExperience = totalExperience;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public JobSeeker() {
		super();
	}
	
}
