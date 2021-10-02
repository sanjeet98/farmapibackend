package com.secureuser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserAlreadyExistException extends RuntimeException {

	private static final long serialVErsionUID =1L;

	/**
	 * 
	 */
	public UserAlreadyExistException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public UserAlreadyExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	
}
