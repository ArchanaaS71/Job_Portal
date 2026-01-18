package com.ey.service;

public interface AuthService {

	void login(String email, String password);

	void forgotPassword(String email);

	void resetPassword(String token, String newPassword);

}
