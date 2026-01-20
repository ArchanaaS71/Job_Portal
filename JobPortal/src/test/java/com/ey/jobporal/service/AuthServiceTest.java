package com.ey.jobporal.service;
 
import static org.junit.jupiter.api.Assertions.*;
 
import java.util.Set;
import java.util.UUID;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
 
import com.ey.jobportal.dto.request.RegisterUserRequest;
import com.ey.jobportal.entity.Role;
import com.ey.jobportal.enums.RoleName;
import com.ey.jobportal.entity.User;
import com.ey.jobportal.repository.RoleRepository;
import com.ey.jobportal.repository.UserRepository;
import com.ey.jobportal.service.AuthService;
 
@SpringBootTest
@Transactional
class AuthServiceTest {
 
    @Autowired
    private AuthService authService;
 
    @Autowired
    private UserRepository userRepository;
 
    @Autowired
    private RoleRepository roleRepository;
 
    private User user;
 
    @BeforeEach
    void setup() {
 
        String unique = UUID.randomUUID().toString();
 
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName(RoleName.ROLE_USER);
                    return roleRepository.save(role);
                });
 
        user = new User();
        user.setFullName("Auth User " + unique);
        user.setEmail("auth" + unique + "@gmail.com");
        user.setPassword("$2a$10$abcdefabcdefabcdefabcdefabcdefabcdefabcdef"); 
        user.setActive(true);
        user.setRoles(Set.of(userRole));
 
        user = userRepository.save(user);
    }
 
    
 
    @Test
    void testRegisterUser() {
 
        RegisterUserRequest request = new RegisterUserRequest();
        request.setFullName("New User");
        request.setEmail("newuser" + UUID.randomUUID() + "@gmail.com");
        request.setPassword("User@123");
 
        String response = authService.registerUser(request);
 
        assertNotNull(response);
        assertTrue(
                userRepository.findByEmail(request.getEmail()).isPresent());
    }
 
 
    @Test
    void testLoginSuccess() {
 
   
        String token = authService.login(
                user.getEmail(),
                "User@123"  
        );
 
        assertNotNull(token);
        assertFalse(token.isBlank());
    }
 
    
 
    @Test
    void testForgotPasswordAndValidateToken() {
 
        String token = authService.forgotPassword(user.getEmail());
 
        assertNotNull(token);
        assertTrue(authService.validateResetToken(token));
    }
}
 
