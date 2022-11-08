package com.ts.photoapp.users.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ts.photoapp.users.dto.UserDTO;

public interface UserService extends UserDetailsService {

	UserDTO createUser(UserDTO userDetails);
	
	UserDTO getUserByEmail(String email);
}
