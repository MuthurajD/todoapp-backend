package com.example.demo.dto;

import com.example.demo.utils.Token;
import com.example.demo.dao.TaskItem;

public class TaskTokenDto {
	Token token;
	TaskItem task;
	
	public TaskTokenDto() {};
	
	public TaskTokenDto(Token token, TaskItem task) {
		this.token = token;
		this.task = task;
	}
	public Token getToken() {
		return token;
	}
	public void setToken(Token token) {
		this.token = token;
	}
	public TaskItem getTask() {
		return task;
	}
	public void setTask(TaskItem task) {
		this.task = task;
	}
	
}
