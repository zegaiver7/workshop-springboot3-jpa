package com.educandoweb.course.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice  //Anotation que vai interceptar as excessões que acontecerem para que eu possa tratar
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)  //Anotation para que este metodo seja capaz de interceptar a requisição que deu excessão pa ele cair aqui
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(),status.value(),error, e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)  //Anotation para que este metodo seja capaz de interceptar a requisição que deu excessão pa ele cair aqui
	public ResponseEntity<StandardError> resourceNotFound(DatabaseException e, HttpServletRequest request){
		String error = "Database error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(),status.value(),error, e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
