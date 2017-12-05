package com.nubbnueng.urlshortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nubbnueng.urlshortener.model.User;
import com.nubbnueng.urlshortener.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	private static boolean login = false;
	
	public boolean doAuthenticate(String username, String password) {
		User user = userRepository.findByUsername(username);
		if (user != null) {
			login = password.equals(user.getPassword());
		}
		return login;
	}
	
	public boolean isLogin() {
		return login;
	}
	
	public void logout() {
		UserService.login = false;
	}
	
}
