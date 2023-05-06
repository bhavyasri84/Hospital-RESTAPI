package com.springboot.hospitalmanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.hospitalmanagement.entitites.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Integer>{
	
	public List<Hospital> findBynameContaining(String name);

}
