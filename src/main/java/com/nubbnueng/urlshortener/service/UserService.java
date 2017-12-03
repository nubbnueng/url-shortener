package com.nubbnueng.urlshortener.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	final String username = "admin";
	final String password = "p@ssw0rd";
	
	public UserService() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean doAuthenticate(String username, String password) {
		return this.username.equals(username) && this.password.equals(password);
	}
	
}
