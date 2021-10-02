package com.secureuser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.secureuser.entity.User;
import com.secureuser.entity.UserPrincipal;
import com.secureuser.repository.UserRepository;


@Service
public class UserPrincipalServiceImpl implements UserPrincipalService{
	
	@Autowired
	RestTemplate restTemplate;
	
	
	@Autowired
	UserService userService;
	
	@Override
	public UserDetails loadUserByLoginname(String loginName){
		// TODO Auto-generated method stub
		
			User user=userService.findByLoginName(loginName); 
			UserPrincipal userPrincipal=new UserPrincipal(user);
			return userPrincipal;
		
	}

}
