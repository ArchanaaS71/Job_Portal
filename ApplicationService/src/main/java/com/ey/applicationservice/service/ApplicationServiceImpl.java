package com.ey.applicationservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.ey.applicationservice.dto.request.ApplicationRequest;
import com.ey.applicationservice.entity.JobApplication;
import com.ey.applicationservice.enums.ApplicationStatus;
import com.ey.applicationservice.mapper.ApplicationMapper;
import com.ey.applicationservice.repository.ApplicationRepository;
import com.ey.applicationservice.response.ApplicationResponse;

import jakarta.validation.Valid;

public class ApplicationServiceImpl implements ApplicationService{

	private final ApplicationRepository repository;
	 
	public ApplicationServiceImpl(ApplicationRepository repository) {
	        this.repository = repository;
	    }
	
	@Override
	public ApplicationResponse applyForJob(@Valid ApplicationRequest request) {
		 repository.findByJobIdAndJobSeekerId(request.getJobId(), request.getJobSeekerId())
	            .ifPresent(a -> { throw new RuntimeException("Already applied"); });
	 
	     JobApplication application =
	                ApplicationMapper.toEntity(request);
	 
	        return ApplicationMapper.toResponse(
	                repository.save(application));
	}

	@Override
	public ApplicationResponse getApplicationById(Long id) {
		 JobApplication app = repository.findById(id).orElseThrow(() ->
	                        new RuntimeException("Application not found"));
	        return ApplicationMapper.toResponse(app);
	}

	@Override
	public List<ApplicationResponse> getApplicationsByJob(Long jobId) {
		 return repository.findByJobId(jobId)
	                .stream()
	                .map(ApplicationMapper::toResponse)
	                .collect(Collectors.toList());
	}

	@Override
	public List<ApplicationResponse> getApplicationsByJobSeeker(Long jobSeekerId) {
		 return repository.findByJobSeekerId(jobSeekerId)
	                .stream()
	                .map(ApplicationMapper::toResponse)
	                .collect(Collectors.toList());
	}

	@Override
	public ApplicationResponse updateStatus(Long applicationId, ApplicationStatus status) {
		 JobApplication app = repository.findById(applicationId)
	                .orElseThrow(() ->new RuntimeException("Application not found"));
	 
	        app.setStatus(status);
	        app.setUpdatedAt(LocalDateTime.now());
	 
	        return ApplicationMapper.toResponse(
	                repository.save(app));
	}

}
