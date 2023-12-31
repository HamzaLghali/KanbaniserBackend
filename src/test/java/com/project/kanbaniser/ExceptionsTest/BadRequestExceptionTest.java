package com.project.kanbaniser.ExceptionsTest;

import com.project.kanbaniser.Exceptions.BadRequestException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BadRequestExceptionTest {

	@Test
	public void testExceptionMessage() {
		// The message we expect our exception to have
		String expectedMessage = "Bad request due to invalid input";

		// Create an instance of BadRequestException with the expected message
		BadRequestException exception = new BadRequestException(expectedMessage);

		// Assert that the message from the exception matches the expected message
		assertEquals(expectedMessage, exception.getMessage());
	}
}
