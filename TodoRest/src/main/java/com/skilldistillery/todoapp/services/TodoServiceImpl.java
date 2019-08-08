package com.skilldistillery.todoapp.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.todoapp.entities.Todo;
import com.skilldistillery.todoapp.entities.User;
import com.skilldistillery.todoapp.repositories.TodoRepository;
import com.skilldistillery.todoapp.repositories.UserRepository;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired 
	TodoRepository tRepo;
	
	@Autowired
	UserRepository uRepo;

	@Override
	public Set<Todo> index(String username) {
//		User thisUser = uRepo.findByUsername(username);
		return tRepo.findByUser_username(username);
	}

	@Override
	public Todo show(String username, int tid) {
		return tRepo.findByIdAndUser_username(tid, username);

	}

	@Override
	public Todo create(String username, Todo todo) {
		User u = uRepo.findByUsername(username);
		todo.setUser(u);
		return tRepo.saveAndFlush(todo);
	}

	@Override
	public Todo update(String username, int tid, Todo todo) {
		Optional<Todo> opt = tRepo.findById(tid);
		if (opt.isPresent()) {
			todo.setId(tid);
			return tRepo.saveAndFlush(todo);
		}
		else {
			return null;
		}
	}

	@Override
	public boolean destroy(String username, int tid) {
		Boolean success = false;
		if(tRepo.existsById(tid)) {
			try {
				tRepo.deleteById(tid);
				success = true;
			} catch (Exception e) {
				System.err.println(e);
				success = null;
			}
		}
		return success;
	}


	
	
	
}
