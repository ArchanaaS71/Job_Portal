package com.ey.jobportal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.ey.jobportal.entity.Skill;
 
public interface SkillRepository extends JpaRepository<Skill, Long> {
 
    Optional<Skill> findByNameIgnoreCase(String name);
}
