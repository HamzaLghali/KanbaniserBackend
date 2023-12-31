package com.project.kanbaniser.RepositoriesTest;

import com.project.kanbaniser.Entities.Board;
import com.project.kanbaniser.Entities.User; // Import your User entity
import com.project.kanbaniser.Entities.Task; // Import your Task entity
import com.project.kanbaniser.Repositories.BoardRepository; // Import your Board repository

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

// ... other imports ...

@DataJpaTest
public class BoardRepositoryTest {

	@Autowired
	private BoardRepository boardRepository;

	@Test
	public void testCreateReadDelete() {
		Board board = new Board();
		board.setTitle("Test Board");
		board.setDescription("Test Description");

		// Mock some User entities
		User user1 = new User();
		user1.setFirstname("Alice");
		user1.setLastname("kiki");
		user1.setEmail("alicekiki@gmail.com");
		user1.setPassword("password");

		User user2 = new User();
		user2.setFirstname("Bob");
		user2.setLastname("Doe");
		user2.setEmail("bobdoe@gmail.com");
		user2.setPassword("password");



		// Add these users to the board
		board.setMembers(Arrays.asList(user1, user2));

		// Mock some Task entities
		Task task1 = new Task();
		task1.setDescription("Task 1");
		// Set other properties and associate with the board
		task1.setBoard(board);

		Task task2 = new Task();
		task2.setDescription("Task 2");
		// Set other properties and associate with the board
		task2.setBoard(board);

		// Add these tasks to the board
		board.setTasks(Arrays.asList(task1, task2));

		// Save the board to the repository
		board = boardRepository.save(board);

		// Assert that the board was saved correctly
		assertThat(board.getId()).isNotNull();
		assertThat(board.getTitle()).isEqualTo("Test Board");
		assertThat(board.getDescription()).isEqualTo("Test Description");

		// Assert that members and tasks are saved correctly
		assertThat(board.getMembers()).hasSize(2);
		assertThat(board.getTasks()).hasSize(2);

		// Retrieve the board from the repository
		Board foundBoard = boardRepository.findById(board.getId()).orElse(null);
		assertThat(foundBoard).isNotNull();
		assertThat(foundBoard.getMembers()).hasSize(2);
		assertThat(foundBoard.getTasks()).hasSize(2);

		// Optionally, test update operations here
		// ...

		// Delete the board
		boardRepository.delete(board);

		// Verify that the board no longer exists in the repository
		assertThat(boardRepository.findById(board.getId())).isEmpty();

	}
}
