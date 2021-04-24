package com.cognizant.zuulapigateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognizant.zuulapigateway.entity.User;
import com.cognizant.zuulapigateway.model.UserPrincipal;
import com.cognizant.zuulapigateway.repository.UserRepository;


@Service
public class UserServiceDetails implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("No such user exists");
		}
		
		UserPrincipal userPrincipal = new UserPrincipal(user);
		return userPrincipal;
	}

}
