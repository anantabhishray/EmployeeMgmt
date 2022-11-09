package com.greatLearning.employeeService.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//import com.greatLearning.employeeService.service.UserDetailsServiceImpl;

@SuppressWarnings("unused")
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .inMemoryAuthentication()
                .withUser("kiran")
                .password(passwordEncoder().encode("user"))
                .roles("USER")
                .and()
                .withUser("vinay")
                .password(passwordEncoder().encode("admin"))
                .roles("USER","ADMIN");

	}
	
	 @Override
	    protected void configure(HttpSecurity httpSecurity) throws Exception {
	        // configure authorization rules here
	        httpSecurity.cors().disable();
	        httpSecurity.csrf().disable();
	        httpSecurity
	                .authorizeRequests()
	                .antMatchers(HttpMethod.GET,"/api/employees/**")
	                .hasAnyRole("USER", "ADMIN")
	                .and()
	                .authorizeRequests()
	                .antMatchers(HttpMethod.POST,"/api/employees/**")
	                .hasRole("ADMIN")
	                .antMatchers(HttpMethod.PUT,"/api/employees/{employeeId}**")
	                .hasRole("ADMIN")
	                .antMatchers(HttpMethod.DELETE,"/api/employees/{employeeId}/**")
	                .hasRole("ADMIN")
	                .antMatchers(HttpMethod.GET,"/api/employees/search/{firstName}/**")
	                .hasRole("ADMIN")
	                .antMatchers(HttpMethod.GET,"/api/employees/sortAsc/**")
	                .hasAnyRole("USER","ADMIN")
	                .antMatchers(HttpMethod.GET,"/api/employees/sortDesc/**")
	                .hasAnyRole("USER","ADMIN")
	                .antMatchers(HttpMethod.POST,"/api/role/**")
	                .hasRole("ADMIN")
	                .antMatchers(HttpMethod.POST,"/api/user/**")
	                .hasRole("ADMIN")
	                .anyRequest()
	                .authenticated()
	                .and()
	                .formLogin()
	                .and()
	                .httpBasic()
	               .and()
	                /*
	                   Set the sessioncreation policy to avoid using cookies for authentication
	                   https://stackoverflow.com/questions/50842258/spring-security-caching-my-authentication/50847571
	                 */
	                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	  
	    }
	 
	 @Bean
	 public PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }

	
	
// Original Code below
//	@Bean
//	public UserDetailsService userDetailsService() {
//		return new UserDetailsServiceImpl();
//	}
//
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	@Bean
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/api/user", "/api/role").hasAuthority("ADMIN")
//				.antMatchers(HttpMethod.POST, "/api/employees").hasAuthority("ADMIN")
//				.antMatchers(HttpMethod.PUT, "/api/employees").hasAuthority("ADMIN")
//				.antMatchers(HttpMethod.DELETE, "/api/employees/*").hasAuthority("ADMIN")
//				.anyRequest().authenticated().and().httpBasic()
//				.and().cors().and().csrf().disable();
//	}
//
}
