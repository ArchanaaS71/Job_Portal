package com.ey.jobservice.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.jobservice.dto.request.JobRequest;
import com.ey.jobservice.dto.response.JobResponse;
import com.ey.jobservice.service.JobService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/jobs")
public class JobController {

		private static final Logger logger = LoggerFactory.getLogger(JobController.class);
	 
	    private final JobService jobService;
	 
	    public JobController(JobService jobService) {
	        this.jobService = jobService;
	    }
	
	    @PostMapping("/create")
	    public ResponseEntity<JobResponse> createJob( @Valid @RequestBody JobRequest request) {
	 
	        logger.info("Creating job for consultant {}", request.getConsultantId());
	        return ResponseEntity
	                .status(HttpStatus.CREATED)
	                .body(jobService.createJob(request));
	    }
	 
	    
	    @GetMapping("/{id}")
	    public ResponseEntity<JobResponse> getById(@PathVariable Long id) {
	        return ResponseEntity.ok(jobService.getJobById(id));
	    }
	
	    @GetMapping("/all")
	    public ResponseEntity<List<JobResponse>> getAll() {
	        return ResponseEntity.ok(jobService.getAllJobs());
	    }
	 
	  
	    @GetMapping("/consultant/{consultantId}")
	    public ResponseEntity<List<JobResponse>> getByConsultant(@PathVariable Long consultantId) {
	        return ResponseEntity.ok(jobService.getJobsByConsultant(consultantId));
	    }
	
	    @PutMapping("/{id}")
	    public ResponseEntity<JobResponse> update( @PathVariable Long id,  @Valid @RequestBody JobRequest request) {
	    	return ResponseEntity.ok(jobService.updateJob(id, request));
	    }
	 
	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> closeJob(@PathVariable Long id) {
	        jobService.closeJob(id);
	        return ResponseEntity.ok("Job closed successfully");
}
}
