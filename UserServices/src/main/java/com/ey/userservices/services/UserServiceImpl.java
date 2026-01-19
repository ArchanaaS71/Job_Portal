package com.ey.userservices.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.ey.userservices.dto.request.UserRegisterRequest;
import com.ey.userservices.dto.response.UserResponse;
import com.ey.userservices.entity.User;
import com.ey.userservices.enums.Role;
import com.ey.userservices.mapper.UserMapper;
import com.ey.userservices.repository.UserRepository;

import jakarta.validation.Valid;

public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository)
	{
		this.userRepository=userRepository;
	}

	@Override
	public UserResponse registerUser(@Valid UserRegisterRequest request) {
		User user=UserMapper.toEntity(request);
		return UserMapper.toResponse(userRepository.save(user));
	}

	@Override
	public List<UserResponse> getAllUsers() {
		return userRepository.findAll().stream().map(UserMapper::toResponse).collect(Collectors.toList());
	}

	@Override
	public UserResponse getUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
		return UserMapper.toResponse(user);
	}

	@Override
	public List<UserResponse> getUsersByRole(Role role) {
		return userRepository.findByRole(role).stream().map(UserMapper::toResponse).collect(Collectors.toList());
	}

	@Override
	public void disableUser(Long id) {
		User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
		user.setActive(false);
		userRepository.save(user);
		
	}

	@Override
	public UserResponse updateUser(Long id, @Valid UserRegisterRequest request) {
		User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());
		user.setRole(request.getRole());
		user.setUpdatedAt(LocalDateTime.now());
		return UserMapper.toResponse(userRepository.save(user));
	}

}
