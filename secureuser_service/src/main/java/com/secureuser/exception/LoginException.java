package com.secureuser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class LoginException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public LoginException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public LoginException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	

}
