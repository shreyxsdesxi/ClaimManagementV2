package com.cognizant.authenticationService.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cognizant.authenticationService.model.LoginRequest;
import com.cognizant.authenticationService.model.LoginResponse;
import com.cognizant.authenticationService.model.User;
import com.cognizant.authenticationService.model.UserPrincipal;
import com.cognizant.authenticationService.service.UserService;
import com.cognizant.authenticationService.service.UserServiceDetails;
import com.cognizant.authenticationService.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthenticationFiler extends UsernamePasswordAuthenticationFilter {
	
	@Autowired JwtUtil jwtUtil;
	
	@Autowired UserService userService;
	
	@Autowired UserServiceDetails userDetails;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {

			LoginRequest creds = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
			

			return getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(), new ArrayList<>()));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

//	@Override
//	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
//			Authentication authResult) throws IOException, ServletException {
//		// TODO Auto-generated method stub
//		String username = authResult.getName();
//		
//		
//		
//		User user = userService.findUserByUsername(username);
//		
//		UserPrincipal userPrincipal = new UserPrincipal(user);
//		 
//		 final String jwt = jwtUtil.generateToken(userPrincipal);
//		 
//		 LoginResponse responseToken = new LoginResponse(jwt);
//		 
//		 response.addHeader("token", jwt);
//		
//		
//	}
	
	

}
