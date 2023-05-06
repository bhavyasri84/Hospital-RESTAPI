package com.springboot.hospitalmanagement.entitites;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="MedOrder")
@Setter
@Getter
public class MedOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String message;
	private LocalDate orderDate;
	private LocalDate deliveryDate;
	
	@ManyToOne
	@JoinColumn(name="encounter_id")
	private Encounter encounter;
	
	@OneToMany(mappedBy = "order" , cascade = CascadeType.ALL)
	private List<MedItem> medItemsList = new ArrayList<>();

}
