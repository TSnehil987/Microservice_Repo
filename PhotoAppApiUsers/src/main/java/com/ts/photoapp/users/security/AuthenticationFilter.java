package com.ts.photoapp.users.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ts.photoapp.users.dto.UserDTO;
import com.ts.photoapp.users.model.LoginRequestModel;
import com.ts.photoapp.users.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private Environment env;
	
	private UserService userService;

	@Autowired
	public AuthenticationFilter(UserService userService, Environment env) {
		this.env = env;
		this.userService = userService;
	}

	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			LoginRequestModel creds = new ObjectMapper().readValue(request.getInputStream(), LoginRequestModel.class);
			return getAuthenticationManager().authenticate(
						new UsernamePasswordAuthenticationToken(
								creds.getEmail(), 
								creds.getPassword(), 
								new ArrayList<>()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String username = ((User) authResult.getPrincipal()).getUsername();
		if(userService == null) System.out.println("Null Pointer Exception");
		UserDTO userDetails = userService.getUserByEmail(username);
		
		String token = Jwts.builder().setSubject(userDetails.getUserId())
		.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(env.getProperty("token.expiration_time"))))
		.signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
		.compact();
		
		response.addHeader("token", token);
		response.addHeader("userId", userDetails.getUserId());
	}
}
