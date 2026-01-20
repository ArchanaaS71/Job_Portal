package com.ey.jobportal.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
 
import org.springframework.stereotype.Service;
 
import com.ey.jobportal.dto.response.JobApplicationResponse;
import com.ey.jobportal.entity.Job;
import com.ey.jobportal.entity.JobApplication;
import com.ey.jobportal.entity.User;
import com.ey.jobportal.mapper.JobApplicationMapper;
import com.ey.jobportal.repository.JobApplicationRepository;
import com.ey.jobportal.repository.JobRepository;
import com.ey.jobportal.repository.UserRepository;

 
@Service
public class JobApplicationServiceImpl implements JobApplicationService {
 
    private final JobApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;
 
    public JobApplicationServiceImpl(JobApplicationRepository applicationRepository,
                                     UserRepository userRepository,
                                     JobRepository jobRepository) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
    }
 
    @Override
    public JobApplicationResponse applyForJob(Long userId, Long jobId) {
 
        if (applicationRepository.findByUserIdAndJobId(userId, jobId).isPresent()) {
            throw new RuntimeException("User already applied for this job");
        }
 
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
 
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));
 
        JobApplication application = JobApplicationMapper.toEntity(user, job);
        application.setAppliedDate(LocalDate.now());
 
        return JobApplicationMapper.toResponse(
                applicationRepository.save(application));
    }
 
    @Override
    public void withdrawApplication(Long applicationId) {
 
        JobApplication application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
 
        applicationRepository.delete(application);
    }
 
    @Override
    public List<JobApplicationResponse> getApplicationsByUser(Long userId) {
        return applicationRepository.findByUserId(userId)
                .stream()
                .map(JobApplicationMapper::toResponse)
                .collect(Collectors.toList());
    }
 
    @Override
    public List<JobApplicationResponse> getApplicationsByJob(Long jobId) {
        return applicationRepository.findByJobId(jobId)
                .stream()
                .map(JobApplicationMapper::toResponse)
                .collect(Collectors.toList());
    }
 
    @Override
    public void updateApplicationStatus(Long applicationId, String status) {
 
        JobApplication application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
 
        application.setStatus(status);
        application.setStatusUpdatedDate(LocalDate.now());
        applicationRepository.save(application);
    }
 
    @Override
    public boolean hasUserApplied(Long userId, Long jobId) {
        return applicationRepository.findByUserIdAndJobId(userId, jobId).isPresent();
    }
}
 
