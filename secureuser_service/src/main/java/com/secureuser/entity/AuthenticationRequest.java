/**
 * 
 */
package com.secureuser.entity;

/**
 * @author kmahendr
 * 
 * This Class Authenticates user with username and password
 *
 */
public class AuthenticationRequest {
	
	private String username;
	private String password;
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @param username
	 * @param password
	 */
	public AuthenticationRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	/**
	 * 
	 */
	public AuthenticationRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}
