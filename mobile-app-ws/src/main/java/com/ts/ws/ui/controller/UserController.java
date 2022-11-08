package com.ts.ws.ui.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ts.ws.ui.exceptions.UserServiceException;
import com.ts.ws.ui.model.request.UserDetailRequestModel;
import com.ts.ws.ui.model.response.UserRest;
import com.ts.ws.ui.service.UserService;

@RestController
@RequestMapping("/users") // http://localhost:8080/users/....
public class UserController {
	
	Map<String, UserRest> users;
	
	@Autowired
	@Qualifier("UserServiceQualifier")
	private UserService userService;

	@GetMapping
	public String getUsers(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "limit", defaultValue = "50", required = false) int limit) {
		return "get user was called with page " + page + " & limit= " + limit;
	}

	@SuppressWarnings("unused")
	@GetMapping(path = "/{userId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserRest> getUser(@PathVariable("userId") String userId) {
		//UserRest response = UserRest.of("Tanya", "Snehil", "tanyasnehil23@gmail.com", userId);
		
		//This will throw UserServiceException
		//if(true) throw new UserServiceException("User Service Exception Thrown");
		
		if(users.containsKey(userId)) {
			return new ResponseEntity<UserRest>(users.get(userId), HttpStatus.OK);
		}else {
			return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);
		}
		
	}

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailRequestModel userDetails) {
//		UserRest response = UserRest.of(userDetails.getFirstName(), userDetails.getLastName(), 
//				userDetails.getEmail(), null);
//		
//		//This will result in NullPointerException
////		String firstName = null;
////		int firstNameLength = firstName.length();
//		
//		var userId = UUID.randomUUID().toString();
//		response.setUserId(userId);
		var responses = userService.createUser(userDetails);
		if(users == null) users = new HashMap<>();
		users.put(responses.getUserId(), responses);
		
		return new ResponseEntity<UserRest>(responses, HttpStatus.OK);
	}

	@PutMapping(path="/{userId}", consumes= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public UserRest updateUser(@PathVariable("userId") String userId, @RequestBody UserDetailRequestModel userDetail) {
		UserRest storedUserDetails = users.get(userId);
		storedUserDetails.setFirstName(userDetail.getFirstName());
		storedUserDetails.setLastName(userDetail.getLastName());
		users.put(userId, storedUserDetails);
		return storedUserDetails;
	}

	@DeleteMapping(path="/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable("userId") String userId) {
		users.remove(userId);
		return ResponseEntity.noContent().build();
	}
}
