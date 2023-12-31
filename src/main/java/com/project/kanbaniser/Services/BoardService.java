package com.project.kanbaniser.Services;

import com.project.kanbaniser.Entities.Board;
import com.project.kanbaniser.Entities.Task;
import com.project.kanbaniser.Entities.User;
import com.project.kanbaniser.Exceptions.BoardNotFoundException;
import com.project.kanbaniser.Exceptions.TaskNotFoundException;
import com.project.kanbaniser.Exceptions.UserNotFoundException;
import com.project.kanbaniser.Repositories.BoardRepository;
import com.project.kanbaniser.Repositories.TaskRepository;
import com.project.kanbaniser.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class BoardService {


	private final BoardRepository boardRepository;
	private final UserRepository userRepository;
	private final TaskRepository taskRepository;

	public void addBoard(Board board) {
		boardRepository.save(board);
	}

	public void deleteBoard(int id) {
		if(!boardRepository.existsById(id)) {
			throw new BoardNotFoundException("Board with id "+id+" does not exist");
		}
		boardRepository.deleteById(id);
	}

	public void updateBoard(int id, Board board) {
		if(!boardRepository.existsById(id)) {
			throw new BoardNotFoundException("Board with id "+id+" does not exist");
		}
		board.setTitle(board.getTitle());
		board.setDescription(board.getDescription());
		board.setMembers(board.getMembers());
		board.setTasks(board.getTasks());
		boardRepository.save(board);
	}

	@Transactional
	public void addUserToBoard(int userId, int boardId) {

		// Find the user by ID
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

		// Find the board by ID
		Board board = boardRepository.findById(boardId)
				.orElseThrow(() -> new BoardNotFoundException("Board not found with id: " + boardId));

		Task task = taskRepository.findById(boardId)
				.orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + boardId));

		// Add the user to the board's user list
		board.getMembers().add(user);

		// Save the updated board
		boardRepository.save(board);
	}

	//optionnel
	@Transactional
	public void removeUserFromBoard(int userId, int boardId) {

		// Find the user by ID
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

		// Find the board by ID
		Board board = boardRepository.findById(boardId)
				.orElseThrow(() -> new BoardNotFoundException("Board not found with id: " + boardId));

		// Remove the user from the board's user list
		board.getMembers().remove(user);

		// Save the updated board
		boardRepository.save(board);
	}

	@Transactional
	public void addTaskToBoard(int taskId, int boardId) {

		// Find the task by ID
		Task task = taskRepository.findById(taskId)
				.orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + taskId));

		// Find the board by ID
		Board board = boardRepository.findById(boardId)
				.orElseThrow(() -> new BoardNotFoundException("Board not found with id: " + boardId));

		// Add the task to the board's task list
		board.getTasks().add(task);

		// Save the updated board
		boardRepository.save(board);
	}

	//optionnel
	@Transactional
	public void removeTaskFromBoard(int taskId, int boardId) {

		// Find the task by ID
		Task task = taskRepository.findById(taskId)
				.orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + taskId));

		// Find the board by ID
		Board board = boardRepository.findById(boardId)
				.orElseThrow(() -> new BoardNotFoundException("Board not found with id: " + boardId));

		// Remove the task from the board's task list
		board.getTasks().remove(task);

		// Save the updated board
		boardRepository.save(board);


	}

	//liste des utilisateurs du board
	public List<User> getAllUsersInBoard(int boardId) {
		Board board = boardRepository.findById(boardId)
				.orElseThrow(() -> new BoardNotFoundException("Board not found with id: " + boardId));
		return board.getMembers();
	}

	//liste des tasks du board
	public List<Task> getAllTasksInBoard(int boardId) {
		Board board = boardRepository.findById(boardId)
				.orElseThrow(() -> new BoardNotFoundException("Board not found with id: " + boardId));
		return board.getTasks();
	}

}
