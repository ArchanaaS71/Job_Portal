
package com.ey.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.ey.config.CurrentUserUtil;
import com.ey.dto.LoginRequest;
import com.ey.dto.SignupRequest;
import com.ey.entity.User;
import com.ey.enums.AccountStatus;
import com.ey.repository.UserRepository;
import com.ey.security.JwtUtil;

@RestController

public class AuthController {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final CurrentUserUtil currentUser;

    public AuthController(UserRepository repo, PasswordEncoder encoder, CurrentUserUtil currentUser) {
        this.repo = repo;
        this.encoder = encoder;
        this.currentUser = currentUser;
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest req) {
        if (repo.findByMail(req.getEmail()).isPresent()) {
            return ResponseEntity.status(409).body(Map.of("error", "Mail Id Already exists"));
        }
       
        if (req.getEmail() == null || !req.getEmail().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$") ||

        		req.getPassword() == null || !req.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$")) {
        	 return ResponseEntity.status(400).body(Map.of(
        			 "error", "Invalid format",
        			 "mail","mail id is invalid",
        			 "password","Password must be at least 8 characters and contain 1 uppercase,1 lowercase, 1 number and 1 special character"));
        }

        String domain = req.getEmail().substring(req.getEmail().indexOf("@") + 1).toLowerCase();
        List<String> allowed = List.of("gmail.com","yahoo.com","outlook.com","hotmail.com","icloud.com");

        if (!allowed.contains(domain)) {
        	return ResponseEntity.status(422).body(
        Map.of("error", "Invalid User data",
               "details", Map.of("email", "Domain does not exist")));
        }

        User u = new User();
        u.setMail(req.getEmail());
        u.setPassword(encoder.encode(req.getPassword()));
        u.setRole(req.getRole());
        u.setStatus(AccountStatus.ACTIVE);
        u.setCreatedAt(LocalDateTime.now());
        u.setUpdatedAt(LocalDateTime.now());
        repo.save(u);
        return ResponseEntity.status(201).body(Map.of(
                "id", u.getId(),
                "mail", u.getMail(),
                "role", u.getRole().name(),
                "status", u.getStatus().name(),
                "createdAt", u.getCreatedAt()
        ));
    }


    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        var opt = repo.findByMail(req.getEmail());
        if (opt.isEmpty())
            return ResponseEntity.status(401).body(Map.of(
                    "error", "Wrong credentials",
                    "details", Map.of("email", "Mail does not exist", "password", "Wrong password")
            ));

        User u = opt.get();
        if (u.getStatus() == AccountStatus.SUSPENDED)
            return ResponseEntity.status(423).body(Map.of(
                    "error", "Account might be locked",
                    "details", "contact admin for further information"
            ));

        if (!encoder.matches(req.getPassword(), u.getPassword()))
            return ResponseEntity.status(401).body(Map.of(
                    "error", "Wrong credentials",
                    "details", Map.of("email", "Mail does not exist", "password", "Wrong password")
            ));

        String token = JwtUtil.generateToken(u.getMail());
        return ResponseEntity.ok(Map.of(
                "token", token,
                "user", Map.of("id", u.getId(), "mail", u.getMail(), "role", u.getRole().name()),
                "expiresIn", 3600
        ));
    }


    @PostMapping("/auth/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String,String> body) {
    	String email = body.get("email");
    	return ResponseEntity.ok(Map.of(
            "message", "if the mail exists, password reset link has been sent"));
    }

    @PostMapping("/auth/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String,String> body) {
    String token = body.get("token");
    String email = body.get("email");
    String newPassword = body.get("newpassword");
    if (token == null || token.isBlank())
        return ResponseEntity.status(401).body(Map.of("error", "Invalid or expired token"));
    if (email == null || newPassword == null)
        return ResponseEntity.status(401).body(Map.of("error", "Invalid or expired token"));
    if (!newPassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$"))
        return ResponseEntity.badRequest().body(Map.of("error", "Weak password"));
    var opt = repo.findByMail(email);
    if (opt.isEmpty())
        return ResponseEntity.status(401).body(Map.of("error", "Invalid or expired token"));

    User u = opt.get();
    u.setPassword(encoder.encode(newPassword));
    u.setUpdatedAt(LocalDateTime.now());
    repo.save(u);

    return ResponseEntity.ok(Map.of("message", "Password is reset successfully"));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
    	if (currentUser.getUser() == null)
    		return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));
    	if (repo.findById(id).isEmpty())
    		return ResponseEntity.status(404).body(Map.of("error", "User not found"));
    	User u = repo.findById(id).get();
    	return ResponseEntity.status(302).body(Map.of(
            "id", u.getId(),
            "mail", u.getMail(),
            "role", u.getRole().name(),
            "status", u.getStatus().name(),
            "createdAt", u.getCreatedAt(),
            "updatedAt", u.getUpdatedAt()
    ));
    }
    


    @PutMapping("/user/{id}/email")
    public ResponseEntity<?> updateEmail(@PathVariable Long id, @RequestBody Map<String, String> body) {
    	if (currentUser.getUser() == null) {
    		return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));
    	}

    	User u1 = repo.findById(id).orElse(null);
    	if (u1 == null) {
    		return ResponseEntity.status(404).body(Map.of("error", "User not found"));
    	}

    	String newMail = body.getOrDefault("mail", "").trim();

    	if (newMail.equalsIgnoreCase(u1.getMail())) {
    		return ResponseEntity.ok(Map.of(
    				"id", u1.getId(),
    				"mail", u1.getMail(),
    				"role", u1.getRole().name(),
    				"status", u1.getStatus().name(),
    				"createdAt", u1.getCreatedAt(),
    				"updatedAt", u1.getUpdatedAt()
    				));
    	}
    	
    	if (repo.existsByMailAndIdNot(newMail, id)) {
    		return ResponseEntity.status(409).body(Map.of("error", "Email already in use"));
    	}

    	u1.setMail(newMail);
    	u1.setUpdatedAt(LocalDateTime.now());
    	repo.save(u1);

    	return ResponseEntity.ok(Map.of(
    			"id", u1.getId(),
    			"mail", u1.getMail(),
    			"role", u1.getRole().name(),
    			"status", u1.getStatus().name(),
    			"createdAt", u1.getCreatedAt(),
    			"updatedAt", u1.getUpdatedAt()
    			));
    }

//    @PutMapping("/user/{id}")
//    public ResponseEntity<?> updateEmail(@PathVariable Long id, @RequestBody Map<String, String> body) {
//    	if (currentUser.getUser() == null)
//    		return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));
//    	if (repo.existsByMail(body.get("mail")))
//    		return ResponseEntity.status(409).body(Map.of("error", "Email already in use"));
//   
//    	User u1 = repo.findById(id).orElse(null);
//    	if (u1 == null)
//    		return ResponseEntity.status(404).body(Map.of("error", "User not found"));
//
//    	u1.setMail(body.get("mail"));
//    	u1.setUpdatedAt(LocalDateTime.now());
//    	repo.save(u1);
//
//    	return ResponseEntity.accepted().body(Map.of(
//    			"id", u1.getId(),
//    			"mail", u1.getMail(),
//    			"role", u1.getRole().name(),
//    			"status", u1.getStatus().name(),
//    			"createdAt", u1.getCreatedAt(),
//    			"updatedAt", u1.getUpdatedAt()
//    			));
//
//    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
    	if (currentUser.getUser() == null)
    		return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));
    	if (repo.findById(id).isEmpty())
    		return ResponseEntity.status(409).body(Map.of("error", "Email already deleted"));
    	repo.deleteById(id);
    	return ResponseEntity.status(204).body(Map.of("Id " + id + " deleted", ""));
}
    
}
