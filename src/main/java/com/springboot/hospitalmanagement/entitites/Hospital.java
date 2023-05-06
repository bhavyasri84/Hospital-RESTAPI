package com.springboot.hospitalmanagement.entitites;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Hospital {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "hospital_name",nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String phoneno;
	
	@Column(unique = true)
	private String email ;
	
	
	@OneToMany(mappedBy = "hospital",cascade = CascadeType.ALL)
	private List<Branch> branches = new ArrayList<>();
}
