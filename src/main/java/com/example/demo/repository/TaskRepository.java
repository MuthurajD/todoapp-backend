package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.dao.TaskItem;

public interface TaskRepository extends MongoRepository<TaskItem,String>{
	 
	 @Query(value="{'user_id':?0}")
	 List<TaskItem> findAllByUser_id(String id);
}