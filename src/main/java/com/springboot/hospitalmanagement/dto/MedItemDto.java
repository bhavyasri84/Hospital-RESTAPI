package com.springboot.hospitalmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MedItemDto {
	
	private int id;
	
	@NotBlank
	@Size(min=5,message = "item name must have atleast 5 characters")
	private String name;
	
	private double cost;

	private int quantity;
}
