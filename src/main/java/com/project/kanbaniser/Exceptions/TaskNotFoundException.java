package com.project.kanbaniser.Exceptions;

public class TaskNotFoundException extends RuntimeException{
	public TaskNotFoundException(String message) {
		super(message);
	}
}
