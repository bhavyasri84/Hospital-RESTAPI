package com.springboot.hospitalmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.hospitalmanagement.entitites.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer>{

}
