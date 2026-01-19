package com.ey.userservices.mapper;

import com.ey.userservices.dto.request.UserRegisterRequest;
import com.ey.userservices.dto.response.UserResponse;
import com.ey.userservices.entity.User;

public class UserMapper {
	
	public static User toEntity(UserRegisterRequest request) {
		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());
		user.setRole(request.getRole());
		return user;
		
	}
	
	public static UserResponse toResponse(User user) {
		UserResponse response=new UserResponse();
		response.setId(user.getId());
		response.setName(user.getName());
		response.setEmail(user.getEmail());
		response.setRole(user.getRole());
		response.setActive(user.getActive());
		return response;
		
	}

}
