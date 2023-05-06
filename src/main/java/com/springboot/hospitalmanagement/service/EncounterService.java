package com.springboot.hospitalmanagement.service;

import java.util.List;

import com.springboot.hospitalmanagement.dto.EncounterDto;

public interface EncounterService {
	
	public EncounterDto saveEncounter(EncounterDto encounterDto,int branchId,int patientId);
	
	public EncounterDto getEncounter(int encounterId);
	
	public EncounterDto updateEncounter(EncounterDto encounterDto , int branchId,int patientId ,int encounterId);
	
	public void deleteEncounter(int branchId);
	
	public List<EncounterDto> getEncounterDetailsByBranch(int branchId);
	
	public List<EncounterDto> getEncounterDetailsByPatient(int patientId);
	
	public List<EncounterDto> getAllEncounters(int pageno , int pagesize ,String sortBy , String sortDir);

}
