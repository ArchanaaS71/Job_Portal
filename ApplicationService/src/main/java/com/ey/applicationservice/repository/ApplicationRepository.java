package com.ey.applicationservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ey.applicationservice.entity.JobApplication;

public interface ApplicationRepository extends JpaRepository<JobApplication , Long> {

	List<JobApplication> findByJobSeekerId(Long jobSeekerId);

	List<JobApplication> findByJobId(Long jobId);

	Optional<JobApplication> findByJobIdAndJobSeekerId(Long jobId, Long jobSeekerId);

}
