package com.project.kanbaniser.RepositoriesTest;

import com.project.kanbaniser.Entities.Task;
import com.project.kanbaniser.Entities.Board;
import com.project.kanbaniser.Repositories.TaskRepository;
import com.project.kanbaniser.Repositories.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TaskRepositoryTest {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private BoardRepository boardRepository;

	@Test
	public void testCreateReadDelete() {
		// Create a board to associate with tasks
		Board board = new Board();
		board.setTitle("Test Board");
		board.setDescription("Test Description");
		board = boardRepository.save(board);

		// Create a task
		Task task = new Task();
		task.setTitle("Test Task");
		task.setDescription("Test Description");
		task.setCreatedAt(new Date());
		task.setBoard(board);

		// Save the task to the repository
		task = taskRepository.save(task);

		// Assert that the task was saved correctly
		assertThat(task.getId()).isNotNull();
		assertThat(task.getTitle()).isEqualTo("Test Task");
		assertThat(task.getDescription()).isEqualTo("Test Description");
		assertThat(task.getBoard().getId()).isEqualTo(board.getId());

		// Retrieve the task from the repository
		Task foundTask = taskRepository.findById(task.getId()).orElse(null);
		assertThat(foundTask).isNotNull();
		assertThat(foundTask.getTitle()).isEqualTo("Test Task");
		assertThat(foundTask.getDescription()).isEqualTo("Test Description");
		assertThat(foundTask.getBoard().getId()).isEqualTo(board.getId());

		// Optionally, test update operations here
		// ...

		// Delete the task
		taskRepository.delete(task);

		// Verify that the task no longer exists in the repository
		assertThat(taskRepository.findById(task.getId())).isEmpty();
	}
}
