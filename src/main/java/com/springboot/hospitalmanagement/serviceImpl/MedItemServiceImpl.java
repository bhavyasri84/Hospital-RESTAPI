package com.springboot.hospitalmanagement.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.hospitalmanagement.dto.MedItemDto;
import com.springboot.hospitalmanagement.entitites.MedItem;
import com.springboot.hospitalmanagement.entitites.MedOrder;
import com.springboot.hospitalmanagement.exceptions.ResourceNotFoundException;
import com.springboot.hospitalmanagement.repositories.MedItemRepository;
import com.springboot.hospitalmanagement.repositories.MedOrderRepository;

@Service
public class MedItemServiceImpl implements MedItemService{
	
	@Autowired
	private MedOrderRepository medOrderRepo;
	
	
	@Autowired
	private MedItemRepository medItemrepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public MedItemDto createMedItem(MedItemDto medItemDto, int medOrderId) {
		MedOrder medOrder = medOrderRepo.findById(medOrderId).orElseThrow(()-> new ResourceNotFoundException("MedOrder"," id ", medOrderId));
		MedItem medItem = modelMapper.map(medItemDto,MedItem.class);
		medItem.setOrder(medOrder);
		MedItem medItemcreated = medItemrepo.save(medItem);
		return modelMapper.map(medItemcreated, MedItemDto.class);
	}

	@Override
	public void deleteMedItem(int medItemId) {
		@SuppressWarnings("unused")
		MedItem medItem = medItemrepo.findById(medItemId).orElseThrow(()-> new ResourceNotFoundException("MedOrder"," id ", medItemId));
		medItemrepo.deleteById(medItemId);
		
		
	}

}
