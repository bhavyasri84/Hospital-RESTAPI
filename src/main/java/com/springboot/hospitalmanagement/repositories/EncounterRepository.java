package com.springboot.hospitalmanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.hospitalmanagement.entitites.Branch;
import com.springboot.hospitalmanagement.entitites.Encounter;
import com.springboot.hospitalmanagement.entitites.Patient;

public interface EncounterRepository extends JpaRepository<Encounter, Integer>{
	
	public List<Encounter> findByBranch(Branch branch);
	
	public List<Encounter> findByPatient(Patient patient);
	

}
