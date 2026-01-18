package com.ey.applicationservice.mapper;

import com.ey.applicationservice.dto.request.ApplicationRequest;
import com.ey.applicationservice.entity.JobApplication;
import com.ey.applicationservice.response.ApplicationResponse;

public class ApplicationMapper {
	 public static JobApplication toEntity(ApplicationRequest request) {
	        JobApplication app = new JobApplication();
	        app.setJobId(request.getJobId());
	        app.setJobSeekerId(request.getJobSeekerId());
	        return app;
	    }
	 
	    public static ApplicationResponse toResponse(JobApplication app) {
	        ApplicationResponse response = new ApplicationResponse();
	        response .setId(app.getId());
	        response .setJobId(app.getJobId());
	        response .setJobSeekerId(app.getJobSeekerId());
	        response .setStatus(app.getStatus());
	        response .setAppliedAt(app.getAppliedAt());
	        return response ;
	    }

}
