package com.ey.jobportal.service;
 

import java.util.List;
import java.util.stream.Collectors;
 
import org.springframework.stereotype.Service;
 
import com.ey.jobportal.dto.request.JobRequest;
import com.ey.jobportal.dto.response.JobResponse;
import com.ey.jobportal.entity.Employer;
import com.ey.jobportal.entity.Job;
import com.ey.jobportal.mapper.JobMapper;
import com.ey.jobportal.repository.EmployerRepository;
import com.ey.jobportal.repository.JobRepository;

 
@Service
public class JobServiceImpl implements JobService {
 
    private final JobRepository jobRepository;
    private final EmployerRepository employerRepository;
 
    public JobServiceImpl(JobRepository jobRepository,
                          EmployerRepository employerRepository) {
        this.jobRepository = jobRepository;
        this.employerRepository = employerRepository;
    }
 
    @Override
    public JobResponse createJob(JobRequest request, Long employerId) {
 
        Employer employer = employerRepository.findById(employerId)
                .orElseThrow(() -> new RuntimeException("Employer not found"));
 
        Job job = JobMapper.toEntity(request);
        job.setEmployer(employer);
 
        return JobMapper.toResponse(jobRepository.save(job));
    }
 
    @Override
    public JobResponse getJobById(Long jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        return JobMapper.toResponse(job);
    }
 
    @Override
    public List<JobResponse> getAllActiveJobs() {
        return jobRepository.findByActiveTrue()
                .stream()
                .map(JobMapper::toResponse)
                .collect(Collectors.toList());
    }
 
    @Override
    public List<JobResponse> getJobsByLocation(String location) {
        return jobRepository.findByLocationIgnoreCase(location)
                .stream()
                .map(JobMapper::toResponse)
                .collect(Collectors.toList());
    }
 
    @Override
    public List<JobResponse> getJobsBySalary(double minSalary, double maxSalary) {
        return jobRepository.findAll()
                .stream()
                .filter(j -> j.getMinSalary() >= minSalary && j.getMaxSalary() <= maxSalary)
                .map(JobMapper::toResponse)
                .collect(Collectors.toList());
    }
 
    @Override
    public List<JobResponse> searchJobs(String keyword) {
        return jobRepository.findAll()
                .stream()
                .filter(j ->
                        j.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                        j.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .map(JobMapper::toResponse)
                .collect(Collectors.toList());
    }
 
    @Override
    public void deactivateJob(Long jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        job.setActive(false);
        jobRepository.save(job);
    }
 
    @Override
    public void activateJob(Long jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        job.setActive(true);
        jobRepository.save(job);
    }
}