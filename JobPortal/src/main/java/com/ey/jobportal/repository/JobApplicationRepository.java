package com.ey.jobportal.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ey.jobportal.entity.*;
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long>{
	List<JobApplication> findByUserId(Long userId);
	 
    List<JobApplication> findByJobId(Long jobId);
 
    Optional<JobApplication> findByUserIdAndJobId(Long userId, Long jobId);
}
