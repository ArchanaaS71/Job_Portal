package com.ey.applicationservice.response;

import java.time.LocalDateTime;

import com.ey.applicationservice.enums.ApplicationStatus;


public class ApplicationResponse {
	private Long id;
	private Long jobId;
	private Long jobSeekerId;	
	private ApplicationStatus status;
	private LocalDateTime appliedAt;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getJobId() {
		return jobId;
	}
	public void setJobId(Long jobId) {
		this.jobId = jobId;
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
	

}
