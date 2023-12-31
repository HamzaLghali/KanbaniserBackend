package com.project.kanbaniser.ExceptionsTest;

import com.project.kanbaniser.Exceptions.BoardNotFoundException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardNotFoundExceptionTest {

	@Test
	public void testExceptionMessage() {
		// The message we expect our exception to have
		String expectedMessage = "Board not found with provided id";

		// Create an instance of BoardNotFoundException with the expected message
		BoardNotFoundException exception = new BoardNotFoundException(expectedMessage);

		// Assert that the message from the exception matches the expected message
		assertEquals(expectedMessage, exception.getMessage());
	}
}
