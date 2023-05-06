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
import com.springboot.hospitalmanagement.dto.AddressDto;
import com.springboot.hospitalmanagement.service.AddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@PostMapping("/branch/{id}/address/")
	public ResponseEntity<AddressDto> saveAddress(@Valid @RequestBody AddressDto addressDto,@PathVariable("id") int branchId){
		AddressDto addresssaved = addressService.saveAddress(addressDto, branchId);
		return new ResponseEntity<AddressDto>(addresssaved,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/address/{id}")
	public ResponseEntity<AddressDto> fetchAddress(@PathVariable("id") int addressId){
		AddressDto address = addressService.getAddress(addressId);
		return new ResponseEntity<AddressDto>(address,HttpStatus.OK);
	}
	
	
	@GetMapping("/address/branch/{id}")
	public ResponseEntity<List<AddressDto>> getAddressByBranch(@PathVariable("id") int branchId){
		List<AddressDto> address = addressService.findAddressofBranch(branchId);
		return new ResponseEntity<List<AddressDto>>(address,HttpStatus.OK);
	}
	
	@PutMapping("/address/{id}/")
	public ResponseEntity<AddressDto> updateAddress(@Valid @RequestBody AddressDto addressdto,@PathVariable("id") int addressId){
		AddressDto address = addressService.updateAddress(addressdto,addressId);
		return new ResponseEntity<AddressDto>(address,HttpStatus.OK);
	}
	
	@DeleteMapping("/address/{id}")
	public ResponseEntity<Map<String,String>> deleteBranch(@PathVariable("id") int addressId){
		addressService.deleteAddress(addressId);
		Map<String,String> resp = new HashMap<>();
		resp.put("message","branch with id "+addressId+" deletion successful");
		resp.put("status", "success");
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.OK);
	}
	
	@GetMapping("/addresses/")
	public ResponseEntity<List<AddressDto>> getAllAddressesWithBranch(
			@RequestParam(value="pageno",defaultValue =AppConstants.PAGE_NO,required=false)int pageno,
			@RequestParam(value="pagesize",defaultValue =AppConstants.PAGE_SIZE,required=false)int pagesize,
			@RequestParam(value="sortby",defaultValue =AppConstants.SORTBY,required=false)String sortBy,
			@RequestParam(value="sortdir",defaultValue =AppConstants.SORTDIR,required=false)String sortDir
			){
		List<AddressDto> addresses = addressService.getAllAddressAndBranchDetails(pageno, pagesize, sortBy, sortDir);
		return new ResponseEntity<List<AddressDto>>(addresses,HttpStatus.OK);
	}
	

}
