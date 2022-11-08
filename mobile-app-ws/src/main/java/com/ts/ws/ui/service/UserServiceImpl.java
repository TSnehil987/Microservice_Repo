package com.ts.ws.ui.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ts.ws.ui.model.request.UserDetailRequestModel;
import com.ts.ws.ui.model.response.UserRest;
import com.ts.ws.ui.utils.Utils;

@Service
@Qualifier("UserServiceQualifier")
public class UserServiceImpl implements UserService {
	
	Utils utils;
	
	public UserServiceImpl() {
	}
	
	@Autowired
	public UserServiceImpl(Utils utils) {
		this.utils = utils;
	}

	@Override
	public UserRest createUser(UserDetailRequestModel userDetails) {
		UserRest response = UserRest.of(userDetails.getFirstName(), userDetails.getLastName(), 
				userDetails.getEmail(), null);
		
		var userId = utils.generateUserId();
		response.setUserId(userId);
		
		return response;
	}

}
