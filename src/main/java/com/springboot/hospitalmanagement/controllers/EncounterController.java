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
import com.springboot.hospitalmanagement.dto.EncounterDto;
import com.springboot.hospitalmanagement.service.EncounterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class EncounterController {
	
	@Autowired
	private EncounterService encounterService;
	
	@PostMapping("/branch/{branchId}/patient/{patientId}/encounter")
	public ResponseEntity<EncounterDto> saveEncounter(@Valid @RequestBody EncounterDto encounterdto,@PathVariable int branchId,@PathVariable int patientId){
		EncounterDto savedEncounter = encounterService.saveEncounter(encounterdto, branchId,patientId);
		return new ResponseEntity<EncounterDto>(savedEncounter,HttpStatus.CREATED);
	}
	
	@GetMapping("/encounter/{id}")
	public ResponseEntity<EncounterDto> getEncounter(@PathVariable("id") int encounterId){
		EncounterDto encounter = encounterService.getEncounter(encounterId);
		return new ResponseEntity<EncounterDto>(encounter,HttpStatus.OK);
	}
	
	@PutMapping("/branch/{branchId}/patient/{patientId}/encounter/{id}")
	public ResponseEntity<EncounterDto> updateEncounter(@Valid @RequestBody EncounterDto encounterdto,@PathVariable("branchId") int branchId,@PathVariable("patientId") int patientId,@PathVariable("id") int encounterId){
		EncounterDto encounter = encounterService.updateEncounter(encounterdto,branchId,patientId,encounterId);
		return new ResponseEntity<EncounterDto>(encounter,HttpStatus.OK);
	}
	
	@DeleteMapping("/encounter/{id}")
	public ResponseEntity<Map<String,String>> deleteEncounter(@PathVariable("id") int encounterId){
		encounterService.deleteEncounter(encounterId);
		Map<String,String> resp = new HashMap<>();
		resp.put("message","deletion of encounter with id "+ encounterId+" successful");
		resp.put("status","successful");
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.OK);
	}
	
	@GetMapping("/encounters/")
	public ResponseEntity<List<EncounterDto>> getBranches(
			@RequestParam(value = "pageno" , defaultValue = AppConstants.PAGE_NO,required = false) int pageno,
			@RequestParam(value = "pagesize" , defaultValue = AppConstants.PAGE_SIZE,required = false) int pagesize,
			@RequestParam(value = "sortby" , defaultValue = AppConstants.SORTBY,required = false) String sortby,
			@RequestParam(value = "sortdir" , defaultValue = AppConstants.SORTDIR,required = false) String sortdir
			){
			
			List<EncounterDto> encounters = encounterService.getAllEncounters(pageno, pagesize, sortby, sortdir);
			
			return new ResponseEntity<List<EncounterDto>>(encounters,HttpStatus.OK);
			
		}
	
	
	@GetMapping("/encounter/branch/{id}")
	public ResponseEntity<List<EncounterDto>> getEncounterByBranch(@PathVariable("id") int branchId){
		List<EncounterDto> encounters = encounterService.getEncounterDetailsByBranch(branchId);
		return new ResponseEntity<List<EncounterDto>>(encounters,HttpStatus.OK);
	}
	
	@GetMapping("/encounter/patient/{id}")
	public ResponseEntity<List<EncounterDto>> getEncounterByPatient(@PathVariable("id") int patientId){
		List<EncounterDto> encounters = encounterService.getEncounterDetailsByPatient(patientId);
		return new ResponseEntity<List<EncounterDto>>(encounters,HttpStatus.OK);
	}
	
}
