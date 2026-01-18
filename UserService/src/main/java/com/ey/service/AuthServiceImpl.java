package com.ey.service;

import java.time.LocalDateTime;
import java.util.UUID;

import com.ey.entity.PasswordResetToken;
import com.ey.entity.User;
import com.ey.repository.PasswordResetTokenRepository;
import com.ey.repository.UserRepository;

public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	private final PasswordResetTokenRepository tokenRepository;
	
	public AuthServiceImpl(UserRepository userRepository, PasswordResetTokenRepository tokenRepository) {
		this.userRepository = userRepository;
		this.tokenRepository = tokenRepository;
	}

	@Override
	public void login(String email, String password) {
		 User user = userRepository.findByEmail(email)
	                .orElseThrow(() ->new RuntimeException("Invalid email or password"));
	 
	        if (!user.getPassword().equals(password)) {
	            throw new RuntimeException("Invalid email or password");
	        }
	 
	        if (!user.getActive()) {
	            throw new RuntimeException("User account is disabled");
	        }
	}

	@Override
	public void forgotPassword(String email) {
		 User user = userRepository.findByEmail(email)
	                .orElseThrow(() ->
	                        new RuntimeException("User not found"));
	 
	        PasswordResetToken token = new PasswordResetToken();
	        token.setUserId(user.getId());
	        token.setToken(UUID.randomUUID().toString());
	        token.setExpiryTime(LocalDateTime.now().plusMinutes(15));
	        tokenRepository.save(token);
		
	}

	@Override
	public void resetPassword(String tokenValue, String newPassword) {
		 PasswordResetToken token = tokenRepository.findByToken(tokenValue)
	                .orElseThrow(() ->
	                        new RuntimeException("Invalid reset token"));
	 
	        if (token.getExpiryTime().isBefore(LocalDateTime.now())) {
	            throw new RuntimeException("Reset token expired");
	        }
	        User user = userRepository.findById(token.getUserId())
	                .orElseThrow(() ->
	                        new RuntimeException("User not found"));
	 
	        user.setPassword(newPassword);
	        userRepository.save(user);
	 
	        tokenRepository.delete(token);
		
	}

}
