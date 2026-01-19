package com.ey.resumeservice.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ey.resumeservice.response.ResumeResponse;
import com.ey.resumeservice.service.ResumeService;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {
	  private static final Logger logger = LoggerFactory.getLogger(ResumeController.class);
	 
	  private final ResumeService resumeService;
	 
	  public ResumeController(ResumeService resumeService) {
	        this.resumeService = resumeService;
	    }
	  
	  @PostMapping("/upload/{jobSeekerId}")
	    public ResponseEntity<ResumeResponse> uploadResume( @PathVariable Long jobSeekerId, @RequestParam("file") MultipartFile file) {
	 
	        logger.info("Uploading resume for jobSeeker {}", jobSeekerId);
	 
	        return ResponseEntity
	                .status(HttpStatus.CREATED)
	                .body(resumeService.uploadResume(jobSeekerId, file));
	    }
	  
	  @GetMapping("/download/{jobSeekerId}")
	  public ResponseEntity<byte[]> downloadResume(@PathVariable Long jobSeekerId) {
	      byte[] fileData = resumeService.downloadResume(jobSeekerId);
	      var resume = resumeService.getResumeDetails(jobSeekerId);
	   
	      return ResponseEntity.ok()
	              .headers(headers -> headers.setContentDispositionFormData(
	                      "attachment", resume.getFileName()))
	              .contentType(MediaType.parseMediaType(resume.getFileType()))
	              .body(fileData);
	  	}
	 
	    
	  @GetMapping("/details/{jobSeekerId}")
	  public ResponseEntity<ResumeResponse> getDetails(@PathVariable Long jobSeekerId) {
	      return ResponseEntity.ok(
	                resumeService.getResumeDetails(jobSeekerId));
	    }
	  

}
