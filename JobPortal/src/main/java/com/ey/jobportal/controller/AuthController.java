package com.ey.jobportal.controller;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
 
import com.ey.jobportal.dto.request.LoginRequest;
import com.ey.jobportal.dto.request.RegisterEmployerRequest;
import com.ey.jobportal.dto.request.RegisterUserRequest;
import com.ey.jobportal.service.AuthService;
 
@RestController
@RequestMapping("/api/auth")
public class AuthController {
 
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;
 
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
 
    @PostMapping("/register/user")
    public ResponseEntity<String> registerUser(@RequestBody RegisterUserRequest request) {
        logger.info("Registering new user: {}", request.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authService.registerUser(request));
    }
 
    @PostMapping("/register/employer")
    public ResponseEntity<String> registerEmployer(@RequestBody RegisterEmployerRequest request) {
        logger.info("Registering new employer: {}", request.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authService.registerEmployer(request));
    }
 
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        logger.info("Login attempt: {}", request.getEmail());
        return ResponseEntity.ok(authService.login(request.getEmail(), request.getPassword()));
    }
 
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        logger.info("Forgot password requested for: {}", email);
        return ResponseEntity.ok(authService.forgotPassword(email));
    }
 
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(
            @RequestParam String token, @RequestParam String newPassword) {
        logger.info("Reset password using token");
        return ResponseEntity.ok(authService.resetPassword(token, newPassword));
    }
 
    
    @GetMapping("/validate-reset-token")
    public ResponseEntity<Boolean> validateResetToken(@RequestParam String token) {
        return ResponseEntity.ok(authService.validateResetToken(token));
    }
}
 