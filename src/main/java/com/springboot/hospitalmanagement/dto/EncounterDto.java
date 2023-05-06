package com.springboot.hospitalmanagement.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class EncounterDto {
	
	private int id;
	
	private LocalDate dateOfEncounter;
	
	@NotNull
	@Size(min=3,message  = "reason must contain atleast 3 characaters")
	private String reason;
	
	private BranchDto branch;
	
	
	private PatientDto patient;

}
