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

import com.springboot.hospitalmanagement.dto.HospitalDto;
import com.springboot.hospitalmanagement.dto.HospitalResponse;
import com.springboot.hospitalmanagement.entitites.Hospital;
import com.springboot.hospitalmanagement.exceptions.ResourceNotFoundException;
import com.springboot.hospitalmanagement.repositories.HospitalRepository;
import com.springboot.hospitalmanagement.service.HospitalService;

@Service
public class HospitalServiceImpl implements HospitalService{
	
	@Autowired
	private HospitalRepository hospitalrepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public HospitalDto saveHospital(HospitalDto hospitaldto) {
		Hospital hospital = hospitalrepo.save(modelMapper.map(hospitaldto, Hospital.class));
		return modelMapper.map(hospital,HospitalDto.class);
	}

	@Override
	public HospitalDto getHospital(int hospitalId) {
		Hospital hospital = hospitalrepo.findById(hospitalId).orElseThrow(()-> new ResourceNotFoundException("Hospital"," id ", hospitalId));
		return modelMapper.map(hospital,HospitalDto.class);
	}

	@Override
	public HospitalDto updateHospital(HospitalDto hospitaldto, int hospitalId) {
		@SuppressWarnings("unused")
		Hospital hospital = hospitalrepo.findById(hospitalId).orElseThrow(()-> new ResourceNotFoundException("Hospital"," id ", hospitalId));
		hospitaldto.setId(hospitalId);
		Hospital updatedHospital = hospitalrepo.save(modelMapper.map(hospitaldto,Hospital.class));
		return modelMapper.map(updatedHospital,HospitalDto.class);
	}

	@Override
	public void deleteHospital(int hospitalId) {
		@SuppressWarnings("unused")
		Hospital hospital = hospitalrepo.findById(hospitalId).orElseThrow(()-> new ResourceNotFoundException("Hospital"," id ", hospitalId));
		hospitalrepo.deleteById(hospitalId);
	}

	@Override
	public HospitalResponse getAllHospitals(int pageno, int pagesize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending():  Sort.by(sortBy).descending();
		Pageable p = PageRequest.of(pageno, pagesize, sort);
		Page<Hospital> hospitals = hospitalrepo.findAll(p);
		List<Hospital> hospitalList = hospitals.getContent();
		HospitalResponse hospitalDetails = new HospitalResponse();
		hospitalDetails.setHospitalDtos(hospitalList.stream().map(hospital->modelMapper.map(hospital,HospitalDto.class)).collect(Collectors.toList()));
		hospitalDetails.setPageNo(pageno);
		hospitalDetails.setPageSize(pagesize);
		hospitalDetails.setTotalElements(hospitals.getTotalElements());
		hospitalDetails.setTotalPages(hospitals.getTotalPages());
		hospitalDetails.setLast(hospitals.isLast());
		return hospitalDetails;
	}

}
