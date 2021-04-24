package com.cognizant.authenticationService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.authenticationService.model.User;
import com.cognizant.authenticationService.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired UserRepository userRepository;
	
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
}
