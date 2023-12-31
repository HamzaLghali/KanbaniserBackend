package com.project.kanbaniser.ServicesTest;

import com.project.kanbaniser.Entities.Board;
import com.project.kanbaniser.Entities.Task;
import com.project.kanbaniser.Exceptions.BoardNotFoundException;
import com.project.kanbaniser.Exceptions.TaskNotFoundException;
import com.project.kanbaniser.Repositories.BoardRepository;
import com.project.kanbaniser.Repositories.TaskRepository;
import com.project.kanbaniser.Services.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


public class TaskServiceTest {

	@Mock
	private BoardRepository boardRepository;

	@Mock
	private TaskRepository taskRepository;

	@InjectMocks
	private TaskService taskService;

	public TaskServiceTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void createTaskInBoardTest() {
		Task task = createTask(1, "Task 1", "Task Description");
		int boardId = 1;
		Board board = new Board("Test Board", "Test Description");

		when(boardRepository.findById(boardId)).thenReturn(java.util.Optional.of(board));
		when(taskRepository.save(task)).thenReturn(task);

		taskService.createTaskInBoard(task, boardId);

		assertThat(task.getBoard()).isEqualTo(board);
		verify(taskRepository, times(1)).save(task);
	}

	@Test
	void removeTaskFromBoardTest() {
		Task task = createTask(1, "Task 1", "Task Description");
		int boardId = 1;
		Board board = new Board("Test Board", "Test Description");

		when(boardRepository.findById(boardId)).thenReturn(java.util.Optional.of(board));

		taskService.removeTaskFromBoard(task, boardId);

		verify(taskRepository, times(1)).delete(task);
	}

	@Test
	void createTaskTest() {
		Task task = createTask(1, "Task 1", "Task Description");
		when(taskRepository.save(task)).thenReturn(task);

		taskService.createTask(task);

		verify(taskRepository, times(1)).save(task);
	}

	@Test
	void deleteTaskTest() {
		int taskId = 1;

		taskService.deleteTask(taskId);

		verify(taskRepository, times(1)).deleteById(taskId);
	}

	@Test
	void updateTaskTest() {
		int taskId = 1;
		Task existingTask = createTask(1, "Task 1", "Task Description");
		when(taskRepository.existsById(taskId)).thenReturn(true);
		when(taskRepository.findById(taskId)).thenReturn(java.util.Optional.of(existingTask));

		Task updatedTask = createTask(1, "Updated Task", "Updated Description");
		taskService.updateTask(taskId, updatedTask);

		assertThat(existingTask).isEqualTo(updatedTask);
	}

	@Test
	void assignTaskToBoardTest() {
		int taskId = 1;
		int boardId = 1;
		Task task = createTask(1, "Task 1", "Task Description");
		Board board = new Board("Test Board", "Test Description");

		when(taskRepository.findById(taskId)).thenReturn(java.util.Optional.of(task));
		when(boardRepository.findById(boardId)).thenReturn(java.util.Optional.of(board));

		taskService.assignTaskToBoard(taskId, boardId);

		assertThat(task.getBoard()).isEqualTo(board);
		verify(taskRepository, times(1)).save(task);
	}

	@Test
	void getAllTasksTest() {
		List<Task> tasks = new ArrayList<>();
		when(taskRepository.findAll()).thenReturn(tasks);

		List<Task> result = taskService.getAllTasks();

		assertThat(result).isEqualTo(tasks);
	}

	@Test
	void getTaskByIdTest() {
		int taskId = 1;
		Task task = createTask(1, "Task 1", "Task Description");
		when(taskRepository.findById(taskId)).thenReturn(java.util.Optional.of(task));

		Task result = taskService.getTaskById(taskId);

		assertThat(result).isEqualTo(task);
	}

	// Add more test methods as needed

	private Task createTask(int id, String title, String description) {
		Task task = new Task();
		task.setId(id);
		task.setTitle(title);
		task.setDescription(description);
		return task;
	}

}
