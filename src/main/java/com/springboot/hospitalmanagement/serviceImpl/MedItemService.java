package com.springboot.hospitalmanagement.serviceImpl;

import com.springboot.hospitalmanagement.dto.MedItemDto;

public interface MedItemService {
	
	public MedItemDto createMedItem(MedItemDto medItemDto , int medOrderId);
	
	public void deleteMedItem(int medItemId);

}
