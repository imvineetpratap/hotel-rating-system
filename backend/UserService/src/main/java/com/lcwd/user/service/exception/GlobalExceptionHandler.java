package com.lcwd.user.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lcwd.user.service.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	//pure project me kahi bhi error aaegi to handle hogi yaha se
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex){
		//setting message in api response and bilding it using builder pattern
		String message=ex.getMessage();
	ApiResponse response=	ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
	return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
	}
}
