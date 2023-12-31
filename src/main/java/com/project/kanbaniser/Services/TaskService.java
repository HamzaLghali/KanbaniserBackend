package com.project.kanbaniser.Services;

import com.project.kanbaniser.Entities.Board;
import com.project.kanbaniser.Entities.Task;
import com.project.kanbaniser.Exceptions.BoardNotFoundException;
import com.project.kanbaniser.Exceptions.TaskNotFoundException;
import com.project.kanbaniser.Repositories.BoardRepository;
import com.project.kanbaniser.Repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TaskService {

	private final BoardRepository boardRepository;
	private final TaskRepository taskRepository;

	//creation de task dans board
	@Transactional
	public void createTaskInBoard(Task task, int boardId) {
		Board board = boardRepository.findById(boardId)
				.orElseThrow(() -> new BoardNotFoundException("Board not found"));
		task.setBoard(board);
		taskRepository.save(task);
	}

	@Transactional
	public void removeTaskFromBoard(Task task, int boardId) {
		Board board = boardRepository.findById(boardId)
				.orElseThrow(() -> new BoardNotFoundException("Board not found"));
		task.setBoard(board);
		taskRepository.delete(task);
	}


	//creation de task
	public void createTask(Task task) {
		taskRepository.save(task);
	}

	//suppression de task
	public void deleteTask(int id) {
		if(!taskRepository.existsById(id)) {
			throw new TaskNotFoundException("Task with id "+id+" does not exist");
		}
		taskRepository.deleteById(id);
	}

	//modification de task
	public void updateTask(int id, Task task) {
		if(!taskRepository.existsById(id)) {
			throw new TaskNotFoundException("Task with id "+id+" does not exist");
		}
		task.setTitle(task.getTitle());
		task.setDescription(task.getDescription());
		task.setCreatedBy(task.getCreatedBy());
		task.setBoard(task.getBoard());
		taskRepository.save(task);
	}

	//affected task to board
	public void assignTaskToBoard(int taskId, int boardId) {
		Task task = taskRepository.findById(taskId)
				.orElseThrow(() -> new TaskNotFoundException("Task not found"));
		Board board = boardRepository.findById(boardId)
				.orElseThrow(() -> new BoardNotFoundException("Board not found"));
		task.setBoard(board);
		taskRepository.save(task);
	}






}
