package com.ts.ws.ui.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ts.ws.ui.model.request.UserDetailRequestModel;
import com.ts.ws.ui.model.response.UserRest;

@Service
@Qualifier("Test")
public class UserTestImpl implements UserService {

	@Override
	public UserRest createUser(UserDetailRequestModel userDetails) {
		UserRest response = UserRest.of("Hello", "World", 
				"xxxxxxxxxxxx", null);
		
		var userId = UUID.randomUUID().toString();
		response.setUserId(userId);
		
		return response;
	}

}
