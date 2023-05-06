package com.springboot.hospitalmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class AddressDto {
	
	private int id;
	
	@NotBlank
	@Size(min = 3,message="location must contain atleast 3 characters")
	private String location;
	
	private BranchDto branch;

}
