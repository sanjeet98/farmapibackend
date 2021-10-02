package com.secureuser.exception;

public class UserAlreadyExistExceptionResponse {
	
	private String loginName;

	/**
	 * @param loginName
	 */
	public UserAlreadyExistExceptionResponse(String loginName) {
		super();
		this.loginName = loginName;
	}

	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

}
