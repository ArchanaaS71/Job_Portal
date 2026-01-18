package com.ey.resumeservice.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.ey.resumeservice.entity.Resume;
import com.ey.resumeservice.exception.ResumeNotFoundException;
import com.ey.resumeservice.repository.ResumeRepository;
import com.ey.resumeservice.response.ResumeResponse;

public class ResumeServiceImpl implements ResumeService {

	private final ResumeRepository repository;
	 
    public ResumeServiceImpl(ResumeRepository repository) {
        this.repository = repository;
    }
    
	@Override
	public ResumeResponse uploadResume(Long jobSeekerId, MultipartFile file) {
		 try {
	            Resume resume = repository
	                    .findByJobSeekerId(jobSeekerId)
	                    .orElse(new Resume());
	 
	            resume.setJobSeekerid(jobSeekerId);
	            resume.setFileName(file.getOriginalFilename());
	            resume.setFileType(file.getContentType());
	            resume.setData(file.getBytes());
	 
	            Resume saved = repository.save(resume);
	 
	            ResumeResponse response = new ResumeResponse();
	            response.setId(saved.getId());
	            response.setJobSeekerId(saved.getJobSeekerid());
	            response.setFileName(saved.getFileName());
	            response.setFileType(saved.getFileType());
	            response.setUploadedAt(saved.getUploadedAt());
	 
	            return response;
	 
	        } catch (IOException e) {
	            throw new RuntimeException("Resume upload failed");
	        }
	    }
	@Override
	public byte[] downloadResume(Long jobSeekerId) {
		 Resume resume = repository.findByJobSeekerId(jobSeekerId)
	                .orElseThrow(() ->
	                        new ResumeNotFoundException("Resume not found"));
	        return resume.getData();
	}

	@Override
	public ResumeResponse getResumeDetails(Long jobSeekerId) {
		 Resume resume = repository.findByJobSeekerId(jobSeekerId)
	                .orElseThrow(() ->
	                        new ResumeNotFoundException("Resume not found"));
	 
	        ResumeResponse response = new ResumeResponse();
	        response.setId(resume.getId());
	        response.setJobSeekerId(resume.getJobSeekerid());
	        response.setFileName(resume.getFileName());
	        response.setFileType(resume.getFileType());
	        response.setUploadedAt(resume.getUploadedAt());
	 
	        return response;
	    }
	}


