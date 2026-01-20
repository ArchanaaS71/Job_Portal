package com.ey.jobportal.service;
 
import com.ey.jobportal.dto.request.LoginRequest;
import com.ey.jobportal.dto.request.RegisterEmployerRequest;
import com.ey.jobportal.dto.request.RegisterUserRequest;
 
public interface AuthService {
 
    String registerUser(RegisterUserRequest request);
 
    String registerEmployer(RegisterEmployerRequest request);
 
    String forgotPassword(String email);
 
    String resetPassword(String token, String newPassword);
 
    boolean validateResetToken(String token);
 
    String changePassword(Long userId, String oldPassword, String newPassword);

	String login(String email, String password);
} 