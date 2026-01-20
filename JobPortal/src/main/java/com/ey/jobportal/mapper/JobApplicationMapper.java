package com.ey.jobportal.mapper;

import com.ey.jobportal.dto.response.JobApplicationResponse;
import com.ey.jobportal.entity.Job;
import com.ey.jobportal.entity.JobApplication;
import com.ey.jobportal.entity.User;

public class JobApplicationMapper {
	  
	    public static JobApplication toEntity(User user, Job job) {
	 
	        JobApplication application = new JobApplication();
	        application.setUser(user);
	        application.setJob(job);
	        application.setStatus("APPLIED");
	        application.setAppliedDate(java.time.LocalDate.now());
	 
	        return application;
	    }
	 
	    public static JobApplicationResponse toResponse(JobApplication application) {
	 
	        if (application == null) {
	            return null;
	        }
	 
	        JobApplicationResponse response = new JobApplicationResponse();
	        response.setId(application.getId());
	        response.setUserId(application.getUser().getId());
	        response.setJobId(application.getJob().getId());
	        response.setStatus(application.getStatus());
	        response.setAppliedDate(application.getAppliedDate());
	        response.setStatusUpdatedDate(application.getStatusUpdatedDate());
	 
	        return response;
	    }

}
