package com.project.kanbaniser.ControllersTest;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.kanbaniser.Controllers.UserController;
import com.project.kanbaniser.Entities.Board;
import com.project.kanbaniser.Entities.Task;
import com.project.kanbaniser.Entities.User;
import com.project.kanbaniser.Services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

public class UserControllerTest {

	private MockMvc mockMvc;

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	private ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = standaloneSetup(userController).build();
	}

	@Test
	void getAllUsersTest() throws Exception {

		List<User> users = Arrays.asList(new User(/* parameters */));
		when(userService.getAllUsers()).thenReturn(users);

		mockMvc.perform(get("/api/v1/users"))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(users)));
	}

	@Test
	void addUserTest() throws Exception {
		User user = new User(/* parameters */);
		doNothing().when(userService).addUser(any(User.class));

		mockMvc.perform(post("/api/v1/users/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isOk());
	}

	@Test
	void updateUserTest() throws Exception {
		// Arrange
		int userId = 1;
		User user = new User(/* parameters */);
		doNothing().when(userService).updateUser(eq(userId), any(User.class));

		mockMvc.perform(put("/api/v1/users/" + userId)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isOk());
	}

	@Test
	void deleteUserTest() throws Exception {
		int userId = 1;
		doNothing().when(userService).deleteUser(userId);

		// Act & Assert
		mockMvc.perform(delete("/api/v1/users/" + userId))
				.andExpect(status().isOk());
	}

	@Test
	void getUserByIdTest() throws Exception {
		// Arrange
		int userId = 1;
		User user = new User(/* parameters */);
		when(userService.getUserById(userId)).thenReturn(user);

		// Act & Assert
		mockMvc.perform(get("/api/v1/users/" + userId))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(user)));
	}

// ... [previous test methods]

	@Test
	void getUserByEmailTest() throws Exception {
		// Arrange
		String email = "user@example.com";
		User user = new User(/* parameters */);
		when(userService.getUserByEmail(email)).thenReturn(user);

		// Act & Assert
		mockMvc.perform(get("/api/v1/users/email/" + email))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(user)));
	}

	@Test
	void getBoardsByUserIdTest() throws Exception {
		// Arrange
		int userId = 1;
		List<Board> boards = Arrays.asList(new Board(/* parameters */));
		when(userService.getBoardsByUserId(userId)).thenReturn(boards);

		// Act & Assert
		mockMvc.perform(get("/api/v1/users/" + userId + "/boards"))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(boards)));
	}

	@Test
	void getTasksByUserIdTest() throws Exception {
		// Arrange
		int userId = 1;
		List<Task> tasks = Arrays.asList(new Task(/* parameters */));
		when(userService.getTasksByUserId(userId)).thenReturn(tasks);

		// Act & Assert
		mockMvc.perform(get("/api/v1/users/" + userId + "/tasks"))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(tasks)));
	}


}
