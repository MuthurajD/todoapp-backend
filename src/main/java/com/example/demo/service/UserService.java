package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserItem;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.AuthResponse;
import com.example.demo.utils.Token;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public AuthResponse signUp(UserItem user) {
		Optional<UserItem> userWithEmail = userRepository.findByEmail(user.getEmail());
		
		if (userWithEmail.isPresent()) return new AuthResponse("User With email Already Exists");
		user = userRepository.save(user);
		
		return new AuthResponse(new Token(user.get_id(),user.getEmail()),"SignedUp Successfully");
	}
	
	public AuthResponse logIn(UserItem user) {
		Optional<UserItem> userWithEmailAndPassword = 
				userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
		
		if (userWithEmailAndPassword.isPresent()==false) return new AuthResponse("Wrong Email or Password");
		user = userWithEmailAndPassword.get();
		
		return new AuthResponse( new Token(user.get_id(),user.getEmail()),"LoggedIn Successfully");
	}
	
	public Optional<UserItem> getUserById(String id) {
		return userRepository.findById(id);
	}
}
