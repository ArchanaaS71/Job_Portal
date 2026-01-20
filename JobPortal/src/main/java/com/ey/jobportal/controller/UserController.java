package com.ey.jobportal.controller;
 
import java.util.List;
import java.util.Set;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
 
import com.ey.jobportal.dto.response.UserResponse;
import com.ey.jobportal.security.config.CurrentUserUtil;
import com.ey.jobportal.service.UserService;
 
@RestController
@RequestMapping("/api/user")
public class UserController {
 
    private static final Logger logger =
            LoggerFactory.getLogger(UserController.class);
 
    private final UserService userService;
 
    public UserController(UserService userService) {
        this.userService = userService;
    }
 
  
    @GetMapping("/profile")
    public ResponseEntity<UserResponse> getMyProfile() {
 
        String email = CurrentUserUtil.getCurrentUserEmail();
        logger.info("Fetching profile for user with email {}", email);
 
        return ResponseEntity.ok(
                userService.getUserByEmail(email));
    }
 
  
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable Long id) {
 
        logger.info("Fetching user by id {}", id);
        return ResponseEntity.ok(
                userService.getUserById(id));
    }
 
  
    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
 
        logger.info("Fetching all users");
        return ResponseEntity.ok(
                userService.getAllUsers());
    }
 
  
    @PutMapping("/activate")
    public ResponseEntity<Void> activateMyAccount() {
 
        String email = CurrentUserUtil.getCurrentUserEmail();
        UserResponse user = userService.getUserByEmail(email);
 
        logger.info("Activating user {}", user.getId());
        userService.activateUser(user.getId());
 
        return ResponseEntity.ok().build();
    }
 
   
    @PutMapping("/deactivate")
    public ResponseEntity<Void> deactivateMyAccount() {
 
        String email = CurrentUserUtil.getCurrentUserEmail();
        UserResponse user = userService.getUserByEmail(email);
 
        logger.warn("Deactivating user {}", user.getId());
        userService.deactivateUser(user.getId());
 
        return ResponseEntity.ok().build();
    }
 
    
    @GetMapping("/skills")
    public ResponseEntity<Set<String>> getMySkills() {
 
        String email = CurrentUserUtil.getCurrentUserEmail();
        UserResponse user = userService.getUserByEmail(email);
 
        logger.info("Fetching skills for user {}", user.getId());
        return ResponseEntity.ok(
                userService.getUserSkills(user.getId()));
    }
 
    
    @PostMapping("/skills/{skillId}")
    public ResponseEntity<Void> addSkillToUser(
            @PathVariable Long skillId) {
 
        String email = CurrentUserUtil.getCurrentUserEmail();
        UserResponse user = userService.getUserByEmail(email);
 
        logger.info("Adding skill {} to user {}", skillId, user.getId());
        userService.addSkillToUser(user.getId(), skillId);
 
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
 
    @DeleteMapping("/skills/{skillId}")
    public ResponseEntity<Void> removeSkillFromUser(
            @PathVariable Long skillId) {
 
        String email = CurrentUserUtil.getCurrentUserEmail();
        UserResponse user = userService.getUserByEmail(email);
 
        logger.info("Removing skill {} from user {}", skillId, user.getId());
        userService.removeSkillFromUser(user.getId(), skillId);
 
        return ResponseEntity.noContent().build();
    }
}