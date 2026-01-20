package com.ey.jobportal.service;

import java.util.List;
import java.util.stream.Collectors;
 
import org.springframework.stereotype.Service;
 
import com.ey.jobportal.dto.response.EmployerResponse;
import com.ey.jobportal.dto.response.JobResponse;
import com.ey.jobportal.entity.Employer;
import com.ey.jobportal.mapper.EmployerMapper;
import com.ey.jobportal.mapper.JobMapper;
import com.ey.jobportal.repository.EmployerRepository;
import com.ey.jobportal.repository.JobRepository;

@Service
public class EmployerServiceImpl implements EmployerService {
 
    private final EmployerRepository employerRepository;
    private final JobRepository jobRepository;
 
    public EmployerServiceImpl(EmployerRepository employerRepository,
                               JobRepository jobRepository) {
        this.employerRepository = employerRepository;
        this.jobRepository = jobRepository;
    }
 
    @Override
    public EmployerResponse getEmployerById(Long id) {
        Employer employer = employerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employer not found"));
        return EmployerMapper.toResponse(employer);
    }
 
    @Override
    public List<EmployerResponse> getAllEmployers() {
        return employerRepository.findAll()
                .stream()
                .map(EmployerMapper::toResponse)
                .collect(Collectors.toList());
    }
 
    @Override
    public void deactivateEmployer(Long id) {
        Employer employer = employerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employer not found"));
        employer.setActive(false);
        employerRepository.save(employer);
    }
 
    @Override
    public void activateEmployer(Long id) {
        Employer employer = employerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employer not found"));
        employer.setActive(true);
        employerRepository.save(employer);
    }
 
    @Override
    public List<JobResponse> getJobsByEmployer(Long employerId) {
        return jobRepository.findByEmployerId(employerId)
                .stream()
                .map(JobMapper::toResponse)
                .collect(Collectors.toList());
    }
 
    @Override
    public long countJobsByEmployer(Long employerId) {
        return jobRepository.findByEmployerId(employerId).size();
    }
 
    @Override
    public boolean isEmployerActive(Long id) {
        Employer employer = employerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employer not found"));
        return employer.isActive();
    }
}