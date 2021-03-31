package com.shahnawaz.todo.config;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import com.shahnawaz.todo.jwt.JwtFilter;
import com.shahnawaz.todo.services.MyuserdetailService;



@Configuration
@EnableWebSecurity
public class MyConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	MyuserdetailService myuserDetailService;
	@Autowired
	JwtFilter jwtfilter;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(myuserDetailService);
	}
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//always add cors config in this configure method at top only
		http=http.cors().and();
//		http.cors(
//				c -> {
//					CorsConfigurationSource cs= r -> {
//						CorsConfiguration cc=new CorsConfiguration();
//						cc.setAllowedOrigins(List.of("*"));
//						cc.setAllowedMethods(List.of("*"));
//						cc.setMaxAge(0L);
//						return cc;
//					};
//					c.configurationSource(cs);
//				}
//				);
		//.and().
		
		http.csrf().disable().authorizeRequests()
		
		.antMatchers("/signup","/login","/forgotpassword","/validateOtp","/resetPassword").permitAll()
		
		.anyRequest().authenticated()
		.and().exceptionHandling().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		
		http.addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);
	}
	
//	@Bean
//	org.apache.catalina.filters.CorsFilter CorsFilter() {
//		UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
//		CorsConfiguration config=new CorsConfiguration();
//		config.setAllowCredentials(true);
//		config.addAllowedOrigin("http://localhost:4200");
//		config.addAllowedHeader("*");
//		config.addAllowedMethod("*");
//		source.registerCorsConfiguration("/**", config);
//		return new org.springframework.web.filter.CorsFilter(config);
//	}
	
//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration configuration=new CorsConfiguration();
//		configuration.setAllowedOrigins(java.util.Arrays.asList("http://localhost:4200"));
//		configuration.setAllowedMethods(java.util.Arrays.asList("*"));
//		UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//		return  (CorsConfigurationSource) source;
//		
//	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); 
	}
}
