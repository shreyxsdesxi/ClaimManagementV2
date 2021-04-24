package com.cognizant.zuulapigateway.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.zuulapigateway.entity.User;
import com.cognizant.zuulapigateway.repository.UserRepository;


public class UserService {

@Autowired UserRepository userRepository;
	
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
}
