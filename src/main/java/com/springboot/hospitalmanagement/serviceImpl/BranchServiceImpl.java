package com.springboot.hospitalmanagement.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.hospitalmanagement.dto.BranchDto;
import com.springboot.hospitalmanagement.dto.BranchResponse;
import com.springboot.hospitalmanagement.dto.HospitalDto;
import com.springboot.hospitalmanagement.entitites.Branch;
import com.springboot.hospitalmanagement.entitites.Hospital;
import com.springboot.hospitalmanagement.exceptions.ResourceNotFoundException;
import com.springboot.hospitalmanagement.repositories.BranchRepository;
import com.springboot.hospitalmanagement.repositories.HospitalRepository;
import com.springboot.hospitalmanagement.service.BranchService;

@Service
public class BranchServiceImpl implements BranchService{
	
	@Autowired
	private BranchRepository branchrepo;
	
	@Autowired
	private HospitalRepository hospitalrepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public BranchDto saveBranch(BranchDto branchDto, int hospitalId) {
		Hospital hospital = hospitalrepo.findById(hospitalId).orElseThrow(()-> new ResourceNotFoundException("Hospital"," id ", hospitalId));
		Branch branch = modelMapper.map(branchDto,Branch.class);
		branch.setHospital(hospital);
		Branch branchdetails = branchrepo.save(branch);
		BranchDto savedBranchDto = modelMapper.map(branchdetails, BranchDto.class);
		savedBranchDto.setHospital(modelMapper.map(hospital,HospitalDto.class));
		return savedBranchDto;
	}

	@Override
	public BranchDto getBranch(int branchId) {
		Branch branch = branchrepo.findById(branchId).orElseThrow(()-> new ResourceNotFoundException("Branch"," id ", branchId));
		Hospital hospital = branch.getHospital();
		HospitalDto hospitalDto = modelMapper.map(hospital,HospitalDto.class);
		BranchDto branchdto = modelMapper.map(branch,BranchDto.class);
		branchdto.setHospital(hospitalDto);
		return branchdto;
	}

	@Override
	public BranchDto updateBranch(BranchDto branchDto, int branchId) {
		Branch branch = branchrepo.findById(branchId).orElseThrow(()-> new ResourceNotFoundException("Branch"," id ", branchId));
		branch.setBranch_email(branchDto.getBranch_email());
		branch.setBranch_name(branchDto.getBranch_name());
		Branch branchupdated = branchrepo.save(branch);
		BranchDto updatedBranch = modelMapper.map(branchupdated,BranchDto.class);
		updatedBranch.setHospital(modelMapper.map(branch.getHospital(),HospitalDto.class));
		return updatedBranch;
	}

	@Override
	public void deleteBranch(int branchId) {
		@SuppressWarnings("unused")
		Branch branch = branchrepo.findById(branchId).orElseThrow(()-> new ResourceNotFoundException("Branch"," id ", branchId));
		branchrepo.deleteById(branchId);
	}

	@Override
	public BranchResponse getAllBranches(int pageno, int pagesize, String sortBy, String sortDir) {
		Pageable p = PageRequest.of(pageno, pagesize);
		Page<Branch> branches = branchrepo.findAll(p);
		List<Branch> branchesList = branches.getContent();
		BranchResponse branchresponse = new BranchResponse();
		branchresponse.setBranchDtos(branchesList.stream().map(branch->modelMapper.map(branch,BranchDto.class)).collect(Collectors.toList()));
		branchresponse.setPageNo(pageno);
		branchresponse.setPageSize(pagesize);
		branchresponse.setTotalElements(branches.getTotalElements());
		branchresponse.setTotalPages(branches.getTotalPages());
		branchresponse.setLast(branches.isLast());
		return branchresponse;
	}

	
}
