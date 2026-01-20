package com.ey.jobportal.mapper;

import org.springframework.stereotype.Component;

import com.ey.jobportal.dto.request.RegisterUserRequest;
import com.ey.jobportal.dto.response.UserResponse;
import com.ey.jobportal.entity.User;

@Component
public class UserMapper {
	public static User toEntity(RegisterUserRequest request) {
		 
        if (request == null) {
            return null;
        }
 
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setLocation(request.getLocation());
        user.setExperienceYears(request.getExperienceYears());
        user.setHighestQualification(request.getHighestQualification());
        user.setResumeSummary(request.getResumeSummary());
        user.setDateOfBirth(request.getDateOfBirth());
//        user.setActive(true);
 
        return user;
    }
 
    public static UserResponse toResponse(User user) {
 
        if (user == null) {
            return null;
        }
 
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setLocation(user.getLocation());
        response.setExperienceYears(user.getExperienceYears());
        response.setHighestQualification(user.getHighestQualification());
//        response.setActive(user.isActive());
 
        return response;
    }
}


