package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.TaskItem;
import com.example.demo.service.TaskService;
import com.example.demo.service.UserService;
import com.example.demo.utils.Token;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@RestController
@CrossOrigin
public class TaskController {
	
	@Autowired
	TaskService taskService;
	@Autowired
	UserService userService;
	@Autowired
	Token token;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@GetMapping("/tasks")
	public ResponseEntity<Map<String,Object>> getTasksByUserId(RequestEntity<String> request){
		HttpHeaders header = request.getHeaders();
		String JWTtoken = header.getFirst("token");
		
		Map<String,Object> result = new HashMap<>();
		try {
			Jws<Claims> claims = token.parseToken(JWTtoken);
			String user_id = claims.getBody().get("_id", String.class);
			
			result.put("tasks", taskService.findAllByUserItemId(user_id));
			
			return ResponseEntity.ok().body(result);
		}
		catch(Exception e) {
			System.out.print(e.getMessage());
			result.put("error",e.getMessage());
			return ResponseEntity.ok().body(result);
		}		
	}
	
	@PostMapping("/tasks/new")
	public ResponseEntity<Map<String,Object>> createTask(RequestEntity<String> request) {
		
		HttpHeaders header = request.getHeaders();
		String JWTtoken = header.getFirst("token");
		System.out.println("true");
		Map<String,Object> result = new HashMap<>();
		
		try {
			Jws<Claims> claims = token.parseToken(JWTtoken);
			String user_id = claims.getBody().get("_id", String.class);
			
			TaskItem task = objectMapper.readValue(request.getBody(), TaskItem.class);
			task.setUser_id(user_id);
			
			taskService.saveTask(task);
			result.put("task", task);
			
			return ResponseEntity.ok().body(result);
		}
		catch(Exception e) {
			System.out.print(e.getMessage());
			result.put("error",e.getMessage());
			return ResponseEntity.ok().body(result);
		}
		
	}
	
	@DeleteMapping("/tasks/{id}/delete")
	public ResponseEntity<Map<String,Object>>
	       deleteTask(RequestEntity<String> request,@PathVariable String id) {
		
		HttpHeaders header = request.getHeaders();
		String JWTtoken = header.getFirst("token");
		
		Map<String,Object> result = new HashMap<>();
		
		try {
			token.parseToken(JWTtoken);
			taskService.deleteTaskbyId(id);
			return ResponseEntity.ok().body(result);
			
		}
		catch(Exception e) {
			System.out.print(e.getMessage());
			result.put("error",e.getMessage());
			return ResponseEntity.ok().body(result);
		}
		
	}
	
	@PutMapping("/tasks/{id}/update")
	public ResponseEntity<Map<String, Object>> 
	       updateTask(RequestEntity<String> request,@PathVariable String id) {
		
		HttpHeaders header = request.getHeaders();
		String JWTtoken = header.getFirst("token");
		
		Map<String,Object> result = new HashMap<>();
		
		try {
			token.parseToken(JWTtoken);
			
			TaskItem task = objectMapper.readValue(request.getBody(), TaskItem.class);
			taskService.updateTask(task);
		
			result.put("task", task);
			return ResponseEntity.ok().body(result);
			
		}
		catch(Exception e) {
			System.out.print(e.getMessage());
			result.put("error",e.getMessage());
			return ResponseEntity.ok().body(result);
		}
	}
}