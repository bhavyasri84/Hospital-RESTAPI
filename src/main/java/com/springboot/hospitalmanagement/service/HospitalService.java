package com.springboot.hospitalmanagement.service;


import com.springboot.hospitalmanagement.dto.HospitalDto;
import com.springboot.hospitalmanagement.dto.HospitalResponse;


public interface HospitalService {
	
	public HospitalDto saveHospital(HospitalDto hospitaldto);
	
	public HospitalDto getHospital(int hospitalId);
	
	public HospitalDto updateHospital(HospitalDto hospitaldto , int hospitalId);
	
	public void deleteHospital(int hospitalId);
	
	public HospitalResponse getAllHospitals(int pageno , int pagesize ,String sortBy , String sortDir);
	
	
	
	
	

}
