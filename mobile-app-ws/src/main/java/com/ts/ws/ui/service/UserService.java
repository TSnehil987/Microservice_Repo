package com.ts.ws.ui.service;

import com.ts.ws.ui.model.request.UserDetailRequestModel;
import com.ts.ws.ui.model.response.UserRest;

public interface UserService {

	public UserRest createUser(UserDetailRequestModel userDetails);
}
