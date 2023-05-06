package com.springboot.hospitalmanagement.service;

import java.util.List;

import com.springboot.hospitalmanagement.dto.MedOrderDto;

public interface MedOrderService {
	
public MedOrderDto saveMedOrder(MedOrderDto medOrderDto , int encounterId);
	
	public MedOrderDto getMedOrder(int medOrderId);
	
	public MedOrderDto updateMedOrder(MedOrderDto medOrderDto , int medOrderId);
	
	public void deleteMedOrder(int medOrderId);
	
	public List<MedOrderDto> getAllMedOrders(int pageno , int pagesize ,String sortBy , String sortDir);

}
