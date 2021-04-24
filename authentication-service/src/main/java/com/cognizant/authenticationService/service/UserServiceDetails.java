package com.cognizant.authenticationService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognizant.authenticationService.model.User;
import com.cognizant.authenticationService.model.UserPrincipal;
import com.cognizant.authenticationService.repository.UserRepository;

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
