package com.springboot.hospitalmanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class BranchDto {
	
	
	private int id ;
	@NotEmpty
	@Size(min = 4 ,message = "name must cntain atleast 4 characters")
	private String branch_name;
	
	@Email(message = "provide valid email")
	private String branch_email;
	
	private HospitalDto hospital;

}
