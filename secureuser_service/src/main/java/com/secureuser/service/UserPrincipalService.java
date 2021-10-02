package com.secureuser.service;

import org.springframework.security.core.userdetails.UserDetails;


public interface UserPrincipalService{

	UserDetails loadUserByLoginname(String loginName) throws Exception;

	
	

	
		
}
