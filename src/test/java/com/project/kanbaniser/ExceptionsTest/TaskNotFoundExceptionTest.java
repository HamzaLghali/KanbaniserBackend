package com.project.kanbaniser.ExceptionsTest;

import com.project.kanbaniser.Exceptions.TaskNotFoundException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskNotFoundExceptionTest {

	@Test
	public void testExceptionMessage() {
		String expectedMessage = "Task not found with provided id";

		TaskNotFoundException exception = new TaskNotFoundException(expectedMessage);

		assertEquals(expectedMessage, exception.getMessage());
	}
}
