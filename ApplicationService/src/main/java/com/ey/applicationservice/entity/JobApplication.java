package com.ey.applicationservice.entity;

import java.time.LocalDateTime;

import com.ey.applicationservice.enums.ApplicationStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "job_applications", uniqueConstraints = {@UniqueConstraint(columnNames = {"jobId","jobSeekerId"})})
public class JobApplication {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private Long jobId;
	@NotNull
	private Long jobSeekerId;	
	@Enumerated(EnumType.STRING)
	private ApplicationStatus status=  ApplicationStatus.APPLIED;
	private LocalDateTime appliedAt = LocalDateTime.now();
	private LocalDateTime updatedAt;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getJobSeekerId() {
		return jobSeekerId;
	}
	public void setJobSeekerId(Long jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}
	public ApplicationStatus getStatus() {
		return status;
	}
	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}
	public LocalDateTime getAppliedAt() {
		return appliedAt;
	}
	public void setAppliedAt(LocalDateTime appliedAt) {
		this.appliedAt = appliedAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Long getJobId() {
		return jobId;
	}
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	
	
	
}
