package com.ey.jobportal.controller;

import java.util.List;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
 
import com.ey.jobportal.dto.response.JobApplicationResponse;
import com.ey.jobportal.dto.response.UserResponse;
import com.ey.jobportal.security.config.CurrentUserUtil;
import com.ey.jobportal.service.JobApplicationService;
import com.ey.jobportal.service.UserService;
 
@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {
 
    private static final Logger logger =
            LoggerFactory.getLogger(JobApplicationController.class);
 
    private final JobApplicationService jobApplicationService;
    private final UserService userService;
 
    public JobApplicationController(JobApplicationService jobApplicationService,
                                    UserService userService) {
        this.jobApplicationService = jobApplicationService;
        this.userService = userService;
    }
 
   
    @PostMapping("/apply/{jobId}")
    public ResponseEntity<JobApplicationResponse> applyForJob(
            @PathVariable Long jobId) {
 
        String email = CurrentUserUtil.getCurrentUserEmail();
        UserResponse user = userService.getUserByEmail(email);
 
        logger.info("User {} applying for job {}", user.getId(), jobId);
 
        JobApplicationResponse response =
                jobApplicationService.applyForJob(user.getId(), jobId);
 
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
 
  
    @DeleteMapping("/{applicationId}")
    public ResponseEntity<Void> withdrawApplication(
            @PathVariable Long applicationId) {
 
        logger.info("Withdrawing application {}", applicationId);
 
        jobApplicationService.withdrawApplication(applicationId);
        return ResponseEntity.noContent().build();
    }
 
   
    @GetMapping("/my")
    public ResponseEntity<List<JobApplicationResponse>> getMyApplications() {
 
        String email = CurrentUserUtil.getCurrentUserEmail();
        UserResponse user = userService.getUserByEmail(email);
 
        logger.info("Fetching applications for user {}", user.getId());
 
        return ResponseEntity.ok(
                jobApplicationService.getApplicationsByUser(user.getId()));
    }
 
    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<JobApplicationResponse>> getApplicationsByJob(
            @PathVariable Long jobId) {
 
        logger.info("Fetching applications for job {}", jobId);
 
        return ResponseEntity.ok(
                jobApplicationService.getApplicationsByJob(jobId));
    }
 
    @PutMapping("/{applicationId}/status")
    public ResponseEntity<Void> updateApplicationStatus(
            @PathVariable Long applicationId,
            @RequestParam String status) {
 
        logger.info("Updating application {} to status {}", applicationId, status);
 
        jobApplicationService.updateApplicationStatus(applicationId, status);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/has-applied/{jobId}")
    public ResponseEntity<Boolean> hasUserApplied(
            @PathVariable Long jobId) {
 
        String email = CurrentUserUtil.getCurrentUserEmail();
        UserResponse user = userService.getUserByEmail(email);
 
        boolean applied =
                jobApplicationService.hasUserApplied(user.getId(), jobId);
 
        return ResponseEntity.ok(applied);
    }
}