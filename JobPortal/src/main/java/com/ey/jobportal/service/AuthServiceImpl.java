package com.ey.jobportal.service;
 
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
 
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
 
import com.ey.jobportal.dto.request.LoginRequest;
import com.ey.jobportal.dto.request.RegisterEmployerRequest;
import com.ey.jobportal.dto.request.RegisterUserRequest;
import com.ey.jobportal.entity.*;
import com.ey.jobportal.enums.RoleName;
import com.ey.jobportal.mapper.EmployerMapper;
import com.ey.jobportal.mapper.UserMapper;
import com.ey.jobportal.repository.*;
import com.ey.jobportal.security.JwtUtil;

@Service
public class AuthServiceImpl implements AuthService {
 
    private final UserRepository userRepository;
    private final EmployerRepository employerRepository;
    private final RoleRepository roleRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
 
    public AuthServiceImpl(UserRepository userRepository,
                           EmployerRepository employerRepository,
                           RoleRepository roleRepository,
                           PasswordResetTokenRepository tokenRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.employerRepository = employerRepository;
        this.roleRepository = roleRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
    }
 
    @Override
    public String registerUser(RegisterUserRequest request) {
 
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
 
        User user = UserMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
 
        Role role = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Role not found"));
 
        user.setRoles(Set.of(role));
        userRepository.save(user);
 
        return "User registered successfully";
    }
 
    @Override
    public String registerEmployer(RegisterEmployerRequest request) {
 
        if (employerRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
 
        Employer employer = EmployerMapper.toEntity(request);
        employer.setPassword(passwordEncoder.encode(employer.getPassword()));
 
        Role role = roleRepository.findByName(RoleName.ROLE_EMPLOYER)
                .orElseThrow(() -> new RuntimeException("Role not found"));
 
        employer.setRoles(Set.of(role));
        employerRepository.save(employer);
 
        return "Employer registered successfully";
    }
 


 
    @Override
    public String forgotPassword(String email) {
 
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
 
        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(LocalDateTime.now().plusMinutes(15));
 
        tokenRepository.save(token);
        return token.getToken();
    }
 
    @Override
    public String resetPassword(String token, String newPassword) {
 
        PasswordResetToken resetToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));
 
        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expired");
        }
 
        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
 
        userRepository.save(user);
        tokenRepository.delete(resetToken);
 
        return "Password reset successful";
    }
 
    @Override
    public boolean validateResetToken(String token) {
        return tokenRepository.findByToken(token)
                .filter(t -> t.getExpiryDate().isAfter(LocalDateTime.now()))
                .isPresent();
    }
 
    @Override
    public String changePassword(Long userId, String oldPassword, String newPassword) {
 
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
 
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new RuntimeException("Old password incorrect");
        }
 
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
 
        return "Password changed successfully";
    }

    @Override
    public String login(String email, String password) {
     
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
     
        if (!user.isActive()) {
            throw new RuntimeException("User account is deactivated");
        }
     
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }
     
     
        String role = user.getRoles()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User has no role assigned"))
                .getName()
                .name();  
     
        return JwtUtil.generateToken(
                user.getEmail(),
                role
        );
    }
}
     
 