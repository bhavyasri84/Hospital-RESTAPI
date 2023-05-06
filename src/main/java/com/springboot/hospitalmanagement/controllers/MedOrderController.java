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
import com.springboot.hospitalmanagement.dto.MedOrderDto;
import com.springboot.hospitalmanagement.service.MedOrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class MedOrderController {

	@Autowired
	private MedOrderService medOrderService;
	
	@PostMapping("/encounter/{id}/medorder/")
	public ResponseEntity<MedOrderDto> saveMedicalOrder(@Valid @RequestBody MedOrderDto medorderdto,@PathVariable("id") int encounterId){
		MedOrderDto medorder = medOrderService.saveMedOrder(medorderdto, encounterId);
		return new ResponseEntity<MedOrderDto>(medorder,HttpStatus.CREATED);
	}
	
	@GetMapping("/medorder/{id}")
	public ResponseEntity<MedOrderDto> getMedOrder(@PathVariable("id") int medOrderId){
		MedOrderDto medorder = medOrderService.getMedOrder(medOrderId);
		return new ResponseEntity<MedOrderDto>(medorder,HttpStatus.OK);
	}
	
	
	@PutMapping("/medorder/{id}")
	public ResponseEntity<MedOrderDto> updateMedicalOrder(@Valid @RequestBody MedOrderDto medorderdto,@PathVariable("id") int encounterId){
		MedOrderDto medorder = medOrderService.updateMedOrder(medorderdto, encounterId);
		return new ResponseEntity<MedOrderDto>(medorder,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/medorder/{id}")
	public ResponseEntity<Map<String,String>> deleteBranch(@PathVariable("id") int medOrderId){
		medOrderService.deleteMedOrder(medOrderId);
		Map<String,String> resp = new HashMap<>();
		resp.put("message","branch with id "+medOrderId+" deletion successful");
		resp.put("status", "success");
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.OK);
	}
	
	@GetMapping("/medorders")
	public ResponseEntity<List<MedOrderDto>> getAllAddressesWithBranch(
			@RequestParam(value="pageno",defaultValue =AppConstants.PAGE_NO,required=false)int pageno,
			@RequestParam(value="pagesize",defaultValue =AppConstants.PAGE_SIZE,required=false)int pagesize,
			@RequestParam(value="sortby",defaultValue =AppConstants.SORTBY,required=false)String sortBy,
			@RequestParam(value="sortdir",defaultValue =AppConstants.SORTDIR,required=false)String sortDir
			){
		List<MedOrderDto> medOrders = medOrderService.getAllMedOrders(pageno, pagesize, sortBy, sortDir);
		return new ResponseEntity<List<MedOrderDto>>(medOrders,HttpStatus.OK);
	}
	
}
