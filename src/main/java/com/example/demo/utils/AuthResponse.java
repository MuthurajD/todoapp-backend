package com.example.demo.utils;


public class AuthResponse {
	Token token;
	String message;
	
	public AuthResponse() {};
	
	public AuthResponse(String message) {
		this.message = message;
	}
	
	public AuthResponse(Token token,String message) {
		this.token = token;
		this.message = message;
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
