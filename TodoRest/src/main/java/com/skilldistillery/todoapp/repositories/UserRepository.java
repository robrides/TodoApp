package com.skilldistillery.todoapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.todoapp.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

//	Set<Todo> findByTodos_user_id(int id);
	User findByUsername(String username);
	
}
