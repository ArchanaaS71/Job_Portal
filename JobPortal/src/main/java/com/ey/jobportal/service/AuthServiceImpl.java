package com.ey.jobportal.service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ey.jobportal.dto.request.RegisterEmployerRequest;
import com.ey.jobportal.dto.request.RegisterUserRequest;
import com.ey.jobportal.entity.Employer;
import com.ey.jobportal.entity.PasswordResetToken;
import com.ey.jobportal.entity.Role;
import com.ey.jobportal.entity.User;
import com.ey.jobportal.enums.RoleName;
import com.ey.jobportal.mapper.EmployerMapper;
import com.ey.jobportal.repository.EmployerRepository;
import com.ey.jobportal.repository.PasswordResetTokenRepository;
import com.ey.jobportal.repository.RoleRepository;
import com.ey.jobportal.repository.UserRepository;
import com.ey.jobportal.security.JwtUtil;

import jakarta.transaction.Transactional;

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

    @Transactional
    @Override
    public String registerUser(RegisterUserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Role role = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("ROLE_USER not found"));

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());
        user.setLocation(request.getLocation());
        user.setExperienceYears(request.getExperienceYears());
        user.setHighestQualification(request.getHighestQualification());
        user.setResumeSummary(request.getResumeSummary());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setActive(true);
        user.setRoles(Set.of(role));

        userRepository.save(user);
        return "User registered successfully";
    }

    @Override
    public String registerEmployer(RegisterEmployerRequest request) {

        if (employerRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        Role role = roleRepository.findByName(RoleName.ROLE_EMPLOYER)
                .orElseThrow(() -> new RuntimeException("ROLE_EMPLOYER not found"));

        Employer employer = EmployerMapper.toEntity(request);
        employer.setPassword(passwordEncoder.encode(employer.getPassword()));
        employer.setRoles(Set.of(role));

        employerRepository.save(employer);
        return "Employer registered successfully";
    }

    @Override
    public String login(String email, String password) {

  
        var userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();

            if (!user.isActive()) {
                throw new RuntimeException("User account is deactivated");
            }

            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new RuntimeException("Invalid email or password");
            }

            String role = user.getRoles()
                    .iterator()
                    .next()
                    .getName()
                    .name();

            return JwtUtil.generateToken(user.getEmail(), role);
        }

        // EMPLOYER login
        var employerOpt = employerRepository.findByEmail(email);
        if (employerOpt.isPresent()) {
            Employer employer = employerOpt.get();

            if (!passwordEncoder.matches(password, employer.getPassword())) {
                throw new RuntimeException("Invalid email or password");
            }

            String role = employer.getRoles()
                    .iterator()
                    .next()
                    .getName()
                    .name();

            return JwtUtil.generateToken(employer.getEmail(), role);
        }

        throw new RuntimeException("Invalid email or password");
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
}
