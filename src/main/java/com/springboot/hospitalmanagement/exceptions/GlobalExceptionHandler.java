package com.springboot.hospitalmanagement.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodNotValidException(MethodArgumentNotValidException ex){
		Map<String,String> resp = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error ->{
			String message = error.getDefaultMessage();
			String fieldName = ((FieldError)error).getField();
			resp.put(fieldName,message);
		});
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String,String>> handleResourceException(ResourceNotFoundException ex){
		Map<String,String> resp = new HashMap<>();
		resp.put("message", ex.getMessage());
		resp.put("status", "fail");
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.NOT_FOUND);
	}

}
