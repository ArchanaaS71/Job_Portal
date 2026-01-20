package com.ey.jobportal.service;

import java.util.List;

import com.ey.jobportal.dto.response.JobApplicationResponse;
 
public interface JobApplicationService {
 
	 	JobApplicationResponse applyForJob(Long userId, Long jobId);
	 
	    void withdrawApplication(Long applicationId);
	 
	    List<JobApplicationResponse> getApplicationsByUser(Long userId);
	 
	    List<JobApplicationResponse> getApplicationsByJob(Long jobId);
	 
	    void updateApplicationStatus(Long applicationId, String status);
	 
	    boolean hasUserApplied(Long userId, Long jobId);
	    }