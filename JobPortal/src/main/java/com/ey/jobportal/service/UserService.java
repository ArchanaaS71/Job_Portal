package com.ey.jobportal.service;
 
import java.util.List;
 
import com.ey.jobportal.dto.response.UserResponse;

import java.util.Set;
 

 
public interface UserService {
 
    UserResponse getUserById(Long id);
 
    List<UserResponse> getAllUsers();
 
    UserResponse getUserByEmail(String email);
 
    void deactivateUser(Long id);
 
    void activateUser(Long id);
 
    Set<String> getUserSkills(Long userId);
 
    void addSkillToUser(Long userId, Long skillId);
 
    void removeSkillFromUser(Long userId, Long skillId);
}
 
  