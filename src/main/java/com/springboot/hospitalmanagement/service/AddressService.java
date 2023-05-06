package com.springboot.hospitalmanagement.service;

import java.util.List;

import com.springboot.hospitalmanagement.dto.AddressDto;

public interface AddressService {
public AddressDto saveAddress(AddressDto AddressDto,int branchId);
	
	public AddressDto getAddress(int addressId);
	
	public AddressDto updateAddress(AddressDto AddressDto , int addressId);
	
	public void deleteAddress(int addressId);
	
	public List<AddressDto> findAddressofBranch(int branchId);
	
	public List<AddressDto> getAllAddressAndBranchDetails(int pageno , int pagesize ,String sortBy , String sortDir);

}
