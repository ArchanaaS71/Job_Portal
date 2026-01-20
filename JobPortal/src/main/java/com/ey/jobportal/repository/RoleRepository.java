package com.ey.jobportal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.ey.jobportal.entity.Role;
import com.ey.jobportal.enums.RoleName;
 
public interface RoleRepository extends JpaRepository<Role, Long> {
 
    Optional<Role> findByName(RoleName name);
}