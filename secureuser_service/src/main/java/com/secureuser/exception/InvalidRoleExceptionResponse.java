package com.secureuser.exception;

public class InvalidRoleExceptionResponse {

	private String error;

	/**
	 * @param error
	 */
	public InvalidRoleExceptionResponse(String error) {
		super();
		this.error = error;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}
	
	
	
}
