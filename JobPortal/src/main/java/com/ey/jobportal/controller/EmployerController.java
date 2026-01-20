package com.ey.jobportal.controller;
 
import java.util.List;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
 
import com.ey.jobportal.dto.response.EmployerResponse;
import com.ey.jobportal.dto.response.JobResponse;
import com.ey.jobportal.service.EmployerService;
 
@RestController
@RequestMapping("/api/employer")
public class EmployerController {
 
    private static final Logger logger = LoggerFactory.getLogger(EmployerController.class);
    private final EmployerService employerService;
 
    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<EmployerResponse> getEmployer(@PathVariable Long id) {
        return ResponseEntity.ok(employerService.getEmployerById(id));
    }
 
    @GetMapping("/all")
    public ResponseEntity<List<EmployerResponse>> getAllEmployers() {
        return ResponseEntity.ok(employerService.getAllEmployers());
    }
 
    @GetMapping("/{id}/jobs")
    public ResponseEntity<List<JobResponse>> getJobsByEmployer(@PathVariable Long id) {
        return ResponseEntity.ok(employerService.getJobsByEmployer(id));
    }
 
    @PutMapping("/{id}/activate")
    public ResponseEntity<Void> activateEmployer(@PathVariable Long id) {
        employerService.activateEmployer(id);
        return ResponseEntity.ok().build();
    }
 
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateEmployer(@PathVariable Long id) {
        employerService.deactivateEmployer(id);
        return ResponseEntity.ok().build();
    }
 
    @GetMapping("/{id}/count-jobs")
    public ResponseEntity<Long> countJobs(@PathVariable Long id) {
        return ResponseEntity.ok(employerService.countJobsByEmployer(id));
    }
 
    @GetMapping("/{id}/is-active")
    public ResponseEntity<Boolean> isActive(@PathVariable Long id) {
        return ResponseEntity.ok(employerService.isEmployerActive(id));
    }
}
 