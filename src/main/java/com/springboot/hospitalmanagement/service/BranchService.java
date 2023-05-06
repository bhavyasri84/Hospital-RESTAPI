package com.springboot.hospitalmanagement.service;

import com.springboot.hospitalmanagement.dto.BranchDto;
import com.springboot.hospitalmanagement.dto.BranchResponse;

public interface BranchService {
	public BranchDto saveBranch(BranchDto BranchDto,int hospitalId);
	
	public BranchDto getBranch(int branchId);
	
	public BranchDto updateBranch(BranchDto BranchDto , int branchId);
	
	public void deleteBranch(int branchId);
	
	public BranchResponse getAllBranches(int pageno , int pagesize ,String sortBy , String sortDir);
	

}
