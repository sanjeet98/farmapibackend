package com.secureuser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.secureuser.exception.CustomResponseEntityExceptionHandler;
import com.secureuser.exception.LoginException;
import com.secureuser.filter.JwtRequestFilter;
import com.secureuser.service.MyUserDetailService;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Autowired
	MyUserDetailService myUserDetailService;
	
	
	
	@Autowired
	JwtRequestFilter jwtRequestFilter;
	
	private final String ADMIN="ROLE_ADMIN";
	private final String FARMER="ROLE_FARMER";
	private final String DEALER="ROLE_DEALER";
	
	public static final String[] SWAGGER_URLS= {
			"/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
	};
	
	public static final String[] PUBLIC_URLS= {
			"/secureuser/farmer/register",
			"/secureuser/dealer/register",
			"/secureuser/profile/{loginName}",
			"/secureuser/profiles/all",
			"/secureuser/profiles/allbyrole/{userrole}",
			"/secureuser/profile/update",
			"/secureuser/profile/deleteprofile/{loginName}",
			"/secureuser/farmer/{loginName}/addcomplaint"
	};
	
//	public static final String[] ADMIN_URLS= {
//			//allusers,hisprofile,add,listall,update delete advertisements,
//			
//	};
	
//	public static final String[] FARMER_URLS= {
//	//alladvertisements,hisprofile,add complaint,delete complaint
//	
//};
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(myUserDetailService).passwordEncoder(passwordEncoder());
	}
	
	/**
	 * line 44 change once api is getting data from other service
	 * change permitall to authenticated
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/secureuser/login").permitAll()
		.antMatchers("/secureuser/admin/register").hasRole("ADMIN")
		.antMatchers(PUBLIC_URLS).permitAll()
		.antMatchers(SWAGGER_URLS).permitAll()
				.anyRequest().authenticated()
				.and().formLogin()
				.and().exceptionHandling().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtRequestFilter,UsernamePasswordAuthenticationFilter.class);
	
	}
	
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
}

