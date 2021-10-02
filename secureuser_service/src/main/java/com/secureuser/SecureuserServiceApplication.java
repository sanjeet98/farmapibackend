package com.secureuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.secureuser.filter.JwtRequestFilter;

@EnableWebSecurity
@SpringBootApplication
@EnableEurekaClient
public class SecureuserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureuserServiceApplication.class, args);
	}

	@Bean
	public JwtRequestFilter jwtRequestFilter()
	{
		return new JwtRequestFilter();
	}
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {return new RestTemplate();}
}
