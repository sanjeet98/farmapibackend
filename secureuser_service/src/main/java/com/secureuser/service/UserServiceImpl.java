/**
 * 
 */
package com.secureuser.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secureuser.entity.User;
import com.secureuser.exception.InvalidRoleException;
import com.secureuser.exception.UserAlreadyExistException;
import com.secureuser.exception.UserNotFoundException;
import com.secureuser.repository.UserRepository;

/**
 * @author kmahendr
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	
	
	
	/**
	 * This is for registering new user
	 * @param user with name ,loginName,password,role,phonenumber 
	 * @param result result set for error
	 * @return a String of User Created Successfully
	 * @exception {@link UserAlreadyExistException},{@link NullPointerException}
	 */
	@Override
	public User saveUser(User user) {
		User savedUser=null;
		
		if (user.getUserName() == null || user.getLoginName() == null || user.getPwd() == null||user.getPhone() == null||user.getRole() == null)
		{
			log.error("Exception");

			throw new NullPointerException("Please Enter All Values in the field");
		}
		
		if ((userRepository.findByLoginName(user.getLoginName())) != null) 
		{
			log.error("Exception");

			throw new UserAlreadyExistException("User already exists Please Login");
		} 
		
		savedUser=userRepository.save(user);
		log.info("User Saved");

		return savedUser;
	}

	/**
	 * 
	 * @param loginName Valid LoginName of the user
	 * @return The User with that login name
	 * @exception {@link UserNotFoundException},{@link NullPointerException}
	 */
	@Override
	public User findByLoginName(String loginName) {
	
	User user=null;
	if (loginName == null)
	{
		log.error("Exception:");

		throw new NullPointerException("Please Provide Login Name");
	}
	
	if ((user = userRepository.findByLoginName(loginName)) == null) 
	{
		log.error("Exception");

		throw new UserNotFoundException("User with loginName : " + loginName + " does not exists");
	}
	
	log.info("Method End Use Found");

	return userRepository.findByLoginName(loginName);
	
	}
	/**
	 * @return List of Users Present in Data base
	 * @exception {@link UserNotFoundException}
	 */

	@Override
	public List<User> findAll() 
	{
		
		try 
		{
			log.info("Users found :");

			return userRepository.findAll();
		} 
		catch (Exception e) 
		{
			log.error("USers not found:");

			throw new UserNotFoundException("No User Found");
		}
	}

	/**
	 * 
	 * @param loginName Valid userid of the user
	 * @return The User with that userid
	 * @exception {@link UserNotFoundException},{@link NullPointerException}
	 */
	@Override
	public User findByUserId(Long userId) {
		// TODO Auto-generated method stub
		User user=null;
		if (userId == null) 
		{
			
			throw new NullPointerException("Please Provide USerID ");
		}
		
		if ((user = userRepository.findByUserId(userId)) == null) 
		{
			
			throw new UserNotFoundException("User with User Id : " + userId + " Does not exists");
		}
		return userRepository.findByUserId(userId);
	}

	/**
	 * @param user modified user details as a user 
	 * @param result for errors in result set
	 * @return a user with updated data
	 * @exception {@link UserNotFoundException},{@link NullPointerException}
	 */
	@Override
	public User updateUser(User user) {
		
		if (user == null) 
		{
			log.error("Exception for :"+user);

			throw new NullPointerException("Please Provide a Valid User ");
		}
		User oldUser = userRepository.findByLoginName(user.getLoginName());
		if (oldUser == null) {
			log.error("Exception:");

			throw new UserNotFoundException(
					"User with loginName : " + user.getLoginName() + " does not exists Cant Update");
		}
		user.setUserId(oldUser.getUserId());
		oldUser = user;
		log.info("Method end User Updated:");
		return userRepository.save(oldUser);
	}

	

	/**
	 * @param loginName of the User to be deleted
	 * @return Message That the user is deleted
	 * @exception {@link UserNotFoundException},{@link NullPointerException}
	 */
	@Override
	public void deleteUserByLoginName(String loginName) {
		User userTodelete=null;
		log.info("User gave this loginname:"+loginName);

		userTodelete=userRepository.findByLoginName(loginName);
		
		if (userTodelete==null) {
			log.error("Exception For This Value:"+userTodelete);

			throw new UserNotFoundException("User with loginName : " + loginName +" Does not exists ! Cannot Delete");
		} 
		userRepository.delete(userTodelete);
		
		log.info("Method end User Deleted:");

		userTodelete.setLoginName(null);	
		
	}

	
	/**
	 * @param userrole a String which has role of the user
	 * @return a list of users of that role
	 * @exception {@link UserNotFoundException},{@link InvalidRoleException},{@link NullPointerException}
	 */
	@Override
	public List<User> findByRole(String findrole) {
		// TODO Auto-generated method stub
		
		log.info("User Gave Role:"+findrole);

		List<User> usersbyrole=null;
		
		if(findrole==null)
		{
			log.error("Exception This Role:"+findrole);

			throw new NullPointerException("Entered Role is null");

		}
		usersbyrole=userRepository.findByRole(findrole);
		if(usersbyrole.isEmpty())
		{
			log.error("Exception This Role:"+findrole);
			throw new InvalidRoleException("Users with Role:"+findrole+":not found");
		}
		log.info("Method end Users found for this:"+findrole+usersbyrole);
		return usersbyrole;
		
		
		
				
	}
	
	
	
}
