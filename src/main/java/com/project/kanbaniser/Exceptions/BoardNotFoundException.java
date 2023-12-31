package com.project.kanbaniser.Exceptions;

public class BoardNotFoundException extends RuntimeException {
	public BoardNotFoundException(String message) {
		super(message);
	}
}
