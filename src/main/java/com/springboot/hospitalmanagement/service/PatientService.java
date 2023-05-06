package com.springboot.hospitalmanagement.service;

import java.util.List;

import com.springboot.hospitalmanagement.dto.PatientDto;

public interface PatientService {
	public PatientDto savePatient(PatientDto patientdto);
	
	public PatientDto getPatient(int patientId);
	
	public PatientDto updatePatient(PatientDto patientdto,int patientId);
	
	public void deletePatient(int patientId);
	
	public List<PatientDto> getAllPatients(int pageno , int pagesize ,String sortBy , String sortDir);

}
