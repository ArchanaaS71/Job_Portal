package com.ey.jobporal.service;
 
import static org.junit.jupiter.api.Assertions.*;
 
import java.util.List;
import java.util.Set;
import java.util.UUID;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
 
import com.ey.jobportal.dto.request.JobRequest;
import com.ey.jobportal.dto.response.JobApplicationResponse;
import com.ey.jobportal.entity.Employer;
import com.ey.jobportal.entity.Role;
import com.ey.jobportal.enums.RoleName;
import com.ey.jobportal.entity.User;
import com.ey.jobportal.repository.EmployerRepository;
import com.ey.jobportal.repository.JobApplicationRepository;
import com.ey.jobportal.repository.JobRepository;
import com.ey.jobportal.repository.RoleRepository;
import com.ey.jobportal.repository.UserRepository;
import com.ey.jobportal.service.JobApplicationService;
import com.ey.jobportal.service.JobService;
 
@SpringBootTest
@Transactional
class JobApplicationServiceTest {
 
    @Autowired
    private JobApplicationService jobApplicationService;
 
    @Autowired
    private JobService jobService;
 
    @Autowired
    private UserRepository userRepository;
 
    @Autowired
    private EmployerRepository employerRepository;
 
    @Autowired
    private RoleRepository roleRepository;
 
    @Autowired
    private JobRepository jobRepository;
 
    @Autowired
    private JobApplicationRepository jobApplicationRepository;
 
    private User user;
    private Employer employer;
    private Long jobId;
 
    @BeforeEach
    void setup() {
 
        String unique = UUID.randomUUID().toString();
 
     
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName(RoleName.ROLE_USER);
                    return roleRepository.save(role);
                });
 
        Role employerRole = roleRepository.findByName(RoleName.ROLE_EMPLOYER)
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName(RoleName.ROLE_EMPLOYER);
                    return roleRepository.save(role);
                });
 
        user = new User();
        user.setFullName("User " + unique);
        user.setEmail("user" + unique + "@gmail.com");
        user.setPassword("User@123");
        user.setActive(true);
        user.setRoles(Set.of(userRole));
        user = userRepository.save(user);
 
        
        employer = new Employer();
        employer.setCompanyName("Company " + unique);
        employer.setEmail("company" + unique + "@gmail.com");
        employer.setPassword("Company@123");
        employer.setActive(true);
        employer.setRoles(Set.of(employerRole));
        employer = employerRepository.save(employer);
 
       
        JobRequest jobRequest = new JobRequest();
        jobRequest.setTitle("Java Developer");
        jobRequest.setDescription("Spring Boot Developer");
        jobRequest.setLocation("Bangalore");
        jobRequest.setMinSalary(30000.0);
        jobRequest.setMaxSalary(60000.0);
        jobRequest.setEmploymentType("FULL_TIME");
        jobRequest.setWorkMode("ONSITE");
        jobRequest.setExperienceRequired(2);
 
        jobId = jobService
                .createJob(jobRequest, employer.getId())
                .getId();
    }
 
 
    @Test
    void testApplyForJobAndCheckApplied() {
 
        JobApplicationResponse response =
                jobApplicationService.applyForJob(user.getId(), jobId);
 
        assertNotNull(response);
        assertEquals(user.getId(), response.getUserId());
        assertEquals(jobId, response.getJobId());
 
        boolean hasApplied =
                jobApplicationService.hasUserApplied(user.getId(), jobId);
 
        assertTrue(hasApplied);
    }
 
  
 
    @Test
    void testGetApplicationsByUser() {
 
        jobApplicationService.applyForJob(user.getId(), jobId);
 
        List<JobApplicationResponse> applications =
                jobApplicationService.getApplicationsByUser(user.getId());
 
        assertNotNull(applications);
        assertEquals(1, applications.size());
    }
 
   
 
    @Test
    void testUpdateAndWithdrawApplication() {
 
        JobApplicationResponse response =
                jobApplicationService.applyForJob(user.getId(), jobId);
 
        jobApplicationService.updateApplicationStatus(
                response.getId(), "SHORTLISTED");
 
        JobApplicationResponse updated =
                jobApplicationRepository.findById(response.getId())
                        .map(app -> new JobApplicationResponse(
                                app.getId(),
                                app.getUser().getId(),
                                app.getJob().getId(),
                                app.getStatus(),
                                app.getAppliedDate(), null))
                        .orElse(null);
 
        assertNotNull(updated);
        assertEquals("SHORTLISTED", updated.getStatus());
 
        jobApplicationService.withdrawApplication(response.getId());
 
        assertFalse(
                jobApplicationRepository.findById(response.getId()).isPresent());
    }
}