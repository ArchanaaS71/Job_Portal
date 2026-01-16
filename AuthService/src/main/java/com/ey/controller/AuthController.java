
package com.ey.controller;

import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.ey.config.CurrentUserUtil;
import com.ey.dto.SignupRequest;
import com.ey.entity.User;
import com.ey.enums.AccountStatus;
import com.ey.repository.UserRepository;

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

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest req) {
        if (repo.findByMail(req.getEmail()).isPresent()) {
            return ResponseEntity.status(409).body(Map.of("error", "Mail Id Already exists"));
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

    @GetMapping("/user/me")
    public ResponseEntity<?> me() {
        User u = currentUser.getUser();
        if (u == null) return ResponseEntity.status(401).build();
        return ResponseEntity.ok(Map.of(
                "id", u.getId(),
                "mail", u.getMail(),
                "role", u.getRole().name(),
                "status", u.getStatus().name(),
                "createdAt", u.getCreatedAt(),
                "updatedAt", u.getUpdatedAt()
        ));
    }

    @PutMapping("/user/email")
    public ResponseEntity<?> updateEmail(@RequestBody Map<String, String> body) {
        User u = currentUser.getUser();
        if (u == null) return ResponseEntity.status(401).build();
        String newMail = body.get("mail");
        if (newMail == null || newMail.isBlank()) return ResponseEntity.badRequest().body(Map.of("error", "Invalid mail"));
        if (repo.findByMail(newMail).isPresent()) return ResponseEntity.status(409).body(Map.of("error", "Email already in use"));
        u.setMail(newMail);
        u.setUpdatedAt(LocalDateTime.now());
        repo.save(u);
        return ResponseEntity.accepted().body(Map.of(
                "id", u.getId(),
                "mail", u.getMail(),
                "role", u.getRole().name(),
                "status", u.getStatus().name(),
                "createdAt", u.getCreatedAt(),
                "updatedAt", u.getUpdatedAt()
        ));
    }

    @DeleteMapping("/user")
    public ResponseEntity<?> delete() {
        User u = currentUser.getUser();
        if (u == null) return ResponseEntity.status(401).build();
        repo.deleteById(u.getId());
        return ResponseEntity.noContent().build();
    }
}
