package com.shahnawaz.todo.Exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.shahnawaz.todo.controller.UserNotFoundException;
@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler 
extends ResponseEntityExceptionHandler {

	public CustomResponseEntityExceptionHandler() {
		// TODO Auto-generated constructor stub
	}
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> 
	handleAllException(Exception ex, WebRequest request) throws Exception {
		
		ExceptionResponse er=new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity(er, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> 
	handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
		
		ExceptionResponse er=new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity(er, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(LoginException.class)
	public final ResponseEntity<Object> 
	handleLoginException(Exception ex, WebRequest request) throws Exception {
		
		ExceptionResponse er=new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity(er, HttpStatus.BAD_REQUEST);
	}

}
