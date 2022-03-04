package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.dao.TaskItem;
import com.example.demo.dao.UserItem;

public interface TaskRepository extends CrudRepository<TaskItem,String>{
	 List<TaskItem> findAllByUserItem(UserItem userItem);
}