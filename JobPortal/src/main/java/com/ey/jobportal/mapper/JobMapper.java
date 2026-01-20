package com.ey.jobportal.mapper;

import com.ey.jobportal.dto.request.JobRequest;
import com.ey.jobportal.dto.response.JobResponse;
import com.ey.jobportal.entity.Job;


public class JobMapper {
	
	  public static Job toEntity(JobRequest request) {
		  
	        if (request == null) {
	            return null;
	        }
	 
	        Job job = new Job();
	        job.setTitle(request.getTitle());
	        job.setDescription(request.getDescription());
	        job.setLocation(request.getLocation());
	        job.setMinSalary(request.getMinSalary());
	        job.setMaxSalary(request.getMaxSalary());
	        job.setEmploymentType(request.getEmploymentType());
	        job.setWorkMode(request.getWorkMode());
	        job.setExperienceRequired(request.getExperienceRequired());
	        job.setApplicationDeadline(request.getApplicationDeadline());
	        job.setActive(true);
	 
	        return job;
	    }
	
	    public static JobResponse toResponse(Job job) {
	 
	        if (job == null) {
	            return null;
	        }
	 
	        JobResponse response = new JobResponse();
	        response.setId(job.getId());
	        response.setTitle(job.getTitle());
	        response.setDescription(job.getDescription());
	        response.setLocation(job.getLocation());
	        response.setMinSalary(job.getMinSalary());
	        response.setMaxSalary(job.getMaxSalary());
	        response.setEmploymentType(job.getEmploymentType());
	        response.setWorkMode(job.getWorkMode());
	        response.setExperienceRequired(job.getExperienceRequired());
	        response.setPostedDate(job.getPostedDate());
	        response.setActive(job.isActive());
	 
	        return response;
	    }

}
