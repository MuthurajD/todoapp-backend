package com.example.demo.utils;

import java.security.Key;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class Token {
	
	public Key privateKey;
	
	public Key getPrivateKey() {
		return Keys.secretKeyFor(SignatureAlgorithm.HS256);
	}
	
	Token(){
		privateKey = getPrivateKey();
	}
	
	public String getSignedToken(Claims claims) {
		return Jwts.builder()
		    .setClaims(claims)
	        .signWith(privateKey)
	        .compact();
	}
	
	public Jws<Claims> parseToken(String token) throws Exception{
		try {
		    return Jwts.parserBuilder().setSigningKey(privateKey).build().parseClaimsJws(token);

		} catch (Exception e) {
			throw new Exception("Invalid JWT token");
		}
		
	}
}
