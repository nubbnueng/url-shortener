package com.nubbnueng.urlshortener.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	private final String username = "admin";
	private final String password = "p@ssw0rd";
	private static boolean login = false;
	
	public UserService() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean doAuthenticate(String username, String password) {
		login = this.username.equals(username) && this.password.equals(password);
		return login;
	}
	
	public boolean isLogin() {
		return login;
	}
	
	public void logout() {
		UserService.login = false;
	}
	
}
