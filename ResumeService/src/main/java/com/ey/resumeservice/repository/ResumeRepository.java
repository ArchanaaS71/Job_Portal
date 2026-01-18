package com.ey.resumeservice.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import  com.ey.resumeservice.entity.*;

public interface ResumeRepository extends JpaRepository<Resume, Long>{

	Optional<Resume> findByJobSeekerId(Long jobSeekerId);

}
