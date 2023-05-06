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

import com.springboot.hospitalmanagement.dto.AddressDto;
import com.springboot.hospitalmanagement.entitites.Address;
import com.springboot.hospitalmanagement.entitites.Branch;
import com.springboot.hospitalmanagement.exceptions.ResourceNotFoundException;
import com.springboot.hospitalmanagement.repositories.AddressRepository;
import com.springboot.hospitalmanagement.repositories.BranchRepository;
import com.springboot.hospitalmanagement.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	private BranchRepository branchrepo;
	
	@Autowired
	private AddressRepository addressrepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public AddressDto saveAddress(AddressDto addressdto, int branchId) {
		Branch branch = branchrepo.findById(branchId).orElseThrow(()->new ResourceNotFoundException("Branch"," id ", branchId));
		Address address = modelMapper.map(addressdto, Address.class);
		address.setBranch(branch);
		Address savedAddress = addressrepo.save(address);
		AddressDto savedAddressDto = modelMapper.map(savedAddress,AddressDto.class);
		return savedAddressDto;
	}

	@Override
	public AddressDto getAddress(int addressId) {
		Address address = addressrepo.findById(addressId).orElseThrow(()->new ResourceNotFoundException("Address"," id ", addressId));
		AddressDto addressDto = modelMapper.map(address,AddressDto.class);
		return addressDto;
	}

	@Override
	public AddressDto updateAddress(AddressDto addressDto, int addressId) {
		Address address = addressrepo.findById(addressId).orElseThrow(()->new ResourceNotFoundException("Address"," id ", addressId));
		address.setLocation(modelMapper.map(addressDto,AddressDto.class).getLocation());
		Address updatedAddress = addressrepo.save(address);
		return modelMapper.map(updatedAddress,AddressDto.class);
	}

	@Override
	public void deleteAddress(int addressId) {
		@SuppressWarnings("unused")
		Address address = addressrepo.findById(addressId).orElseThrow(()->new ResourceNotFoundException("Address"," id ", addressId));
		addressrepo.deleteById(addressId);
	}

	@Override
	public List<AddressDto> findAddressofBranch(int branchId) {
		Branch branch = branchrepo.findById(branchId).orElseThrow(()->new ResourceNotFoundException("Branch"," id ", branchId));
		List<Address> addressofBranch = addressrepo.findByBranch(branch);
		return addressofBranch.stream().map(address->modelMapper.map(address,AddressDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<AddressDto> getAllAddressAndBranchDetails(int pageno, int pagesize, String sortBy, String sortDir) {
		Sort sort = "asc".equalsIgnoreCase(sortDir)? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
		Pageable p = PageRequest.of(pageno, pagesize,sort);
		Page<Address> addresses = addressrepo.findAll(p);
		List<Address> addressList = addresses.getContent();
		return addressList.stream().map(address->modelMapper.map(address,AddressDto.class)).collect(Collectors.toList());
	}
	
	

	
}
