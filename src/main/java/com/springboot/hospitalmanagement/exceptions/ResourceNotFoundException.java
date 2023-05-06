package com.springboot.hospitalmanagement.exceptions;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Setter
@Getter
public class ResourceNotFoundException extends RuntimeException{
	
	private String resourceName ;
	private long fieldvalue;
	private String fieldname;
	
	public ResourceNotFoundException(String resourceName ,String fieldname , long fieldvalue) {
		super(String.format("%s not found with %s : %s",resourceName , fieldname,fieldvalue));
		this.resourceName = resourceName;
		this.fieldname = fieldname;
		this.fieldvalue = fieldvalue;
		
	}
	

}
