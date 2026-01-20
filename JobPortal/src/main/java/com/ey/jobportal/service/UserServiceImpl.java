package com.ey.jobportal.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
 
import org.springframework.stereotype.Service;
 
import com.ey.jobportal.dto.response.UserResponse;
import com.ey.jobportal.entity.Skill;
import com.ey.jobportal.entity.User;
import com.ey.jobportal.mapper.UserMapper;
import com.ey.jobportal.repository.SkillRepository;
import com.ey.jobportal.repository.UserRepository;

 
@Service
public class UserServiceImpl implements UserService {
 
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;
 
    public UserServiceImpl(UserRepository userRepository,
                           SkillRepository skillRepository) {
        this.userRepository = userRepository;
        this.skillRepository = skillRepository;
    }
 
    @Override
    public UserResponse getUserById(Long id) {
        return UserMapper.toResponse(
                userRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("User not found")));
    }
 
    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toResponse)
                .collect(Collectors.toList());
    }
 
    @Override
    public UserResponse getUserByEmail(String email) {
        return UserMapper.toResponse(
                userRepository.findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("User not found")));
    }
 
    @Override
    public void deactivateUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setActive(false);
        userRepository.save(user);
    }
 
    @Override
    public void activateUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setActive(true);
        userRepository.save(user);
    }
 
    @Override
    public Set<String> getUserSkills(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getSkills()
                .stream()
                .map(Skill::getName)
                .collect(Collectors.toSet());
    }
 
    @Override
    public void addSkillToUser(Long userId, Long skillId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));
 
        user.getSkills().add(skill);
        userRepository.save(user);
    }
 
    @Override
    public void removeSkillFromUser(Long userId, Long skillId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
 
        user.getSkills().removeIf(skill -> skill.getId().equals(skillId));
        userRepository.save(user);
    }
}
 