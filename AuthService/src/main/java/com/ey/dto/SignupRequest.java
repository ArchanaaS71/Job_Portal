package com.ey.dto;

import com.ey.enums.Role;

public class SignupRequest {
	private String email;
	private String password;
	private Role role;
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
	public SignupRequest(String email, String password, Role role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
	}
	public SignupRequest() {
		super();
	}
	@Override
	public String toString() {
		return "SignupRequest [email=" + email + ", password=" + password + ", role=" + role + "]";
	}
	
	
	
	
	

}
