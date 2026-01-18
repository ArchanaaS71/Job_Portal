package com.ey.jobservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ey.jobservice.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long>{

	List<Job> findByConsultantId(Long consultantId);
}
