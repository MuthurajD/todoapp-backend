package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.UserItem;
import com.example.demo.service.UserService;
import com.example.demo.utils.Token;

import io.jsonwebtoken.Jwts;

@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Token token;
	
	
	
	@PostMapping("/user/signup")
	public ResponseEntity< Map<String,Object> >  signUp(@RequestBody UserItem user) {
		
		System.out.print("received");
		
		try {
			userService.signUp(user);
			
			Map <String,Object> claims = new HashMap<>();
			claims.put("_id", user.get_id());
			claims.put("name", user.getName());
			claims.put("email", user.getEmail());
			
			String JWTtoken = token.getSignedToken(Jwts.claims(claims));
			
			Map<String,Object> body = new HashMap<>();
			body.put("token",JWTtoken );
			
			return ResponseEntity
					.ok(body);
		}
		catch(Exception e){
			
			Map<String,Object> result = new HashMap<>();
			result.put("error", e.getMessage());
			
			return ResponseEntity.ok().body(result);
		}
		
	}
	
	@PostMapping("/user/login")
	public ResponseEntity<Map<String,Object>> logIn(@RequestBody UserItem user) {
		
		try {
			
			userService.logIn(user);
			
			Map <String,Object> claims = new HashMap<>();
			claims.put("_id", user.get_id());
			claims.put("name", user.getName());
			claims.put("email", user.getEmail());
			
			String JWTtoken = token.getSignedToken(Jwts.claims(claims));
			
			Map<String,Object> body = new HashMap<>();
			body.put("token",JWTtoken );
			
			return ResponseEntity
					.ok(body);

		}
		catch(Exception e) {
			Map<String,Object> body = new HashMap<>();
			body.put("error", e.getMessage());
			
			return ResponseEntity.ok().body(body);
		}
		
		
		
	}
	
}