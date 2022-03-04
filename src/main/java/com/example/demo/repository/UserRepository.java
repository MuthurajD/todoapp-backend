package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.dao.UserItem;

public interface UserRepository extends MongoRepository<UserItem,String>{
	Optional<UserItem> findByEmail(String email);
	Optional<UserItem> findByEmailAndPassword(String email,String password);
}