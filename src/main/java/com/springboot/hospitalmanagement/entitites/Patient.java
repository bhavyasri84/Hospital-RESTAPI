package com.springboot.hospitalmanagement.entitites;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	
	@Column(unique = true)
	private String email;
	private String password;
	private String gender;
	@Column(unique = true)
	private long phone;
	
	@OneToMany(mappedBy = "patient")
	private List<Encounter> encounter;

}
