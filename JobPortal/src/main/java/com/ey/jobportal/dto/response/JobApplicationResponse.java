package com.ey.jobportal.dto.response;

import java.time.LocalDate;

public class JobApplicationResponse {
 
    private Long id;
    private Long userId;
    private Long jobId;
    private String status;
    private LocalDate appliedDate;
    private LocalDate statusUpdatedDate;
	
	public JobApplicationResponse(Long id, Long userId, Long jobId, String status, LocalDate appliedDate,
			LocalDate statusUpdatedDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.jobId = jobId;
		this.status = status;
		this.appliedDate = appliedDate;
		this.statusUpdatedDate = statusUpdatedDate;
	}
	
	public JobApplicationResponse() {
		super();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getJobId() {
		return jobId;
	}
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getAppliedDate() {
		return appliedDate;
	}
	public void setAppliedDate(LocalDate appliedDate) {
		this.appliedDate = appliedDate;
	}
	public LocalDate getStatusUpdatedDate() {
		return statusUpdatedDate;
	}
	public void setStatusUpdatedDate(LocalDate statusUpdatedDate) {
		this.statusUpdatedDate = statusUpdatedDate;
	}
    
    
}
