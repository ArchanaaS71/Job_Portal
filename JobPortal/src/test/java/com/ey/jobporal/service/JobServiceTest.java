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
import com.ey.jobportal.dto.response.JobResponse;
import com.ey.jobportal.entity.Employer;
import com.ey.jobportal.entity.Role;
import com.ey.jobportal.enums.RoleName;
import com.ey.jobportal.repository.EmployerRepository;
import com.ey.jobportal.repository.JobRepository;
import com.ey.jobportal.repository.RoleRepository;
import com.ey.jobportal.service.JobService;
 
@SpringBootTest
@Transactional
class JobServiceTest {
 
    @Autowired
    private JobService jobService;
 
    @Autowired
    private EmployerRepository employerRepository;
 
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
        employer.setCompanyName("Test Company " + unique);
        employer.setEmail("company" + unique + "@gmail.com");
        employer.setPassword("Company@123");
        employer.setActive(true);
        employer.setRoles(Set.of(employerRole));
 
        employer = employerRepository.save(employer);
    }
 
    private JobRequest createValidJobRequest(String title) {
 
        JobRequest request = new JobRequest();
        request.setTitle(title);
        request.setDescription("Job description for " + title);
        request.setLocation("Bangalore");
        request.setMinSalary(30000.0);
        request.setMaxSalary(60000.0);
        request.setEmploymentType("FULL_TIME");
        request.setWorkMode("ONSITE");
        request.setExperienceRequired(2);
 
        return request;
    }
 
   
 
    @Test
    void testCreateAndGetJob() {
 
        JobRequest request = createValidJobRequest("Java Developer");
 
        JobResponse savedJob =
                jobService.createJob(request, employer.getId());
 
        assertNotNull(savedJob);
        assertNotNull(savedJob.getId());
        assertEquals("Java Developer", savedJob.getTitle());
 
        JobResponse fetchedJob =
                jobService.getJobById(savedJob.getId());
 
        assertEquals(savedJob.getId(), fetchedJob.getId());
        assertEquals("Java Developer", fetchedJob.getTitle());
        assertTrue(fetchedJob.isActive());
    }
 
   
 
    @Test
    void testGetAllActiveJobs() {
 
        jobService.createJob(
                createValidJobRequest("Backend Developer"),
                employer.getId());
 
        jobService.createJob(
                createValidJobRequest("Frontend Developer"),
                employer.getId());
 
        List<JobResponse> jobs = jobService.getAllActiveJobs();
 
        assertNotNull(jobs);
        assertTrue(jobs.size() >= 2);
    }
 
   
 
    @Test
    void testSearchJobs() {
 
        jobService.createJob(
                createValidJobRequest("Spring Boot Developer"),
                employer.getId());
 
        jobService.createJob(
                createValidJobRequest("Python Developer"),
                employer.getId());
 
        List<JobResponse> results =
                jobService.searchJobs("Spring");
 
        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertTrue(
                results.get(0).getTitle().contains("Spring"));
    }
}
 