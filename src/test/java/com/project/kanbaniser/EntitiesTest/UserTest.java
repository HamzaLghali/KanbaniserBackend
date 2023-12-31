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


	@Test
	public void testEqualsAndHashCode() {
		User user1 = new User(1, "John", "Doe", "1234567890", "john.doe@example.com", "password", null, null);
		User user2 = new User(1, "John", "Doe", "1234567890", "john.doe@example.com", "password", null, null);
		User user3 = new User(2, "Jane", "Doe", "0987654321", "jane.doe@example.com", "password", null, null);

		assertEquals(user1, user2);
		assertNotEquals(user1, user3);
		assertEquals(user1.hashCode(), user2.hashCode());
		assertNotEquals(user1.hashCode(), user3.hashCode());
	}

	@Test
	public void testToString() {
		User user = new User(1, "John", "Doe", "1234567890", "john.doe@example.com", "password", null, null);
		String userString = user.toString();
		assertTrue(userString.contains("John"));
		assertTrue(userString.contains("Doe"));
		assertTrue(userString.contains("1234567890"));
		//assertTrue(userString.contains("johndoe@gmail.com"));
		assertTrue(userString.contains("password"));

		// Verify all relevant information is included in the string
	}

	@Test
	public void testConstructor() {
		User user = new User(1, "John", "Doe", "1234567890", "john.doe@example.com", "password", null, null);
		assertEquals(1, user.getUserId());
		assertEquals("John", user.getFirstname());
		assertEquals("Doe", user.getLastname());
		assertEquals("1234567890", user.getPhoneNumber());
		//assertEquals("johndoe@gmail.com", user.getEmail());
		assertEquals("password", user.getPassword());


	}
}
