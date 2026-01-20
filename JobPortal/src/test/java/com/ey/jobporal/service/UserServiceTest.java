package com.ey.jobporal.service;

import static org.junit.jupiter.api.Assertions.*;
 
import java.util.List;
import java.util.Set;
import java.util.UUID;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
 
import com.ey.jobportal.dto.response.UserResponse;
import com.ey.jobportal.entity.Role;
import com.ey.jobportal.enums.RoleName;
import com.ey.jobportal.entity.Skill;
import com.ey.jobportal.entity.User;
import com.ey.jobportal.repository.RoleRepository;
import com.ey.jobportal.repository.SkillRepository;
import com.ey.jobportal.repository.UserRepository;
import com.ey.jobportal.service.UserService;
 
@SpringBootTest
@Transactional
class UserServiceTest {
 
    @Autowired
    private UserService userService;
 
    @Autowired
    private UserRepository userRepository;
 
    @Autowired
    private RoleRepository roleRepository;
 
    @Autowired
    private SkillRepository skillRepository;
 
    private User user;
 
    @BeforeEach
    void setup() {
 
        String unique = UUID.randomUUID().toString();
 
        Role role = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseGet(() -> {
                    Role r = new Role();
                    r.setName(RoleName.ROLE_USER);
                    return roleRepository.save(r);
                });
 
        user = new User();
        user.setFullName("Test User " + unique);
        user.setEmail("user" + unique + "@gmail.com");
        user.setPassword("Password@123");
        user.setActive(true);
        user.setRoles(Set.of(role));
 
        user = userRepository.save(user);
    }
 
    private Skill createSkill(String name) {
        Skill skill = new Skill();
        skill.setName(name);
        return skillRepository.save(skill);
    }

 
    @Test
    void testGetUserById() {
 
        UserResponse response = userService.getUserById(user.getId());
 
        assertNotNull(response);
        assertEquals(user.getId(), response.getId());
        assertEquals(user.getEmail(), response.getEmail());
        assertTrue(response.isActive());
    }
 
   
 
    @Test
    void testGetAllUsers() {
 
        List<UserResponse> users = userService.getAllUsers();
 
        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

 
    @Test
    void testAddAndGetUserSkills() { 
        Skill javaSkill = createSkill("Java"); 
        userService.addSkillToUser(user.getId(), javaSkill.getId()); 
        Set<String> skills = userService.getUserSkills(user.getId()); 
        assertNotNull(skills);
        assertTrue(skills.contains("Java"));
    }
}
 