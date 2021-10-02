package com.secureuser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MyUserDetailService implements UserDetailsService {

	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UserPrincipalService userPrincipalService;
	
	
	/**
	 * 
	 * This class will be authenticating the user on user service login page 
	 * after creating jwt tokens we need to connect the microservice
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		/**
		 * this Will run query on user controller and then
		 * will bring the user data to userdetail object and return
		 */
	
		
		//User user=this.restTemplate.getForObject("http://user-service/user/"+username, User.class);
		
		//List users=this.restTemplate.getForObject("http://localhost:8084/user/all", List.class);
		
		//System.out.println("List of users\n"+users.toString());
//		
//			
				UserDetails foundUser=null;
				try {
					foundUser = userPrincipalService.loadUserByLoginname(username);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			return new User(foundUser.getUsername(),foundUser.getPassword(),foundUser.getAuthorities());
		
//		
//		//return new User("admin","admin",new ArrayList<>());
	}

	
}
