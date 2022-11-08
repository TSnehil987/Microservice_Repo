package com.ts.ws.ui.model.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName="of")
public class ErrorMessage {

	private Date timestamp;
	
	private String message;
}
