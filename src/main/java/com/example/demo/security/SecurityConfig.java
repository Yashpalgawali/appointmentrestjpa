package com.example.demo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource datasource;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
	
		auth.jdbcAuthentication()
		.dataSource(datasource)
		
		//Following will select the username from database depending on the username from Login form
		.usersByUsernameQuery("select username,password,enabled from users where username=? ")
		
		//Following will select the authority(s) depending on the username
		.authoritiesByUsernameQuery("select username,role from users  where username=?")
		
		.passwordEncoder(new BCryptPasswordEncoder())
		;

	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf().disable()
			.authorizeHttpRequests()
			.antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
			.anyRequest()
			.authenticated() 
			.and()
			.httpBasic();
		
	/* START OF WORKING LOGIN Credentials*/		
//		http.cors();
//		http.
//			csrf().disable()
//			.authorizeRequests()
//			.anyRequest()
//			.fullyAuthenticated()
////			.and()
////			.formLogin()
////			.loginProcessingUrl("/login")
////			.permitAll()
//			.and()
//			
//			.httpBasic()
//		;
		
	/* END OF WORKING LOGIN Credentials*/
		
//		http.csrf().disable()
//			.authorizeRequests()
//			.and()
//			.formLogin()
//			.loginProcessingUrl("/login")
//			.defaultSuccessUrl("/company/comp")
//			.permitAll()
//			.and()
//			.logout()
//			.logoutSuccessUrl("/")
//			;
//		http.csrf().disable();
//		http.cors();
//		http
//		.authorizeHttpRequests()
//		.antMatchers("/","/addcompany","/bookappointment","/saveappointment","/viewappointments","/confotppassword",
//					 "/getallappointments","/searchappointment","/searchappointbyemail","/changepassword",
//					 "/confotp","/confotprl","/viewappointmentbyemail","/getallappointmentsbyemail/**","/changepass",
//					 "/gettodaysappointmentsbyemail/**","/confappointment/**","/declineappointment/**",
//					 "/getdeptbycompid/**","/getdeptbyempid/**","/getDeptByEmpId/**","/forgotpass","/forgotpassword",
//					 "/confotppass","/resources/static/**","/css/**","/js/**")
//		.permitAll()
//		.anyRequest().permitAll()
	
//		//.anyRequest().hasAnyAuthority("ROLE_ADMIN")

//		.and()
//		.formLogin()
//		.permitAll()
//		
//		.defaultSuccessUrl("/adminhome", true)
//		
//		.and()
//		.logout()
//		.logoutSuccessUrl("/")
//		;
		
	}
	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		// TODO Auto-generated method stub
//		//web.ignoring().antMatchers("/resources/static/**","/css/**","/js/**");
//	}
	
	
//	public WebMvcConfigurer corsConfigurer()
//	{
//		return new WebMvcConfigurer() {
//			public void addCorsMapping(CorsRegistry reg)
//			{
//				reg
//					.addMapping("/*")
//					.allowedOrigins("*")
//					.allowedMethods("GET","POST","PATCH","PUT","DELETE")
//					.allowedHeaders("Origin","Content-Type","Accept")
//					.allowCredentials(true)
//				;
//			}
//		};
//	}
}
