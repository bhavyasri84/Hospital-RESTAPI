package com.springboot.hospitalmanagement.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.hospitalmanagement.dto.PatientDto;
import com.springboot.hospitalmanagement.entitites.Patient;
import com.springboot.hospitalmanagement.exceptions.ResourceNotFoundException;
import com.springboot.hospitalmanagement.repositories.PatientRepository;
import com.springboot.hospitalmanagement.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService{
	
	@Autowired
	private PatientRepository patientrepo;
	
	@Autowired
	private ModelMapper modelMapper; 

	@Override
	public PatientDto savePatient(PatientDto patientdto) {
		Patient patient = patientrepo.save(modelMapper.map(patientdto, Patient.class));
		return modelMapper.map(patient,PatientDto.class);
	}

	@Override
	public PatientDto getPatient(int patientId) {
		Patient patient = patientrepo.findById(patientId).orElseThrow(()-> new ResourceNotFoundException("Patient"," id ", patientId));
		return modelMapper.map(patient,PatientDto.class);
		
	}

	@Override
	public PatientDto updatePatient(PatientDto patientdto,int patientId) {
		Patient patient = patientrepo.findById(patientId).orElseThrow(()-> new ResourceNotFoundException("Patient"," id ", patientId));
		patientdto.setId(patient.getId());
		Patient updatedPatient = patientrepo.save(modelMapper.map(patientdto,Patient.class));
		return modelMapper.map(updatedPatient,PatientDto.class);
	}

	@Override
	public void deletePatient(int patientId) {
		@SuppressWarnings("unused")
		Patient patient = patientrepo.findById(patientId).orElseThrow(()-> new ResourceNotFoundException("Patient"," id ", patientId));
		patientrepo.findById(patientId);
	}

	@Override
	public List<PatientDto> getAllPatients(int pageno, int pagesize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending():  Sort.by(sortBy).descending();
		Pageable p = PageRequest.of(pageno, pagesize, sort);
		Page<Patient> patients = patientrepo.findAll(p);
		List<Patient> patientsList = patients.getContent();
		return patientsList.stream().map(patient->modelMapper.map(patient,PatientDto.class)).collect(Collectors.toList());
	}

	


}
