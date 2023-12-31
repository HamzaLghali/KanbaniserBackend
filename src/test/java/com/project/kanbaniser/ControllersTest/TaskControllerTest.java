package com.project.kanbaniser.ControllersTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.kanbaniser.Controllers.TaskController;
import com.project.kanbaniser.Entities.Task;
import com.project.kanbaniser.Entities.User;
import com.project.kanbaniser.Services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

class TaskControllerTest {

	private MockMvc mockMvc;

	@Mock
	private TaskService taskService;

	@InjectMocks
	private TaskController taskController;

	private ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = standaloneSetup(taskController).build();
	}

	@Test
	void createTaskInBoardTest() throws Exception {
		int boardId = 1;
		Task task = new Task();
		task.setId(1);
		task.setTitle("Sample Task");
		task.setDescription("Sample Description");
		task.setCreatedAt(new Date());

		User createdBy = new User();
		createdBy.setUserId(1);
		createdBy.setFirstname("Imad");
		createdBy.setLastname("Maailil");
		createdBy.setPhoneNumber("1234567890");
		createdBy.setEmail("imad.maailil@example.com");
		createdBy.setPassword("password");

		task.setCreatedBy(createdBy);

		doNothing().when(taskService).createTaskInBoard(any(Task.class), eq(boardId));

		mockMvc.perform(post("/api/v1/tasks/createInBoard/" + boardId)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(task)))
				.andExpect(status().isOk());
	}

	@Test
	void assignTaskToBoardTest() throws Exception {
		int taskId = 1;
		int boardId = 1;

		Task task = new Task();
		task.setId(taskId);
		task.setTitle("Sample Task");
		task.setDescription("Sample Description");
		task.setCreatedAt(new Date());

		doNothing().when(taskService).assignTaskToBoard(eq(taskId), eq(boardId));

		mockMvc.perform(put("/api/v1/tasks/" + taskId + "/assignToBoard/" + boardId))
				.andExpect(status().isOk());
	}

	@Test
	void deleteTaskTest() throws Exception {
		int taskId = 1;

		doNothing().when(taskService).deleteTask(eq(taskId));

		mockMvc.perform(delete("/api/v1/tasks/" + taskId))
				.andExpect(status().isOk());
	}

	@Test
	void updateTaskTest() throws Exception {
		int taskId = 1;
		Task task = new Task();
		task.setId(taskId);
		task.setTitle("Updated Task");
		task.setDescription("Updated Description");
		task.setCreatedAt(new Date());

		doNothing().when(taskService).updateTask(eq(taskId), any(Task.class));

		mockMvc.perform(put("/api/v1/tasks/update/" + taskId)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(task)))
				.andExpect(status().isOk());
	}
	@Test
	void getAllTasksTest() throws Exception {
		User createdBy = new User();
		createdBy.setUserId(1);
		createdBy.setFirstname("Imad");
		createdBy.setLastname("Maailil");
		createdBy.setPhoneNumber("1234567890");
		createdBy.setEmail("imad.maailil@example.com");
		createdBy.setPassword("password");

		Task task = new Task();
		task.setId(1);
		task.setTitle("Sample Task");
		task.setDescription("Sample Description");
		task.setCreatedAt(new Date());
		task.setCreatedBy(createdBy);

		List<Task> tasks = Arrays.asList(task);

		when(taskService.getAllTasks()).thenReturn(tasks);

		mockMvc.perform(get("/api/v1/tasks"))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(tasks)));
	}

	@Test
	void getTaskByIdTest() throws Exception {
		int taskId = 1;

		User createdBy = new User();
		createdBy.setUserId(1);
		createdBy.setFirstname("Imad");
		createdBy.setLastname("Maailil");
		createdBy.setPhoneNumber("1234567890");
		createdBy.setEmail("imad.maailil@example.com");
		createdBy.setPassword("password");

		Task task = new Task();
		task.setId(taskId);
		task.setTitle("Sample Task");
		task.setDescription("Sample Description");
		task.setCreatedAt(new Date());
		task.setCreatedBy(createdBy);

		when(taskService.getTaskById(eq(taskId))).thenReturn(task);

		mockMvc.perform(get("/api/v1/tasks/" + taskId))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(task)));
	}

}
