/**
 * 
 */
package com.secureuser.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.persistence.*;

/**
 * @author kmahendr
 *
 */
@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Size(min=5, max=10,message = "user name can be 5 to 10 characters")
	@NotBlank(message = "username should not be blank")
	@Column(name="usernames")
	private String userName;
	
	@Size(min=5, max=10,message = "Login name should be 5 to 10 characters")
	@NotBlank(message="loginname Should not be empty and can include numbers")
	@Column(name="loginnames",unique = true, updatable = false)
	private String loginName;
	
	//@Size(min=5, max=10,message = "password should be 5 to 10 characters")
	@NotBlank(message="password Should not be empty trust us")
	@Column(name="passwords")
	private String pwd;
	
	@Size(min=10, max=12,message = "phone number should be 10 digits")
	@NotBlank(message="Please Provide a phone number trust us")
	@Column(name="phone_numbers")
	private String phone;
	
	/**
	 * This is Role of User 
	 * example Admin,Farmer,Dealer
	 */
	//@Size(min=5,max=6,message ="Please Enter a role of 5 to 6 characters")
	//@NotBlank(message= "Role can be Admin ,Farmer and Dealer only developers can add roles "
	//		+ "Dont try this at home")

	/**
	 * @param userName
	 * @param loginName
	 * @param pwd
	 * @param phone
	 */
	public User(
			@Size(min = 5, max = 10, message = "user name can be 5 to 10 characters") @NotBlank(message = "username should not be blank") String userName,
			@Size(min = 5, max = 10, message = "Login name should be 5 to 10 characters") @NotBlank(message = "loginname Should not be empty and can include numbers") String loginName,
			@NotBlank(message = "password Should not be empty trust us") String pwd,
			@Size(min = 10, max = 12, message = "phone number should be 10 digits") @NotBlank(message = "Please Provide a phone number trust us") String phone) {
		super();
		this.userName = userName;
		this.loginName = loginName;
		this.pwd = pwd;
		this.phone = phone;
	}


	@Column(name="roles")
	private String role;

	
	
	/**
	 * @param userId
	 * @param userName
	 * @param loginName
	 * @param pwd
	 * @param phone
	 * @param role
	 */
	public User(Long userId, String userName, String loginName, String pwd, String phone, String role) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.loginName = loginName;
		this.pwd = pwd;
		this.phone = phone;
		this.role = role;
	}


	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}


	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}


	

	/**
	 * @param userName
	 * @param loginName
	 * @param pwd
	 * @param phone
	 * @param role
	 */
	public User(String userName, String loginName, String pwd, String phone, String role) {
		super();
		this.userName = userName;
		this.loginName = loginName;
		this.pwd = pwd;
		this.phone = phone;
		this.role = role;
	}


	


	/**
	 * @param loginName
	 * @param pwd
	 * @param role
	 */
	public User(String loginName, String pwd, String role) {
		super();
		this.loginName = loginName;
		this.pwd = pwd;
		this.role = role;
	}


	/**
	 * 
	 */
	public User() {
		super();
		
	}


	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}


	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}


	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}


	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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


	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}


	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}


	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "User \n[userId=" + userId + ", \nuserName=" + userName + ", \nloginName=" + loginName
				+ ", \nphone=" + phone + ", \nRole=" + role + "]";
	}

	
	
	

}
