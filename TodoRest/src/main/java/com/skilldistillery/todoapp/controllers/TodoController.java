package com.skilldistillery.todoapp.controllers;

import java.security.Principal;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.todoapp.entities.Todo;
import com.skilldistillery.todoapp.services.TodoService;

@RestController
@RequestMapping(path = "api")
@CrossOrigin({ "*", "http://localhost:4200" })
public class TodoController {

	@Autowired
	private TodoService svc;
	
	@GetMapping("todos")
	public Set<Todo> index(HttpServletRequest req, HttpServletResponse resp, Principal principal) {
		return svc.index(principal.getName());
	}

	@GetMapping("todos/{tid}")
	public Todo show(HttpServletRequest req, HttpServletResponse resp, @PathVariable("tid") int tid, Principal principal) {
		return svc.show(principal.getName(), tid);
	}

	@PostMapping("todos")
	public Todo create(HttpServletRequest req, HttpServletResponse resp, @RequestBody Todo todo, Principal principal) {
		
		System.out.println(" New TODO Posted ***********************************************");
	if (todo.getId() != 0) { //  && principal.getName() owns the todo
		resp.setStatus(406);
		return todo;
	}
	
	todo = svc.create(principal.getName(), todo);
	
	if (todo != null) {
		resp.setStatus(201);
		StringBuffer sb = req.getRequestURL();
		sb.append("/").append(todo.getId());
		resp.addHeader("Location",  sb.toString());
	}
	return todo;
	}

	@PutMapping("todos/{tid}")
	public Todo update(HttpServletRequest req, HttpServletResponse resp, @PathVariable("tid") int tid, @RequestBody Todo todo, Principal principal) {
		todo = svc.update(principal.getName(), tid, todo);
		if (todo == null) {
			resp.setStatus(404);
		}
		return todo;

	}

	@DeleteMapping("todos/{tid}")
	public boolean destroy(HttpServletRequest req, HttpServletResponse resp, @PathVariable("tid") int tid, Principal principal) {
		Boolean success =svc.destroy(principal.getName(), tid); 
		if(!success) {
			resp.setStatus(404);
		}
		return success;
	}

}
