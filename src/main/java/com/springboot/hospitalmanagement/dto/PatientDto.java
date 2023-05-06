package com.springboot.hospitalmanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatientDto {
	
	private int id;
	private String name;
	@Email(message = "enter valid Email")
	private String email;
	
	@NotBlank
	@Size(min=5, message="password must contain atleast 5 characters")
	private String password;
	
	private String gender;
	
	@NotBlank
	@Size(min =10 , message = "phoneno should contain 10 digits")
	private String phone;

}
