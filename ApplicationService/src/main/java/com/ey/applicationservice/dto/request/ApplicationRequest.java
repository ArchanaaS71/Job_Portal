package com.ey.applicationservice.dto.request;

import jakarta.validation.constraints.NotNull;

public class ApplicationRequest {
	
	@NotNull
	private Long jobId;
	@NotNull
	private Long jobSeekerId;
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
	

}
