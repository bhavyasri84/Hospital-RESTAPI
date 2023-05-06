package com.springboot.hospitalmanagement.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.hospitalmanagement.config.AppConstants;
import com.springboot.hospitalmanagement.dto.PatientDto;
import com.springboot.hospitalmanagement.service.PatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
	
	@Autowired
	private PatientService patientservice;
	
	@PostMapping("/")
	public ResponseEntity<PatientDto> savePatient(@Valid @RequestBody PatientDto patientdto){
		PatientDto createdPatient = patientservice.savePatient(patientdto);
		return new ResponseEntity<PatientDto>(createdPatient,HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PatientDto> fetchPatient(@PathVariable("id") int patientId){
		PatientDto patientdetails = patientservice.getPatient(patientId);
		return new ResponseEntity<PatientDto>(patientdetails,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PatientDto> updatePatient(@Valid @RequestBody PatientDto patientdto,@PathVariable("id") int patientId){
		PatientDto patientdetails = patientservice.updatePatient(patientdto,patientId);
		return new ResponseEntity<PatientDto>(patientdetails,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String,String>> deletePatient(@PathVariable("id") int patientId){
		patientservice.deletePatient(patientId);
		Map<String,String> resp = new HashMap<>();
		resp.put("message", "Hospital deletion successful");
		resp.put("status", "success");
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<PatientDto>> getAllPatients(
		@RequestParam(value="pageno",defaultValue =AppConstants.PAGE_NO,required=false) int pageno,
		@RequestParam(value="pagesize",defaultValue =AppConstants.PAGE_SIZE,required=false) int pagesize,
		@RequestParam(value="sortBy",defaultValue =AppConstants.SORTBY,required=false) String sortBy,
		@RequestParam(value="sortDir",defaultValue =AppConstants.SORTDIR,required=false) String sortDir
		){
		List<PatientDto> allPatients = patientservice.getAllPatients(pageno, pagesize, sortBy, sortDir);
		return new ResponseEntity<List<PatientDto>>(allPatients,HttpStatus.OK);
	}
	
	

}
