package com.project.kanbaniser.ExceptionsTest;

import com.project.kanbaniser.Exceptions.UserNotFoundException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserNotFoundExceptionTest {

	@Test
	public void testExceptionMessage() {
		String expectedMessage = "User not found with provided id";

		UserNotFoundException exception = new UserNotFoundException(expectedMessage);

		assertEquals(expectedMessage, exception.getMessage());
	}
}
