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
import com.springboot.hospitalmanagement.dto.BranchDto;
import com.springboot.hospitalmanagement.dto.BranchResponse;
import com.springboot.hospitalmanagement.service.BranchService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class BranchController {
	
	@Autowired
	private BranchService branchService;
	
	@PostMapping("/hospital/{id}/branch/")
	public ResponseEntity<BranchDto> saveBranch(@Valid @RequestBody BranchDto branchdto,@PathVariable("id") int hospitalId){
		BranchDto branch = branchService.saveBranch(branchdto, hospitalId);
		return new ResponseEntity<BranchDto>(branch,HttpStatus.CREATED);
	}
	
	@GetMapping("/branch/{id}")
	public ResponseEntity<BranchDto> fetchBranch(@PathVariable("id") int branchId){
		BranchDto branch = branchService.getBranch(branchId);
		return new ResponseEntity<BranchDto>(branch,HttpStatus.OK);
	}
	
	@PutMapping("/branch/{id}")
	public ResponseEntity<BranchDto> updateBranch(@Valid @RequestBody BranchDto branchdto , @PathVariable("id") int branchId){
		BranchDto updatedBranch = branchService.updateBranch(branchdto, branchId);
		return new ResponseEntity<BranchDto>(updatedBranch,HttpStatus.OK);
	}
	
	@DeleteMapping("/branch/{id}")
	public ResponseEntity<Map<String,String>> deleteBranch(@PathVariable("id") int branchId){
		branchService.deleteBranch(branchId);
		Map<String,String> resp = new HashMap<>();
		resp.put("message","branch with id "+branchId+" deletion successful");
		resp.put("status", "success");
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.OK);
	}
	
	@GetMapping("/branches/")
	public ResponseEntity<BranchResponse> getBranches(
		@RequestParam(value = "pageno" , defaultValue = AppConstants.PAGE_NO,required = false) int pageno,
		@RequestParam(value = "pagesize" , defaultValue = AppConstants.PAGE_SIZE,required = false) int pagesize,
		@RequestParam(value = "sortby" , defaultValue = AppConstants.SORTBY,required = false) String sortby,
		@RequestParam(value = "sortdir" , defaultValue = AppConstants.SORTDIR,required = false) String sortdir
		){
		
		BranchResponse branches = branchService.getAllBranches(pageno, pagesize, sortby, sortdir);
		return new ResponseEntity<BranchResponse>(branches,HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	

}
