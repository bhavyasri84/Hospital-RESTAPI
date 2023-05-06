package com.springboot.hospitalmanagement.controllers;

import java.util.HashMap;
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
import com.springboot.hospitalmanagement.dto.HospitalDto;
import com.springboot.hospitalmanagement.dto.HospitalResponse;
import com.springboot.hospitalmanagement.service.HospitalService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/hospital")
public class HospitalController {
	
	@Autowired
	private HospitalService hospitalService;
	
	@PostMapping("/")
	public ResponseEntity<HospitalDto> savehospital(@Valid @RequestBody HospitalDto hospitaldto){
		HospitalDto createdhospital = hospitalService.saveHospital(hospitaldto);
		return new ResponseEntity<HospitalDto>(createdhospital,HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<HospitalDto> fetchhospital(@PathVariable("id") int hospitalId){
		HospitalDto hospitaldetails = hospitalService.getHospital(hospitalId);
		return new ResponseEntity<HospitalDto>(hospitaldetails,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<HospitalDto> fetchhospital(@Valid @RequestBody HospitalDto hospitaldto,@PathVariable("id") int hospitalId){
		HospitalDto hospitaldetails = hospitalService.updateHospital(hospitaldto,hospitalId);
		return new ResponseEntity<HospitalDto>(hospitaldetails,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String,String>> deletehospital(@PathVariable("id") int hospitalId){
		hospitalService.deleteHospital(hospitalId);
		Map<String,String> resp = new HashMap<>();
		resp.put("message", "Hospital deletion successful");
		resp.put("status", "success");
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<HospitalResponse> getAllhospital(
		@RequestParam(value="pageno",defaultValue =AppConstants.PAGE_NO,required=false) int pageno,
		@RequestParam(value="pagesize",defaultValue =AppConstants.PAGE_SIZE,required=false) int pagesize,
		@RequestParam(value="sortBy",defaultValue =AppConstants.SORTBY,required=false) String sortBy,
		@RequestParam(value="sortDir",defaultValue =AppConstants.SORTDIR,required=false) String sortDir
			
		){
		HospitalResponse allHospitals = hospitalService.getAllHospitals(pageno, pagesize, sortBy, sortDir);
		
		return new ResponseEntity<HospitalResponse>(allHospitals,HttpStatus.OK);
	}
	

}
