package com.ts.ws.ui.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ts.ws.ui.model.response.ErrorMessage;

// To register this class with framework and make it listen to the exceptions that occurs in our 
// application across all the mappings, we need this annotation. Without this, it will not be able to listen to 
// the exceptions that takes place in our application.
@ControllerAdvice	
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

	//Method that handles the exception needs to be annotated with @ExceptionHandler
	@ExceptionHandler(value= {Exception.class})
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request){
		
		String errorMessageDesc = ex.getLocalizedMessage();
		
		if(errorMessageDesc == null) errorMessageDesc = ex.toString();
		ErrorMessage errorMessage = ErrorMessage.of(new Date(), errorMessageDesc);
		
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// This method handles both NullPointerException as well as UserServiceException as there is no much change 
	// in business logic. Hence commenting below method
	@ExceptionHandler(value= {NullPointerException.class, UserServiceException.class})
	public ResponseEntity<Object> handleSpecificException(Exception ex, WebRequest request){
		
		String errorMessageDesc = ex.getLocalizedMessage();
		
		if(errorMessageDesc == null) errorMessageDesc = ex.toString();
		ErrorMessage errorMessage = ErrorMessage.of(new Date(), errorMessageDesc);
		
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
//	@ExceptionHandler(value= {UserServiceException.class})
//	public ResponseEntity<Object> handleUserServiceException(UserServiceException ex, WebRequest request){
//		
//		String errorMessageDesc = ex.getLocalizedMessage();
//		
//		if(errorMessageDesc == null) errorMessageDesc = ex.toString();
//		ErrorMessage errorMessage = ErrorMessage.of(new Date(), errorMessageDesc);
//		
//		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
//	}
}
