package com.ey.jobportal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.ey.jobportal.entity.Employer;
 
public interface EmployerRepository extends JpaRepository<Employer, Long> {
 
    Optional<Employer> findByEmail(String email);
 
    boolean existsByEmail(String email);
}
