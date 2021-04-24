package com.cognizant.zuulapigateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cognizant.zuulapigateway.entity.User;
import com.cognizant.zuulapigateway.repository.UserRepository;


@Component
public class ZuulCommandLineRunner implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUsername("shreyas");
		user.setPassword("$2a$10$R/lZJuT9skteNmAku9Y7aeutxbOKstD5xE5bHOf74M2PHZipyt3yK");
		userRepository.save(user);
		
		User user1 = new User();
		user1.setUsername("harvey");
		user1.setPassword("$2a$10$R/lZJuT9skteNmAku9Y7aeutxbOKstD5xE5bHOf74M2PHZipyt3yK");
		userRepository.save(user1);
	}

}
