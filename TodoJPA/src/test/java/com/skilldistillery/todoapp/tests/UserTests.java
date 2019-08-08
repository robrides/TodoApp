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

import com.skilldistillery.todoapp.entities.User;

class UserTests {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;

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
		user = em.find(User.class, 1);
	}

	@AfterEach
	public void tearDown() {
		em.close();
		user = null;
	}
	
	@Test
	public void test_User_getUsername() {
		assertEquals("shaun", user.getUsername());
	}

}
