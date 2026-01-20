package com.ey.jobportal.service;

import java.util.List;

import com.ey.jobportal.dto.request.JobRequest;
import com.ey.jobportal.dto.response.JobResponse;
 
public interface JobService {
 
    JobResponse createJob(JobRequest request, Long employerId);
 
    JobResponse getJobById(Long jobId);
 
    List<JobResponse> getAllActiveJobs();
 
    List<JobResponse> getJobsByLocation(String location);
 
    List<JobResponse> getJobsBySalary(double minSalary, double maxSalary);
 
    List<JobResponse> searchJobs(String keyword);
 
    void deactivateJob(Long jobId);
 
    void activateJob(Long jobId);
}
  
