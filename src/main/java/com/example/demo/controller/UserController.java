package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.UserItem;
import com.example.demo.service.UserService;
import com.example.demo.utils.AuthResponse;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/user/signup")
	public AuthResponse  signUp(@RequestBody UserItem user) {
		AuthResponse result = userService.signUp(user);
		System.out.print(result.getToken().getId());
		return result;
	}
	
	@PostMapping("/user/login")
	public AuthResponse logIn(@RequestBody UserItem user) {
		return userService.logIn(user);
	}
	
}