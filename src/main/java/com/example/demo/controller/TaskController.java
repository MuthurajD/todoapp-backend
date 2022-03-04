package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.TaskItem;
import com.example.demo.dao.UserItem;
import com.example.demo.dto.TaskTokenDto;
import com.example.demo.dto.TokenDto;
import com.example.demo.service.TaskService;
import com.example.demo.service.UserService;
import com.example.demo.utils.Status;
import com.example.demo.utils.Token;

@RestController
public class TaskController {
	
	@Autowired
	TaskService taskService;
	@Autowired
	UserService userService;
	
	@PostMapping("/tasks")
	public List<TaskItem> getTasksByUserId(@RequestBody TokenDto tokenDto){
		System.out.println("ReachedHere");
		Token token = tokenDto.getToken();
		
		if (!Token.verify(token)) return List.of();
		
		return taskService.findAllByUserItemId(token.getId());
		
	}
	
	@PostMapping("/tasks/new")
	public TaskItem createTask(@RequestBody TaskTokenDto taskTokenDto) {
		Token token = taskTokenDto.getToken();
		TaskItem task = taskTokenDto.getTask();
		
		if(!Token.verify(token)) return null;
		
		Optional<UserItem> user = userService.getUserById(token.getId());
		if (user.isEmpty()) return null;
		
		task.setStatus(Status.INPROGRESS);
		task.setUserItem(user.get());
	
		return taskService.saveTask(task);
	}
	
	@DeleteMapping("/tasks/{id}/delete")
	public void deleteTask(@PathVariable String id,@RequestBody TokenDto tokenDto) {
		Token token = tokenDto.getToken();
		if (!Token.verify(token)) return;
	
		
		taskService.deleteTaskbyId(id);
	}
	
	@PutMapping("/tasks/{id}/update")
	public TaskItem updateTask(@PathVariable String id,@RequestBody TaskTokenDto taskTokenDto) {
		Token token = taskTokenDto.getToken();
		TaskItem task = taskTokenDto.getTask();
		
		if (!Token.verify(token)) return null;
		
		
		return taskService.updateTask(task, id);
	}
}
