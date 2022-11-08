package com.ts.ws.ui.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName="of")
public class UserRest {

	private String firstName;
	private String lastName;
	private String email;
	private String userId;
}
