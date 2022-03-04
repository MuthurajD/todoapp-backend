package com.example.demo.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TaskItem;
import com.example.demo.dao.UserItem;
import com.example.demo.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	TaskRepository taskRepository ;
	
	@Autowired
	UserService userService;
	
	public List<TaskItem> findAllByUserItemId(String id){
		Optional<UserItem> userItem = userService.getUserById(id);
		if (userItem.isEmpty()) return List.of();
		
		return taskRepository.findAllByUserItem(userItem.get());
	}
	
	public TaskItem saveTask(TaskItem task) {
		return taskRepository.save(task);
	}
	
	
	public void deleteTaskbyId(String id) {
		taskRepository.deleteById(id);
	}
	
	public TaskItem updateTask(TaskItem task,String id) {
		task.set_id(id);
		return taskRepository.save(task);
	}
}
