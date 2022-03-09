package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserItem;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public void signUp (UserItem user) throws Exception {
		
		if(user.getEmail()==null || user.getPassword()==null || user.getName()==null) 
			throw new Exception("Info not provided");
		
		Optional<UserItem> userWithEmail = userRepository.findByEmail(user.getEmail());
		
		if (userWithEmail.isPresent() ) throw new Exception("User with email Already Exists");
		
		userRepository.save(user);
	}
	
	public void logIn(UserItem user) throws Exception {
		
		if (user.getEmail() == null || user.getPassword() == null) 
			throw new Exception("Password or email not given");
		Optional<UserItem> userWithEmailAndPassword = 
				userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
		
		if (userWithEmailAndPassword.isEmpty()) throw new Exception("Wrong email or password");
		
		user.set_id(userWithEmailAndPassword.get().get_id());
		user.setName(userWithEmailAndPassword.get().getName());
		
	}
	
	public Optional<UserItem> getUserById(String id) {
		return userRepository.findById(id);
	}
}
