package com.springboot.hospitalmanagement.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class MedOrderDto {
	
	private int id;
	@NotBlank
	@Size(min=5,message = "message must not be empty and should contain atleast 5 characters")
	private String message;
	private LocalDate orderDate;
	private LocalDate deliveryDate;
	private EncounterDto encounter;
	private List<MedItemDto> medItems = new ArrayList<>();

}
