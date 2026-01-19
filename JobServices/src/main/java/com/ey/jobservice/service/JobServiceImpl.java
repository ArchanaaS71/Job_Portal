package com.ey.jobservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ey.jobservice.dto.request.JobRequest;
import com.ey.jobservice.dto.response.JobResponse;
import com.ey.jobservice.entity.Job;
import com.ey.jobservice.enums.JobStatus;
import com.ey.jobservice.mapper.JobMapper;
import com.ey.jobservice.repository.JobRepository;

import jakarta.validation.Valid;

@Service
public class JobServiceImpl implements JobService {
	   private final JobRepository jobRepository;
	   
	    public JobServiceImpl(JobRepository jobRepository) {
	        this.jobRepository = jobRepository;
	    }
	 
	    @Override
	    public JobResponse createJob(@Valid JobRequest reqeuest) {
	        Job job = JobMapper.toEntity(reqeuest);
	        return JobMapper.toResponse(jobRepository.save(job));
	    }
	 
	    @Override
	    public JobResponse getJobById(Long id) {
	        Job job = jobRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Job not found"));
	        return JobMapper.toResponse(job);
	    }
	 
	    @Override
	    public List<JobResponse> getAllJobs() {
	        return jobRepository.findAll()
	                .stream()
	                .map(JobMapper::toResponse)
	                .collect(Collectors.toList());
	    }
	 
	    @Override
	    public List<JobResponse> getJobsByConsultant(Long consultantId) {
	        return jobRepository.findByConsultantId(consultantId)
	                .stream()
	                .map(JobMapper::toResponse)
	                .collect(Collectors.toList());
	    }
	 
	    @Override
	    public JobResponse updateJob(Long id,@Valid JobRequest request) {
	        Job job = jobRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Job not found"));
	 
	        job.setTitle(request.getTitle());
	        job.setDescription(request.getDescription());
	        job.setLocation(request.getLocation());
	        job.setJobType(request.getJobType());
	        job.setExperience(request.getExperience());
	        job.setSalaryMin(request.getSalaryMin());
	        job.setSalaryMax(request.getSalaryMax());
	        job.setSkills(request.getSkills());
	        job.setCompanyName(request.getCompanyName());
	        job.setUpdatedAt(LocalDateTime.now());
	 
	        return JobMapper.toResponse(jobRepository.save(job));
	    }
	 
	    @Override
	    public void closeJob(Long id) {
	        Job job = jobRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Job not found"));
	        job.setStatus(JobStatus.CLOSED);
	        jobRepository.save(job);

}
}