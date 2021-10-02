package com.secureuser.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.secureuser.entity.AuthenticationRequest;
import com.secureuser.entity.AuthenticationResponse;
import com.secureuser.entity.Complaint;
import com.secureuser.entity.User;
import com.secureuser.exception.InvalidRoleException;
import com.secureuser.exception.LoginException;
import com.secureuser.exception.UserAlreadyExistException;
import com.secureuser.exception.UserNotFoundException;
import com.secureuser.exception.UserNotFoundExceptionResponse;
import com.secureuser.service.MapValidationErrorService;
import com.secureuser.service.UserService;
import com.secureuser.util.JwtUtil;

/**
 * This Controller is Running on Port 8084
 * @author kmahendr
 *
 */
@RestController
@RequestMapping("/secureuser")
public class UserController {
	
	
	@Autowired
	private UserService userService;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	BCryptPasswordEncoder bcryptEncoder;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	private final String ROLE_ADMIN="ROLE_ADMIN";
	private final String ROLE_FARMER="ROLE_FARMER";
	private final String ROLE_DEALER="ROLE_DEALER";
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	/**-----------------------------restTemplate url--------------------------------**/
	private final String FARMER_ADD_COMPLAINT_URL="http://complaint-service/complaint/addComplaint";
	
	/**-----------------------------restTemplate url--------------------------------**/

	/**-------------------------UserController----------------------------------------**/
	/**
	 * This is for registering new user
	 * @param user with name ,loginName,password,role,phonenumber 
	 * @param result result set for error
	 * @return a String of User Created Successfully
	 * @exception UserAlreadyExistExceptionResponse,NullPointerException
	 */
	/**secureuser/admin/register**/
	/** 
	 * @param user
	 * @param result
	 * @return
	 */
	@PostMapping("/admin/register")
	public ResponseEntity<?> createNewAdmin(@Valid @RequestBody User user, BindingResult result) 
	{
		log.info("--UserController--Register");
		log.info("User Passed This Data:"+user.toString());

		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		log.info("errorMap"+errorMap);
		if (errorMap != null)
			return errorMap;
		
		try {
			
			String original=user.getPwd();
			String encryptedpassword=this.bcryptEncoder.encode(original);
			user.setPwd(encryptedpassword);
			user.setRole(ROLE_ADMIN);
			User savedUser = userService.saveUser(user);
			log.info("Saved USer"+savedUser.toString()+"--User Controller--Register Mapping");
			return new ResponseEntity<String>("Administrator With LoginName :- "+savedUser.getLoginName()+" Has Been Created Successfully", HttpStatus.CREATED);
		} 
		catch (UserAlreadyExistException e) 
		{
			log.error("Exception--"+e.getMessage()+"----UserController--Register");
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
		
	}
	
	/**secureuser/admin/complaint**/
	
	/**secureuser/farmer/register**/
	/** 
	 * @param user
	 * @param result
	 * @return
	 */
	@PostMapping("/farmer/register")
	public ResponseEntity<?> createNewFarmer(@Valid @RequestBody User user, BindingResult result) 
	{
		log.info("--UserController--Register- as a Farmer");
		log.info("User Passed This Data:"+user.toString());

		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		log.info("errorMap"+errorMap);
		if (errorMap != null)
			return errorMap;
		
		try {
			
			String original=user.getPwd();
			String encryptedpassword=this.bcryptEncoder.encode(original);
			user.setPwd(encryptedpassword);
			user.setRole(ROLE_FARMER);
			User savedUser = userService.saveUser(user);
			log.info("Saved USer"+savedUser.toString()+"--User Controller--Register Mapping");
			return new ResponseEntity<String>("Farmer With LoginName :- "+savedUser.getLoginName()+" Has Been Created Successfully", HttpStatus.CREATED);
		} 
		catch (UserAlreadyExistException e) 
		{
			log.error("Exception--"+e.getMessage()+"----UserController--Register");
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
		
	}
	
	/**secureuser/farmer/{loginName}/addcomplaint **/
	@PostMapping("/farmer/{loginName}/addcomplaint")
	public ResponseEntity<?> FarmerAddComplaint(@PathVariable String loginName, @RequestBody Complaint complaint )
	{
		complaint.setCreatedBy(loginName);
		ResponseEntity<Complaint> newComplaint=restTemplate.postForEntity(FARMER_ADD_COMPLAINT_URL, complaint, Complaint.class);
		return newComplaint;
	}
	
	/**secureuser/dealer/register**/
	/** 
	 * @param user
	 * @param result
	 * @return
	 */
	@PostMapping("/dealer/register")
	public ResponseEntity<?> createNewDealer(@Valid @RequestBody User user, BindingResult result) 
	{
		log.info("--UserController--Register- as a Farmer");
		log.info("User Passed This Data:"+user.toString());

		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		log.info("errorMap"+errorMap);
		if (errorMap != null)
			return errorMap;
		
		try {
			
			String original=user.getPwd();
			String encryptedpassword=this.bcryptEncoder.encode(original);
			user.setPwd(encryptedpassword);
			user.setRole(ROLE_DEALER);
			User savedUser = userService.saveUser(user);
			log.info("Saved USer"+savedUser.toString()+"--User Controller--Register Mapping");
			return new ResponseEntity<String>("Dealer With LoginName :- "+savedUser.getLoginName()+" Has Been Created Successfully", HttpStatus.CREATED);
		} 
		catch (UserAlreadyExistException e) 
		{
			log.error("Exception--"+e.getMessage()+"----UserController--Register");
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
		
	}
	
	
	/**user/profile/{loginName}**/
	/**
	 * 
	 * @param loginName Valid LoginName of the user
	 * @return The User with that login name
	 * @exception UserNotFoundExceptionResponse,NullPointerException
	 */
	@GetMapping("/profile/{loginName}")
	public ResponseEntity<?> findUserByloginName(@PathVariable String loginName,HttpServletRequest request) 
	{
		
	
		
		log.info("User Passed This LoginName:"+loginName);
		try
		{
			String Header=request.getHeader("Authorization");

			String username=null;
			String jwt=null;
			if (Header != null && Header.startsWith("Bearer ")) {
				jwt = Header.substring(7);
				username = jwtTokenUtil.extractUsername(jwt);
			}
			
			if(!username.equals(loginName))
			{
				throw new LoginException("Please Authorize yourself as a :-"+loginName);
			}	
				
		User foundUser=userService.findByLoginName(loginName); 
		
		log.info("-----UserController--FindByLoginName"+foundUser.toString()+"-----");
		return new ResponseEntity<User>(foundUser, HttpStatus.FOUND);
		
		
		}
		catch(UserNotFoundException em) {
			// TODO: handle exception
			
			log.error("Exception-----UserController--FindByLoginName"+em.getMessage()+"-----");

			return new ResponseEntity<String>(em.getMessage(), HttpStatus.NOT_FOUND);
		}
		catch(LoginException ex) {
			// TODO: handle exception
			
			log.error("Exception-----UserController--UNauthorized-----");

			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
		
		
		
	}
	
	
	
	/**secureuser/profiles/all**/
	/**
	 * @return List of Users Present in Data base
	 * @exception UserNotFoundExceptionResponse
	 */
	@GetMapping("/profiles/all")
	public ResponseEntity<?> getAllUsers()
	{
		try
		{
			
			List<User> users = userService.findAll();
			log.info("Users list:"+users);
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		}
		catch(UserNotFoundException em){
			log.error("Exception This LoginName:"+em.getMessage());
		return new ResponseEntity<String>(em.getMessage(), HttpStatus.NOT_FOUND);
		}
		catch(Exception e)
		{
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.UNAUTHORIZED);
		}
	}
	
	/**secureuser/profiles/allbyrole/{userrole}**/
	/**
	 * 
	 * @param userrole a String which has role of the user
	 * @return a list of users of that role
	 * @exception {@link UserNotFoundException},{@link InvalidRoleException},{@link NullPointerException}
	 */
	@GetMapping("/profiles/allbyrole/{userrole}")
	public ResponseEntity<?> getAllUsersByRole(@PathVariable String userrole)
	{
		try
		{
			List<User> usersbyrole = userService.findByRole(userrole);
			log.info("Users list:"+usersbyrole);
			return new ResponseEntity<List<User>>(usersbyrole, HttpStatus.OK);
		}
		catch(UserNotFoundException em){
			log.error("Exception This Role:"+em.getMessage());
		return new ResponseEntity<String>(em.getMessage(), HttpStatus.NOT_FOUND);
		}
		catch(InvalidRoleException er)
		{
			log.error("Exception:"+er.getMessage());
		return new ResponseEntity<String>(er.getMessage(), HttpStatus.BAD_REQUEST);
			
		}
		
	}
	
	/**secureuser/profile/update**/
	/**
	 * @param user modified user details as a user 
	 * @param result for errors in result set
	 * @return a user with updated data
	 * @exception UserNotFoundExceptionResponse,NullPointerException
	 */

	@PutMapping("/profile/update")
	public ResponseEntity<?> updateUser(@Valid @RequestBody User user,BindingResult result) 
	{
		User newUser=null;
		log.info("User Passed These Details:"+user);
		/*session code uncomment if u need if (session.getAttribute("userType") != null && session.getAttribute("userType").equals("User")){}return new ResponseEntity<String>("You do not have Access!!!", HttpStatus.UNAUTHORIZED);*/
		try
		{
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		log.info("errorMap"+errorMap);
		if (errorMap != null)
			return errorMap;
		
		
		String original=user.getPwd();
		String encryptedpassword=this.bcryptEncoder.encode(original);
		user.setPwd(encryptedpassword);
		
		newUser = userService.updateUser(user);
		log.info("User Updated"+user.toString());
		return new ResponseEntity<User>(newUser, HttpStatus.ACCEPTED);
		}
		catch(UserNotFoundException e)
		{
			log.error("Exception:"+e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);

		}
		
	}
	
	/**secureuser/profile/deleteprofile/{loginName}**/
	/**
	 * @param loginName of the User to be deleted
	 * @return Message That the user is deleted
	 * @exception UserNotFoundExceptionResponse,NullPointerException
	 */
	@DeleteMapping("/profile/deleteprofile/{loginName}")
	public ResponseEntity<?> deleteUser(@PathVariable String loginName) 
	{
		log.info("User Entered this LoginName:"+loginName);
		try
		{
		userService.deleteUserByLoginName(loginName);
		log.info("\"User with loginName :\" + loginName + \" is deleted\", HttpStatus.OK");
		return new ResponseEntity<String>("User with loginName :" + loginName + " is deleted", HttpStatus.OK);
		}
		catch(UserNotFoundException ewd)
		{
			log.error("Exception:"+ewd.getMessage());
			return new ResponseEntity<String>(ewd.getMessage(),HttpStatus.NOT_FOUND);

		}
		
	}
	

	/**LOGIN**/
	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authrequest) throws Exception
	{
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authrequest.getUsername(),authrequest.getPassword())
					);
		} catch (AuthenticationException e) {
			throw new Exception("Bad Credentials",e);
		}
		
		final UserDetails userDetails= userDetailsService.loadUserByUsername(authrequest.getUsername());
		
		final String jwt =jwtTokenUtil.generateToken(userDetails);
		
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
/**---------------------------------UserController----------------------------------------**/


}
