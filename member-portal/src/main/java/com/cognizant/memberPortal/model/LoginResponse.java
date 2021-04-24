package com.cognizant.memberPortal.model;

public class LoginResponse {
	
	private String token;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LoginResponse(String token, User user) {
		super();
		this.token = token;
		this.user = user;
	}

	public LoginResponse() {
		super();
	}
	
	

	
	
	
}
