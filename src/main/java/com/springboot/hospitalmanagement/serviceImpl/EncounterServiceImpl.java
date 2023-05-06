package com.springboot.hospitalmanagement.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.hospitalmanagement.dto.EncounterDto;
import com.springboot.hospitalmanagement.entitites.Branch;
import com.springboot.hospitalmanagement.entitites.Encounter;
import com.springboot.hospitalmanagement.entitites.Patient;
import com.springboot.hospitalmanagement.exceptions.ResourceNotFoundException;
import com.springboot.hospitalmanagement.repositories.BranchRepository;
import com.springboot.hospitalmanagement.repositories.EncounterRepository;
import com.springboot.hospitalmanagement.repositories.PatientRepository;
import com.springboot.hospitalmanagement.service.EncounterService;

@Service
public class EncounterServiceImpl implements EncounterService{
	
	@Autowired
	private EncounterRepository encounterrepo;
	
	@Autowired
	private BranchRepository branchrepo;
	
	@Autowired
	private PatientRepository patientrepo;

	
	@Autowired
	private ModelMapper modelMapper;


	@Override
	public EncounterDto saveEncounter(EncounterDto encounterDto, int branchId,int patientId) {
		
		Branch branch = branchrepo.findById(branchId).orElseThrow(()-> new ResourceNotFoundException("Branch"," id ", branchId));
		Patient patient = patientrepo.findById(patientId).orElseThrow(()-> new ResourceNotFoundException("Patient"," id ", patientId));
		Encounter encounter = modelMapper.map(encounterDto,Encounter.class);
		encounter.setBranch(branch);
		encounter.setPatient(patient);
		encounter.setDateOfEncounter(LocalDate.now());
		Encounter savedEncounter = encounterrepo.save(encounter);
		return modelMapper.map(savedEncounter,EncounterDto.class);
	}

	@Override
	public EncounterDto getEncounter(int encounterId) {
		Encounter encounter = encounterrepo.findById(encounterId).orElseThrow(()->new ResourceNotFoundException("Encounter"," id ", encounterId));
		EncounterDto encounterDto = modelMapper.map(encounter,EncounterDto.class);
		return encounterDto;
	}

	@Override
	public EncounterDto updateEncounter(EncounterDto encounterDto, int branchId , int patientId , int encounterId) {
		Encounter encounter = encounterrepo.findById(encounterId).orElseThrow(()->new ResourceNotFoundException("Encounter"," id ", encounterId));
		encounter.setReason(encounterDto.getReason());
		encounter.setDateOfEncounter(LocalDate.now());
		Encounter encounterUpdated = encounterrepo.save(encounter);
		EncounterDto updatedEncounter = modelMapper.map(encounterUpdated,EncounterDto.class);
		return updatedEncounter;
	}

	@Override
	public void deleteEncounter(int encounterId) {
		@SuppressWarnings("unused")
		Encounter encounter = encounterrepo.findById(encounterId).orElseThrow(()->new ResourceNotFoundException("Encounter"," id ", encounterId));
		encounterrepo.deleteById(encounterId);
	}

	@Override
	public List<EncounterDto> getAllEncounters(int pageno, int pagesize, String sortBy, String sortDir) {
		Sort sort = "asc".equalsIgnoreCase(sortDir)? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
		Pageable p = PageRequest.of(pageno, pagesize,sort);
		Page<Encounter> encounters = encounterrepo.findAll(p);
		List<Encounter> encountersList = encounters.getContent();
		return encountersList.stream().map(encounter->modelMapper.map(encounter,EncounterDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<EncounterDto> getEncounterDetailsByBranch(int branchId) {
		Branch branch = branchrepo.findById(branchId).orElseThrow(()-> new ResourceNotFoundException("Branch"," id ", branchId));
		List<Encounter> encountersList = encounterrepo.findByBranch(branch);
		return encountersList.stream().map(encounter->modelMapper.map(encounter,EncounterDto.class)).collect(Collectors.toList());
		
		
	}

	@Override
	public List<EncounterDto> getEncounterDetailsByPatient(int patientId) {
		Patient patient = patientrepo.findById(patientId).orElseThrow(()-> new ResourceNotFoundException("Patient"," id ", patientId));
		List<Encounter> encountersList = encounterrepo.findByPatient(patient);
		return encountersList.stream().map(encounter->modelMapper.map(encounter,EncounterDto.class)).collect(Collectors.toList());
		
	}

}
