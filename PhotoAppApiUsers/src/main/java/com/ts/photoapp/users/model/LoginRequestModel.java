package com.ts.photoapp.users.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class LoginRequestModel {
	
	private String email;
	
	private String password;
}
