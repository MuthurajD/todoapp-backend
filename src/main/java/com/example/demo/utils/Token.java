package com.example.demo.utils;


public class Token {
	String id;
	String email;
	
	public Token(String id,String email) {
		this.id = id;
		this.email = email;
	}
	
	public static Boolean verify(Token token) {
		return token!=null;
	}
	
	public static Token encrypt(Token token) {
		return token;
	}
	
	public static Token decrypt(Token token) {
		return token;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}