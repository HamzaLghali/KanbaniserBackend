package com.project.kanbaniser.Controllers;

import com.project.kanbaniser.Entities.Board;
import com.project.kanbaniser.Entities.Task;
import com.project.kanbaniser.Entities.User;
import com.project.kanbaniser.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/v1/users")
@AllArgsConstructor
public class UserController {

	private final UserService userService;


	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}

	@PostMapping
	public ResponseEntity<User> addUser(@RequestBody User user) {
		userService.addUser(user);
		return ResponseEntity.ok(user);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateUser(@PathVariable int id, @RequestBody User user) {
		userService.updateUser(id, user);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id) {
		User user = userService.getUserById(id);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
		User user = userService.getUserByEmail(email);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/{id}/boards")
	public ResponseEntity<List<Board>> getBoardsByUserId(@PathVariable int id) {
		List<Board> boards = userService.getBoardsByUserId(id);
		return ResponseEntity.ok(boards);
	}

	@GetMapping("/{id}/tasks")
	public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable int id) {
		List<Task> tasks = userService.getTasksByUserId(id);
		return ResponseEntity.ok(tasks);
	}
}
