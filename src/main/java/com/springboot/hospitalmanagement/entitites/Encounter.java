package com.springboot.hospitalmanagement.entitites;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Encounter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private LocalDate dateOfEncounter;
	
	private String reason;
	

	
	
	@ManyToOne
	@JoinColumn(name="branch_id")
	private Branch branch;
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	
	@OneToMany(mappedBy = "encounter")
	private List<MedOrder> medorder; 
	
	

}
