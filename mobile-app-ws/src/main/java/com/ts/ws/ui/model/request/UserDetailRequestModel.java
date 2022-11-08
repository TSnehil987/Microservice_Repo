package com.ts.ws.ui.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName="of")
public class UserDetailRequestModel {

	@NotNull(message="First Name cannot be null")
	private String firstName;

	@NotNull(message="Last Name cannot be null")
	private String lastName;
	
	@Email
	@NotNull(message="Email cannot be null")
	private String email;
	
	@NotNull(message="Password cannot be null")
	@Size(min=5, max= 30, message="Email can be in range of 5 to 30")
	private String password;
}
