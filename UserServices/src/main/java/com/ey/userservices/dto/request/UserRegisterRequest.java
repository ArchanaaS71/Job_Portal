package com.ey.userservices.dto.request;

import com.ey.userservices.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserRegisterRequest {
	@NotBlank
	private String name;
	
	@Email
	private String email;
	
	@NotBlank
	private String password;
	
	@NotNull
	private Role role;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public UserRegisterRequest(@NotBlank String name, @Email String email, @NotBlank String password,
			@NotNull Role role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public UserRegisterRequest() {
		super();
	}
	
	

}
