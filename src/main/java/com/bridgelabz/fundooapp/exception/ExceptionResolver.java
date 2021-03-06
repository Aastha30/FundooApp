package com.bridgelabz.fundooapp.exception;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.fundooapp.response.Response;

@ControllerAdvice
public class ExceptionResolver {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> exceptionResolver(Exception exception) {
		Response response = new Response();
		response.setStatusCode(500);
		response.setStatusMessage(exception.getMessage());
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}
	@ExceptionHandler(UserException.class)
	public ResponseEntity<Response> userExceptionResolver(UserException exception) {
		Response response = new Response();
		response.setStatusCode(exception.getErrorCode());
		response.setStatusMessage(exception.getMessage());
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

}
