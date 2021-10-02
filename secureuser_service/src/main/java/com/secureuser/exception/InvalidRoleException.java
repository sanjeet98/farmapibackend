/**
 * 
 */
package com.secureuser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author kmahendr
 *
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidRoleException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public InvalidRoleException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public InvalidRoleException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	

	

	
}
