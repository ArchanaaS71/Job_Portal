package com.ey.userservices.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.userservices.services.UserService;

import jakarta.validation.Valid;

import com.ey.userservices.dto.request.UserRegisterRequest;
import com.ey.userservices.dto.response.UserResponse;
import com.ey.userservices.enums.Role;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private final UserService userService;
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	public UserController(UserService userService) {
		this.userService=userService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRegisterRequest request){
		
		logger.info("Registering user {}",request.getEmail());
		return ResponseEntity.ok(userService.registerUser(request));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getById(@PathVariable Long id){

		return ResponseEntity.ok(userService.getUserById(id));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<UserResponse>> getAll(){

		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@GetMapping("/role/{role}")
	public ResponseEntity<List<UserResponse>> getByRole(@PathVariable Role role){

		return ResponseEntity.ok(userService.getUsersByRole(role));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserResponse> update(@PathVariable Long id, @Valid @RequestBody UserRegisterRequest request){

		return ResponseEntity.ok(userService.updateUser(id, request));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> disable(@PathVariable Long id){

		userService.disableUser(id);
		return ResponseEntity.ok("User disabled successfully");
	}
	

}
