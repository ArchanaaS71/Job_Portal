package com.ey.userservices.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.userservices.dto.request.ForgetPasswordRequest;
import com.ey.userservices.dto.request.LoginRequest;
import com.ey.userservices.dto.request.ResetPasswordRequest;
import com.ey.userservices.dto.response.ApiResponse;
import com.ey.userservices.services.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private static final Logger logger=LoggerFactory.getLogger(AuthController.class);
	
	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	@PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginRequest request) {
 
        logger.info("Login attempt for {}", request.getEmail());
        authService.login(request.getEmail(), request.getPassword());
 
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse("Login successful"));
    }
	 @PostMapping("/forgot-password")
	 public ResponseEntity<ApiResponse> forgotPassword(@Valid @RequestBody ForgetPasswordRequest request) {
	 
		 	logger.info("Forgot password request for {}", request.getEmail());
	        authService.forgotPassword(request.getEmail());
	 
	        return ResponseEntity
	                .status(HttpStatus.OK)
	                .body(new ApiResponse(
	                        "Password reset link sent to email"));
	    }
	 @PostMapping("/reset-password")
	    public ResponseEntity<ApiResponse> resetPassword(
	            @Valid @RequestBody ResetPasswordRequest request) {
	 
	        logger.info("Reset password attempt");
	        authService.resetPassword(request.getToken(), request.getNewPassword());
	 
	        return ResponseEntity
	                .status(HttpStatus.OK)
	                .body(new ApiResponse("Password reset successful"));
	    }
	
	

	
}
