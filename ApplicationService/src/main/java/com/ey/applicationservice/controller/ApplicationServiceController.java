package com.ey.applicationservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ey.applicationservice.dto.request.ApplicationRequest;
import com.ey.applicationservice.enums.ApplicationStatus;
import com.ey.applicationservice.response.ApplicationResponse;
import com.ey.applicationservice.service.ApplicationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/applications")
public class ApplicationServiceController {
	
	  private static final Logger logger = LoggerFactory.getLogger(ApplicationServiceController.class);
	 
	  private final ApplicationService service;
	 
	  public ApplicationServiceController(ApplicationService service) {
	        this.service = service;
	   }
	  @PostMapping("/apply")
	  public ResponseEntity<ApplicationResponse> apply(@Valid @RequestBody ApplicationRequest request) {
	 
		  logger.info("JobSeeker {} applying for job {}",request.getJobSeekerId(), request.getJobId());
	      return ResponseEntity
	                .status(HttpStatus.CREATED)
	                .body(service.applyForJob(request));
	    }
	  
	  @GetMapping("/{id}")
	    public ResponseEntity<ApplicationResponse> getById(@PathVariable Long id) {
	 	        return ResponseEntity.ok(service.getApplicationById(id));
	    }
	  
	   @GetMapping("/job/{jobId}")
	    public ResponseEntity<List<ApplicationResponse>> getByJob(@PathVariable Long jobId) {
	        return ResponseEntity.ok(service.getApplicationsByJob(jobId));
	    }
	   
	   @GetMapping("/jobseeker/{jobSeekerId}")
	    public ResponseEntity<List<ApplicationResponse>> getByJobSeeker(@PathVariable Long jobSeekerId) {
	 	        return ResponseEntity.ok(service.getApplicationsByJobSeeker(jobSeekerId));
	    }
	   
	   @PutMapping("/status/{applicationId}")
	    public ResponseEntity<ApplicationResponse> updateStatus(@PathVariable Long applicationId, @RequestParam ApplicationStatus status) {
	        return ResponseEntity.ok(service.updateStatus(applicationId, status));
	    }
	   
	 
	 

}
