package com.secureuser.exception;

public class LoginExceptionResponse {
	
	private String error;

	/**
	 * @param error
	 */
	public LoginExceptionResponse(String error) {
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
