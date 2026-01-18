package com.ey.resumeservice.service;

import org.springframework.web.multipart.MultipartFile;

import com.ey.resumeservice.response.ResumeResponse;

public interface ResumeService {

	ResumeResponse uploadResume(Long jobSeekerId, MultipartFile file);

	byte[] downloadResume(Long jobSeekerId);

	ResumeResponse getResumeDetails(Long jobSeekerId);
	

}
