/**
 * 
 */
package com.secureuser.entity;

/**
 * @author kmahendr
 * 
 * This Class Provides jwt response token in authentication responses
 *
 */
public class AuthenticationResponse {
	
	
	private String jwt;

	
	
	/**
	 * 
	 */
	public AuthenticationResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param jwt
	 */
	public AuthenticationResponse(String jwt) {
		super();
		this.jwt = jwt;
	}

	/**
	 * @return the jwt
	 */
	public String getJwt() {
		return jwt;
	}

	/**
	 * @param jwt the jwt to set
	 */
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	

}
