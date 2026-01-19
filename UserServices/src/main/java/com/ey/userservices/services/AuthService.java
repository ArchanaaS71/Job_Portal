package com.ey.userservices.services;

import com.ey.userservices.dto.request.LoginRequest;

public interface AuthService {

	void login(String email, String password);

	void forgotPassword(String email);

	void resetPassword(String token, String newPassword);
	

}