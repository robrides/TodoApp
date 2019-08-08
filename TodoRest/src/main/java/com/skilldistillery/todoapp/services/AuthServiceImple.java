package com.skilldistillery.todoapp.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.todoapp.entities.User;
import com.skilldistillery.todoapp.repositories.UserRepository;

@Service
public class AuthServiceImple implements AuthService {

	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public User register(User user) {
		String encodedPW = encoder.encode(user.getPassword());
		// Should do some validating of password here before proceeding both in the controller and impl
		
		user.setPassword(encodedPW); // only persist encoded password

		user.setEnabled(true);

		user.setRole("standard");
		
		userRepo.saveAndFlush(user);
		return user;
	}
}
