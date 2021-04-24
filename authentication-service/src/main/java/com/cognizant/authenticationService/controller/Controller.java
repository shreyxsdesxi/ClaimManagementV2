package com.cognizant.authenticationService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.authenticationService.model.LoginRequest;
import com.cognizant.authenticationService.model.LoginResponse;
import com.cognizant.authenticationService.model.User;
import com.cognizant.authenticationService.security.AuthenticationFiler;
import com.cognizant.authenticationService.service.UserService;
import com.cognizant.authenticationService.service.UserServiceDetails;
import com.cognizant.authenticationService.util.JwtUtil;

@RestController
public class Controller {
	
	@Autowired AuthenticationManager authenticationManager;
	
	@Autowired UserServiceDetails userDetailsService;
	
	@Autowired UserService userService;
	
	@Autowired JwtUtil jwtUtil;
	
	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody LoginRequest loginRequest) throws Exception{
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		}
		catch(Exception e) {
			throw new Exception("Bad Credentials");
		}
		
		 final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
		 
		 final String jwt = jwtUtil.generateToken(userDetails);
		 
		 User user = userService.findUserByUsername(loginRequest.getUsername());
		 
		 LoginResponse response = new LoginResponse(jwt, user);

		 return new ResponseEntity<LoginResponse>(response, HttpStatus.CREATED);
		 
	}
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}
	
	
}
