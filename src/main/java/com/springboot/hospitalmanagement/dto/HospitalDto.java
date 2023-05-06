package com.springboot.hospitalmanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class HospitalDto {
	
	private int id;
	
	@NotNull
	@Size(min = 5 , message = "name must contain atleast 5 characters")
	private String name;
	
	@NotBlank
	@Size(min=10 , max =10 , message = "phoneno should be 10 digit")
	private String phoneno;
	
	@Email(message = "provide a valid email")
	private String email;

}
