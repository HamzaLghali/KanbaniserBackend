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

}

