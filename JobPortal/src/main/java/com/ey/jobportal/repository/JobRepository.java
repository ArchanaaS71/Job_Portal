package com.ey.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ey.jobportal.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long>{

    List<Job> findByActiveTrue();
    
    List<Job> findByLocationIgnoreCase(String location);
 
    List<Job> findByEmployerId(Long employerId);

}
