package com.project.kanbaniser.ServicesTest;

import com.project.kanbaniser.Entities.Board;
import com.project.kanbaniser.Entities.Task;
import com.project.kanbaniser.Entities.User;
import com.project.kanbaniser.Repositories.BoardRepository;
import com.project.kanbaniser.Repositories.TaskRepository;
import com.project.kanbaniser.Repositories.UserRepository;
import com.project.kanbaniser.Services.BoardService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BoardServiceTest {

	@Mock
	private BoardRepository boardRepository;

	@Mock
	private TaskRepository taskRepository;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private BoardService boardService;

	@Test
	void addUserToBoardTest() {
		int userId = 1;
		int boardId = 1;
		User user = new User(userId, "Imad", "Maailil", "1234567890", "imadmaailil@gmail.com", "password", new ArrayList<>(), new ArrayList<>());
		Board board = new Board("Test Board", "Test Description");
		when(userRepository.findById(userId)).thenReturn(Optional.of(user));
		when(boardRepository.findById(boardId)).thenReturn(Optional.of(board));

		boardService.addUserToBoard(userId, boardId);

		assertThat(board.getMembers()).contains(user);
	}

	@Test
	void removeUserFromBoardTest() {
		int userId = 1;
		int boardId = 1;
		User user = new User(userId, "Imad", "Maailil", "1234567890", "imadmaailil@gmail.com", "password", new ArrayList<>(), new ArrayList<>());
		Board board = new Board(boardId,"Test Board", "Test Description", Arrays.asList(user), new ArrayList<>());
		when(userRepository.findById(userId)).thenReturn(Optional.of(user));
		when(boardRepository.findById(boardId)).thenReturn(Optional.of(board));

		boardService.removeUserFromBoard(userId, boardId);

		assertThat(board.getMembers()).doesNotContain(user);
	}

	@Test
	void addTaskToBoardTest() {
		int taskId = 1;
		int boardId = 1;
		Task task = createTask(taskId, "Task 1", "Task Description");
		Board board = new Board("Test Board", "Test Description");
		when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
		when(boardRepository.findById(boardId)).thenReturn(Optional.of(board));

		boardService.addTaskToBoard(taskId, boardId);

		assertThat(board.getTasks()).contains(task);
	}

	@Test
	void removeTaskFromBoardTest() {
		int taskId = 1;
		int boardId = 1;
		Task task = createTask(taskId, "Task 1", "Task Description");
		Board board = new Board("Test Board", "Test Description");
		when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
		when(boardRepository.findById(boardId)).thenReturn(Optional.of(board));

		boardService.removeTaskFromBoard(taskId, boardId);

		assertThat(board.getTasks()).doesNotContain(task);
	}

	private Task createTask(int id, String title, String description) {
		Task task = new Task();
		task.setId(id);
		task.setTitle(title);
		task.setDescription(description);
		task.setCreatedAt(new Date());
		task.setCreatedBy(new User());
		task.setBoard(new Board());
		return task;
	}

	@Test
	void getAllUsersInBoardTest() {
		int boardId = 1;
		List<User> users = Arrays.asList(new User(1, "Imad", "Maailil", "1234567890", "imadmaailil@gmail.com", "password", new ArrayList<>(), new ArrayList<>()));
		when(boardRepository.findById(boardId)).thenReturn(Optional.of(new Board(boardId,"Test Board", "Test Description", users, new ArrayList<>())));
		when(userRepository.findAllByBoards_Id(boardId)).thenReturn(users);

		List<User> result = boardService.getAllUsersInBoard(boardId);

		assertThat(result).isEqualTo(users);
	}

	@Test
	void getAllTasksInBoardTest() {
		int boardId = 1;
		List<Task> tasks = Arrays.asList(createTask(1, "Task 1", "Task Description"));
		when(boardRepository.findById(boardId)).thenReturn(Optional.of(new Board(boardId, "Test Board", "Test Description", new ArrayList<>(), tasks)));
		when(taskRepository.findAllByBoard_Id(boardId)).thenReturn(tasks);

		List<Task> result = boardService.getAllTasksInBoard(boardId);

		assertThat(result).isEqualTo(tasks);
	}
}
