package com.project.kanbaniser.EntitiesTest;

import static org.junit.jupiter.api.Assertions.*;

import com.project.kanbaniser.Entities.User;
import org.junit.jupiter.api.Test;
import java.util.Collections;

public class UserTest {

	@Test
	public void testUserGettersAndSetters() {
		User user = new User();
		user.setUserId(1);
		user.setFirstname("John");
		user.setLastname("Doe");
		user.setPhoneNumber("1234567890");
		user.setEmail("john.doe@example.com");
		user.setPassword("password123");
		user.setBoards(Collections.emptyList());
		user.setTasks(Collections.emptyList());

		assertEquals(1, user.getUserId());
		assertEquals("John", user.getFirstname());
		assertEquals("Doe", user.getLastname());
		assertEquals("1234567890", user.getPhoneNumber());
		assertEquals("john.doe@example.com", user.getEmail());
		assertEquals("password123", user.getPassword());
		assertTrue(user.getBoards().isEmpty());
		assertTrue(user.getTasks().isEmpty());
	}

}
