package com.ey.applicationservice.service;

import java.util.List;

import com.ey.applicationservice.dto.request.ApplicationRequest;
import com.ey.applicationservice.enums.ApplicationStatus;
import com.ey.applicationservice.response.ApplicationResponse;

import jakarta.validation.Valid;

public interface ApplicationService {

	ApplicationResponse applyForJob(@Valid ApplicationRequest request);

	ApplicationResponse getApplicationById(Long id);

	List<ApplicationResponse> getApplicationsByJob(Long jobId);

	List<ApplicationResponse> getApplicationsByJobSeeker(Long jobSeekerId);

	ApplicationResponse updateStatus(Long applicationId, ApplicationStatus status);

}
