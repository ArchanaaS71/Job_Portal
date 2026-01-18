package com.ey.jobservice.service;

import java.util.List;

import com.ey.jobservice.dto.request.JobRequest;
import com.ey.jobservice.dto.response.JobResponse;

import jakarta.validation.Valid;

public interface JobService {

	JobResponse createJob(@Valid JobRequest request);

	JobResponse getJobById(Long id);

	List<JobResponse> getAllJobs();

	List<JobResponse> getJobsByConsultant(Long consultantId);

	JobResponse updateJob(Long id, @Valid JobRequest request);

	void closeJob(Long id);

}
