package com.ey.jobportal.controller;
 
import java.util.List;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
 
import com.ey.jobportal.dto.request.JobRequest;
import com.ey.jobportal.dto.response.JobResponse;
import com.ey.jobportal.service.JobService;
 
@RestController
@RequestMapping("/api/jobs")
public class JobController {
 
    private static final Logger logger = LoggerFactory.getLogger(JobController.class);
    private final JobService jobService;
 
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
 
    @PostMapping("/{employerId}")
    public ResponseEntity<JobResponse> createJob(@PathVariable Long employerId,
                                                 @RequestBody JobRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(jobService.createJob(request, employerId));
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<JobResponse> getJob(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }
 
    @GetMapping("/all")
    public ResponseEntity<List<JobResponse>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllActiveJobs());
    }
 
    @GetMapping("/location")
    public ResponseEntity<List<JobResponse>> getJobsByLocation(@RequestParam String location) {
        return ResponseEntity.ok(jobService.getJobsByLocation(location));
    }
 
    @GetMapping("/salary")
    public ResponseEntity<List<JobResponse>> getJobsBySalary(@RequestParam double min,
                                                             @RequestParam double max) {
        return ResponseEntity.ok(jobService.getJobsBySalary(min, max));
    }
 
    @GetMapping("/search")
    public ResponseEntity<List<JobResponse>> searchJobs(@RequestParam String keyword) {
        return ResponseEntity.ok(jobService.searchJobs(keyword));
    }
 
    @PutMapping("/{id}/activate")
    public ResponseEntity<Void> activateJob(@PathVariable Long id) {
        jobService.activateJob(id);
        return ResponseEntity.ok().build();
    }
 
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateJob(@PathVariable Long id) {
        jobService.deactivateJob(id);
        return ResponseEntity.ok().build();
    }
}
 