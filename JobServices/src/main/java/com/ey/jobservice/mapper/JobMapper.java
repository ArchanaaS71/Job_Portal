package com.ey.jobservice.mapper;

import com.ey.jobservice.dto.request.JobRequest;
import com.ey.jobservice.dto.response.JobResponse;
import com.ey.jobservice.entity.Job;

public class JobMapper {
	public static Job toEntity(JobRequest request) {
        Job job = new Job();
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setLocation(request.getLocation());
        job.setJobType(request.getJobType());
        job.setExperience(request.getExperience());
        job.setSalaryMin(request.getSalaryMin());
        job.setSalaryMax(request.getSalaryMax());
        job.setSkills(request.getSkills());
        job.setConsultantId(request.getConsultantId());
        job.setCompanyName(request.getCompanyName());
        return job;
    }
 
    public static JobResponse toResponse(Job job) {
        JobResponse response = new JobResponse();
        response.setId(job.getId());
        response.setTitle(job.getTitle());
        response.setDescription(job.getDescription());
        response.setLocation(job.getLocation());
        response.setJobType(job.getJobType());
        response.setExperience(job.getExperience());
        response.setSalaryMin(job.getSalaryMin());
        response.setSalaryMax(job.getSalaryMax());
        response.setSkills(job.getSkills());
        response.setConsultantId(job.getConsultantId());
        response.setCompanyName(job.getCompanyName());
        response.setStatus(job.getStatus());
        return response;
    }

}
