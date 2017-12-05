package com.nubbnueng.urlshortener.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	private final String username = "admin";
	private final String password = "p@ssw0rd";
	
	public UserService() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean doAuthenticate(String username, String password) {
		return this.username.equals(username) && this.password.equals(password);
	}
	
}
