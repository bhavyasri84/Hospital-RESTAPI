package com.springboot.hospitalmanagement.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.hospitalmanagement.dto.MedItemDto;
import com.springboot.hospitalmanagement.serviceImpl.MedItemService;

@RestController
@RequestMapping("/api/")
public class MedItemController {
	
	@Autowired
	private MedItemService medItemService;
	
	
	@PostMapping("/medorder/{id}/meditem")
	public ResponseEntity<MedItemDto> createMedItem(@RequestBody MedItemDto medItemDto ,@PathVariable("id") int medOrderId){
		MedItemDto medItem = medItemService.createMedItem(medItemDto, medOrderId);
		return new ResponseEntity<MedItemDto>(medItem,HttpStatus.OK);
	}
	
	
	
	
	@DeleteMapping("/meditem/{id}")
	public ResponseEntity<Map<String,String>> deleteMedItem(@PathVariable("id") int medItemId){
		medItemService.deleteMedItem(medItemId);
		Map<String,String> resp = new HashMap<>();
		resp.put("message","branch with id "+medItemId+" deletion successful");
		resp.put("status", "success");
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.OK);
	}

}
