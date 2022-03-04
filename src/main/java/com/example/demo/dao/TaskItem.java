package com.example.demo.dao;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.example.demo.utils.Status;

@Document("tasks")
public class TaskItem {
	private String _id;
	private String description;
	private Status status;
	
	@DocumentReference
	private UserItem userItem;
	
	public TaskItem() {};
	
	public TaskItem(String _id, String description, Status status, UserItem userItem) {
		this._id = _id;
		this.description = description;
		this.status = status;
		this.userItem = userItem;
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

	public UserItem getUserItem() {
		return userItem;
	}

	public void setUserItem(UserItem userItem) {
		this.userItem = userItem;
	}

}
