package com.project.kanbaniser.ControllersTest;

import com.project.kanbaniser.Controllers.BoardController;
import com.project.kanbaniser.Entities.Board;
import com.project.kanbaniser.Entities.Task;
import com.project.kanbaniser.Entities.User;
import com.project.kanbaniser.Services.BoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class BoardControllerTest {

	private MockMvc mockMvc;

	@Mock
	private BoardService boardService;

	@InjectMocks
	private BoardController boardController;

	private ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(boardController).build();
	}

	@Test
	void addBoardTest() throws Exception {
		Board board = new Board(/* parameters */);
		doNothing().when(boardService).addBoard(any(Board.class));

		mockMvc.perform(post("/api/v1/boards")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(board)))
				.andExpect(status().isOk());
	}

	@Test
	void deleteBoardTest() throws Exception {
		int boardId = 1;
		doNothing().when(boardService).deleteBoard(boardId);

		mockMvc.perform(delete("/api/v1/boards/" + boardId))
				.andExpect(status().isOk());
	}

	@Test
	void updateBoardTest() throws Exception {
		int boardId = 1;
		Board board = new Board(/* parameters */);
		doNothing().when(boardService).updateBoard(eq(boardId), any(Board.class));

		mockMvc.perform(put("/api/v1/boards/" + boardId)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(board)))
				.andExpect(status().isOk());
	}

	@Test
	void addUserToBoardTest() throws Exception {
		int userId = 1;
		int boardId = 1;
		doNothing().when(boardService).addUserToBoard(userId, boardId);

		mockMvc.perform(post("/api/v1/boards/" + boardId + "/users/" + userId))
				.andExpect(status().isOk());
	}

	@Test
	void removeUserFromBoardTest() throws Exception {
		int userId = 1;
		int boardId = 1;
		doNothing().when(boardService).removeUserFromBoard(userId, boardId);

		mockMvc.perform(delete("/api/v1/boards/" + boardId + "/users/" + userId))
				.andExpect(status().isOk());
	}


	@Test
	void addTaskToBoardTest() throws Exception {
		int taskId = 1;
		int boardId = 1;
		doNothing().when(boardService).addTaskToBoard(taskId, boardId);

		mockMvc.perform(post("/api/v1/boards/" + boardId + "/tasks/" + taskId))
				.andExpect(status().isOk());
	}

	@Test
	void removeTaskFromBoardTest() throws Exception {
		int taskId = 1;
		int boardId = 1;
		doNothing().when(boardService).removeTaskFromBoard(taskId, boardId);

		mockMvc.perform(delete("/api/v1/boards/" + boardId + "/tasks/" + taskId))
				.andExpect(status().isOk());
	}

	@Test
	void getAllUsersInBoardTest() throws Exception {
		int boardId = 1;
		List<User> users = Arrays.asList(new User(/* parameters */));
		when(boardService.getAllUsersInBoard(boardId)).thenReturn(users);

		mockMvc.perform(get("/api/v1/boards/" + boardId + "/users"))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(users)));
	}

	@Test
	void getAllTasksInBoardTest() throws Exception {
		int boardId = 1;
		List<Task> tasks = Arrays.asList(new Task(/* parameters */));
		when(boardService.getAllTasksInBoard(boardId)).thenReturn(tasks);

		mockMvc.perform(get("/api/v1/boards/" + boardId + "/tasks"))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(tasks)));
	}
}