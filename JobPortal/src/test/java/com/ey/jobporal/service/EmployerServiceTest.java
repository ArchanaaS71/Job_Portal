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
import com.ey.jobportal.dto.response.EmployerResponse;
import com.ey.jobportal.dto.response.JobResponse;
import com.ey.jobportal.entity.Employer;
import com.ey.jobportal.entity.Role;
import com.ey.jobportal.enums.RoleName;
import com.ey.jobportal.repository.EmployerRepository;
import com.ey.jobportal.repository.JobRepository;
import com.ey.jobportal.repository.RoleRepository;
import com.ey.jobportal.service.EmployerService;
import com.ey.jobportal.service.JobService;
 
@SpringBootTest
@Transactional
class EmployerServiceTest {
 
    @Autowired
    private EmployerService employerService;
 
    @Autowired
    private EmployerRepository employerRepository;
 
    @Autowired
    private JobService jobService;
 
    @Autowired
    private RoleRepository roleRepository;
 
    @Autowired
    private JobRepository jobRepository;
 
    private Employer employer;
 
    @BeforeEach
    void setup() {
 
        String unique = UUID.randomUUID().toString();
 
        Role employerRole = roleRepository.findByName(RoleName.ROLE_EMPLOYER)
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName(RoleName.ROLE_EMPLOYER);
                    return roleRepository.save(role);
                });
 
        employer = new Employer();
        employer.setCompanyName("Employer Co " + unique);
        employer.setEmail("employer" + unique + "@gmail.com");
        employer.setPassword("Employer@123");
        employer.setActive(true);
        employer.setRoles(Set.of(employerRole));
 
        employer = employerRepository.save(employer);
    }
 
    private JobRequest createValidJobRequest(String title) {
 
        JobRequest request = new JobRequest();
        request.setTitle(title);
        request.setDescription("Job description for " + title);
        request.setLocation("Chennai");
        request.setMinSalary(25000.0);
        request.setMaxSalary(50000.0);
        request.setEmploymentType("FULL_TIME");
        request.setWorkMode("ONSITE");
        request.setExperienceRequired(1);
 
        return request;
    }
 
 
 
    @Test
    void testGetEmployerById() {
 
        EmployerResponse response =
                employerService.getEmployerById(employer.getId());
 
        assertNotNull(response);
        assertEquals(employer.getId(), response.getId());
        assertEquals(employer.getEmail(), response.getEmail());
        assertTrue(response.isActive());
    }
 
 
 
    @Test
    void testGetJobsByEmployerAndCount() {
 
        jobService.createJob(
                createValidJobRequest("Java Developer"),
                employer.getId());
 
        jobService.createJob(
                createValidJobRequest("React Developer"),
                employer.getId());
 
        List<JobResponse> jobs =
                employerService.getJobsByEmployer(employer.getId());
 
        long count =
                employerService.countJobsByEmployer(employer.getId());
 
        assertNotNull(jobs);
        assertEquals(2, jobs.size());
        assertEquals(2, count);
    }
 
   
 
    @Test
    void testActivateAndDeactivateEmployer() {
 
        employerService.deactivateEmployer(employer.getId());
 
        boolean isActiveAfterDeactivate =
                employerService.isEmployerActive(employer.getId());
 
        assertFalse(isActiveAfterDeactivate);
 
        employerService.activateEmployer(employer.getId());
 
        boolean isActiveAfterActivate =
                employerService.isEmployerActive(employer.getId());
 
        assertTrue(isActiveAfterActivate);
    }
}
 