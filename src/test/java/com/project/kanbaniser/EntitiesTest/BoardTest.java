package com.project.kanbaniser.EntitiesTest;
import static org.junit.jupiter.api.Assertions.*;

import com.project.kanbaniser.Entities.Board;
import com.project.kanbaniser.Entities.Task;
import com.project.kanbaniser.Entities.User;
import org.junit.jupiter.api.Test;
import java.util.Collections;

public class BoardTest {

	@Test
	public void testBoardGettersAndSetters() {
		Board board = new Board();
		User user = new User();
		Task task = new Task();

		board.setId(1);
		board.setTitle("Project Management Board");
		board.setDescription("Board for managing software development projects");
		board.setMembers(Collections.singletonList(user));
		board.setTasks(Collections.singletonList(task));

		assertEquals(1, board.getId());
		assertEquals("Project Management Board", board.getTitle());
		assertEquals("Board for managing software development projects", board.getDescription());
		assertTrue(board.getMembers().contains(user));
		assertTrue(board.getTasks().contains(task));
	}

}

