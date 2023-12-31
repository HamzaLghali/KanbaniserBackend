package com.project.kanbaniser.Controllers;

import com.project.kanbaniser.Entities.Task;
import com.project.kanbaniser.Services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/v1/tasks")
@AllArgsConstructor
public class TaskController {

	private final TaskService taskService;

	@PostMapping("/create")
	public ResponseEntity<Task> createTask(@RequestBody Task task) {
		taskService.createTask(task);
		return ResponseEntity.ok(task);
	}

	@PostMapping("/createInBoard/{boardId}")
	public ResponseEntity<Task> createTaskInBoard(@RequestBody Task task, @PathVariable int boardId) {
		taskService.createTaskInBoard(task, boardId);
		return ResponseEntity.ok(task);
	}

	@PutMapping("/{taskId}/assignToBoard/{boardId}")
	public ResponseEntity<Void> assignTaskToBoard(@PathVariable int taskId, @PathVariable int boardId) {
		taskService.assignTaskToBoard(taskId, boardId);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{taskId}")
	public ResponseEntity<Void> deleteTask(@PathVariable int taskId) {
		taskService.deleteTask(taskId);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/update/{taskId}")
	public ResponseEntity<Void> updateTask(@PathVariable int taskId, @RequestBody Task task) {
		taskService.updateTask(taskId, task);
		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<List<Task>> getAllTasks() {
		List<Task> tasks = taskService.getAllTasks();
		return ResponseEntity.ok(tasks);
	}

	@GetMapping("/{taskId}")
	public ResponseEntity<Task> getTaskById(@PathVariable int taskId) {
		Task task = taskService.getTaskById(taskId);
		return ResponseEntity.ok(task);
	}


}
