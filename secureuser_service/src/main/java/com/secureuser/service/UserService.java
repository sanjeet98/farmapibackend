/**
 * 
 */
package com.secureuser.service;

import java.util.List;

import com.secureuser.entity.User;

/**
 * @author kmahendr
 *
 */
public interface UserService {
	
	public User saveUser(User user);
	
	public User findByLoginName(String loginName);
	
	public User findByUserId(Long userId);
	
	public List<User> findAll();

	public User updateUser(User user);

	public void deleteUserByLoginName(String loginName);

	public List<User> findByRole(String role);

}
