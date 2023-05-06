package com.springboot.hospitalmanagement.entitites;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Branch {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "branch_id")
	private int id;
	
	@Column(name = "branch_name" , nullable = false)
	private String branch_name;
	
	@Column(nullable = false)
	private String branch_email;
	
	@ManyToOne
	@JoinColumn(name = "hospital_id")
	private Hospital hospital;
	
	
	@OneToOne(mappedBy = "branch",cascade = CascadeType.ALL)
	private Address address;
	
	@OneToMany(mappedBy = "branch",cascade = CascadeType.ALL)
	private List<Encounter> encounter;
	

}
