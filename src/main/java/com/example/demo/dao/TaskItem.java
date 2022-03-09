package com.example.demo.dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.utils.Status;


@Document("tasks")
public class TaskItem {
	@Id
	private String _id;
	private String description;
	private Status status;
	private String user_id;
	
	public TaskItem() {
		super();
	}

	public TaskItem(String _id, String description, Status status, String user_id) {
		super();
		this._id = _id;
		this.description = description;
		this.status = status;
		this.user_id = user_id;
	}
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}	
	
}