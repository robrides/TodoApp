package com.skilldistillery.todoapp.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.todoapp.entities.User;
import com.skilldistillery.todoapp.services.AuthService;

@RestController
@CrossOrigin({ "*", "http://localhost:4200" })
public class AuthController {

	@Autowired AuthService authService;
	
	@PostMapping("/register")
	public User register(@RequestBody User user, HttpServletResponse res) {
		// Should add logic to check input data
	    if (user == null) {
	        res.setStatus(400);
	        // could return a successful status code and no response body
	    }

	    user = authService.register(user);

	    return user;
	}

	@GetMapping("/authenticate")
	public Principal authenticate(Principal principal) {
	    return principal;
	}
	
}
