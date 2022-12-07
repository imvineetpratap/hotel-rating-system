package com.lcwd.user.service.exception;

public class ResourceNotFoundException extends RuntimeException {
//entend means us class ki properties is class me ajae
	
	
	//extra properties that you want to manage
	public ResourceNotFoundException() {
		super("Resource not found on server !! ");
	}
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
