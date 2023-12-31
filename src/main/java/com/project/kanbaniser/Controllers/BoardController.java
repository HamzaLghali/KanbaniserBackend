package com.project.kanbaniser.Controllers;

import com.project.kanbaniser.Entities.Board;
import com.project.kanbaniser.Entities.Task;
import com.project.kanbaniser.Entities.User;
import com.project.kanbaniser.Services.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/v1/boards")
@AllArgsConstructor
public class BoardController {

	private final BoardService boardService;

	@PostMapping
	public ResponseEntity<Void> addBoard(@RequestBody Board board) {
		boardService.addBoard(board);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{boardId}")
	public ResponseEntity<Void> deleteBoard(@PathVariable int boardId) {
		boardService.deleteBoard(boardId);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{boardId}")
	public ResponseEntity<Void> updateBoard(@PathVariable int boardId, @RequestBody Board board) {
		boardService.updateBoard(boardId, board);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/{boardId}/users/{userId}")
	public ResponseEntity<Void> addUserToBoard(@PathVariable int userId, @PathVariable int boardId) {
		boardService.addUserToBoard(userId, boardId);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{boardId}/users/{userId}")
	public ResponseEntity<Void> removeUserFromBoard(@PathVariable int userId, @PathVariable int boardId) {
		boardService.removeUserFromBoard(userId, boardId);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/{boardId}/tasks/{taskId}")
	public ResponseEntity<Void> addTaskToBoard(@PathVariable int taskId, @PathVariable int boardId) {
		boardService.addTaskToBoard(taskId, boardId);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{boardId}/tasks/{taskId}")
	public ResponseEntity<Void> removeTaskFromBoard(@PathVariable int taskId, @PathVariable int boardId) {
		boardService.removeTaskFromBoard(taskId, boardId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{boardId}/users")
	public ResponseEntity<List<User>> getAllUsersInBoard(@PathVariable int boardId) {
		List<User> users = boardService.getAllUsersInBoard(boardId);
		return ResponseEntity.ok(users);
	}

	@GetMapping("/{boardId}/tasks")
	public ResponseEntity<List<Task>> getAllTasksInBoard(@PathVariable int boardId) {
		List<Task> tasks = boardService.getAllTasksInBoard(boardId);
		return ResponseEntity.ok(tasks);
	}

}
