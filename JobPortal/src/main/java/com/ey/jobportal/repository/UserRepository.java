package com.ey.jobportal.repository;

import java.util.Optional;
 
import org.springframework.data.jpa.repository.JpaRepository;
 
import com.ey.jobportal.entity.User;
 
public interface UserRepository extends JpaRepository<User, Long> {
 
    Optional<User> findByEmail(String email);
 
    boolean existsByEmail(String email);
}