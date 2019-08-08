package com.skilldistillery.todoapp.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.todoapp.entities.Todo;

class TodoTests {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Todo todo;

	@BeforeAll
	public static void setupAll() {
		emf = Persistence.createEntityManagerFactory("Todos");
	}
	
	@AfterAll
	public static void teardownAll() {
		emf.close();
	}
	
	@BeforeEach
	public void setUp() {
		em = emf.createEntityManager();
		todo = em.find(Todo.class, 1);
	}

	@AfterEach
	public void tearDown() {
		em.close();
		todo = null;
	}
	
	@Test
	public void test_Todo_getTodo() {
		assertEquals("Go round Mum's", todo.getTask());
	}

}
