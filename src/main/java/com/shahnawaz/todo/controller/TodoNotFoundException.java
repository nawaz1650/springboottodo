package com.shahnawaz.todo.controller;

public class TodoNotFoundException extends RuntimeException {
	public TodoNotFoundException(String message) {
		super(message);
	}
}
