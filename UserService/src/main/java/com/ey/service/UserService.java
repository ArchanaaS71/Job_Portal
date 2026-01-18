package com.ey.service;

import java.util.List;

import com.ey.dto.request.UserRegisterRequest;
import com.ey.dto.response.UserResponse;
import com.ey.enums.Role;

import jakarta.validation.Valid;

public interface UserService {

	
	UserResponse registerUser(@Valid UserRegisterRequest request);

	List<UserResponse> getAllUsers();

	UserResponse getUserById(Long id);

	List<UserResponse> getUsersByRole(Role role);

	void disableUser(Long id);

	UserResponse updateUser(Long id, @Valid UserRegisterRequest request);

}
