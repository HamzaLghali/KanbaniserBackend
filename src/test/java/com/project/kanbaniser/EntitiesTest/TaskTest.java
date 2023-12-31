package com.project.kanbaniser.EntitiesTest;

import static org.junit.jupiter.api.Assertions.*;

import com.project.kanbaniser.Entities.Board;
import com.project.kanbaniser.Entities.Task;
import com.project.kanbaniser.Entities.User;
import org.junit.jupiter.api.Test;
import java.util.Date;

public class TaskTest {

	@Test
	public void testTaskGettersAndSetters() {
		Task task = new Task();
		User user = new User();
		Board board = new Board();
		Date now = new Date();

		task.setId(1);
		task.setTitle("Sample Task");
		task.setDescription("This is a sample task description");
		task.setCreatedAt(now);
		task.setCreatedBy(user);
		task.setBoard(board);

		assertEquals(1, task.getId());
		assertEquals("Sample Task", task.getTitle());
		assertEquals("This is a sample task description", task.getDescription());
		assertEquals(now, task.getCreatedAt());
		assertEquals(user, task.getCreatedBy());
		assertEquals(board, task.getBoard());
	}

	@Test
	public void testTaskEqualsHashCodeToStringAndConstructor() {
		User user = new User(); // Assuming User has an empty constructor
		Board board = new Board(); // Assuming Board has an empty constructor
		Date date = new Date();
		Task task1 = new Task(1, "Task 1", "Description 1", date, user, board);
		Task task2 = new Task(1, "Task 1", "Description 1", date, user, board);
		Task task3 = new Task(2, "Task 2", "Description 2", new Date(), user, board);

		// equals and hashCode
		assertEquals(task1, task2);
		assertNotEquals(task1, task3);
		assertEquals(task1.hashCode(), task2.hashCode());
		assertNotEquals(task1.hashCode(), task3.hashCode());

		// toString
		assertNotNull(task1.toString());
		assertTrue(task1.toString().contains("Task 1"));


	}

}

