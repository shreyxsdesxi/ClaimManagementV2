package com.cognizant.zuulapigateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cognizant.zuulapigateway.filter.JwtAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private JwtAuthenticationFilter jwtRequestFilter;

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/authentication-service/**").permitAll()
		.antMatchers("/api/policy-service/h2-console/**").permitAll()
		
		.anyRequest().authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	//eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzaHJleWFzIiwiZXhwIjoxNjE5MjQwNjk5LCJpYXQiOjE2MTkyMDQ2OTl9.1LZzCTS1jrEZet7He3-JB1SrPY45AADJA7A5du6LMSQ


	
	
	
	
	
}
