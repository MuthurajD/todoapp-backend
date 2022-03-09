package com.example.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TaskItem;
import com.example.demo.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	TaskRepository taskRepository ;
	
	@Autowired
	UserService userService;
	
	public List<TaskItem> findAllByUserItemId(String id){
		return taskRepository.findAllByUser_id(id);
	}
	
	public TaskItem saveTask(TaskItem task) {
		return taskRepository.save(task);
	}
	
	
	public void deleteTaskbyId(String id) {
		taskRepository.deleteById(id);
	}
	
	public TaskItem updateTask(TaskItem task) {
		return taskRepository.save(task);
	}
}
