package com.springboot.hospitalmanagement.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.hospitalmanagement.dto.MedOrderDto;
import com.springboot.hospitalmanagement.entitites.Encounter;
import com.springboot.hospitalmanagement.entitites.MedOrder;
import com.springboot.hospitalmanagement.exceptions.ResourceNotFoundException;
import com.springboot.hospitalmanagement.repositories.EncounterRepository;
import com.springboot.hospitalmanagement.repositories.MedOrderRepository;
import com.springboot.hospitalmanagement.service.MedOrderService;

@Service
public class MedOrderServiceImpl implements MedOrderService{
	
	@Autowired
	private MedOrderRepository medOrderrepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private EncounterRepository encounterrepo;

	@Override
	public MedOrderDto saveMedOrder(MedOrderDto medOrderDto , int encounterId) {
		Encounter encounter = encounterrepo.findById(encounterId).orElseThrow(()->new ResourceNotFoundException("Encounter"," id ", encounterId));
		MedOrder medorder = modelMapper.map(medOrderDto,MedOrder.class);
		medorder.setOrderDate(LocalDate.now());
		medorder.setDeliveryDate(LocalDate.now().plusDays(3));
		medorder.setEncounter(encounter);
		MedOrder savedmedOrder = medOrderrepo.save(medorder);
		return modelMapper.map(savedmedOrder,MedOrderDto.class);
	}

	@Override
	public MedOrderDto getMedOrder(int medOrderId) {
		MedOrder medorder = medOrderrepo.findById(medOrderId).orElseThrow(()->new ResourceNotFoundException("MedOrder"," id ", medOrderId));
		return modelMapper.map(medorder,MedOrderDto.class);
	}

	@Override
	public MedOrderDto updateMedOrder(MedOrderDto medOrderDto, int medOrderId) {
		MedOrder medorder = medOrderrepo.findById(medOrderId).orElseThrow(()-> new ResourceNotFoundException("MedOrder"," id ", medOrderId));
		medorder.setMessage(medOrderDto.getMessage());
		medorder.setOrderDate(LocalDate.now());
		medorder.setDeliveryDate(LocalDate.now().plusDays(3));
		MedOrder updatedMedOrder = medOrderrepo.save(medorder);
		return modelMapper.map(updatedMedOrder, MedOrderDto.class);
	}

	@Override
	public void deleteMedOrder(int medOrderId) {
		@SuppressWarnings("unused")
		MedOrder medorder = medOrderrepo.findById(medOrderId).orElseThrow(()-> new ResourceNotFoundException("MedOrder"," id ", medOrderId));
		medOrderrepo.deleteById(medOrderId);
	}

	@Override
	public List<MedOrderDto> getAllMedOrders(int pageno, int pagesize, String sortBy, String sortDir) {
		Sort sort = "asc".equalsIgnoreCase(sortDir)? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
		Pageable p = PageRequest.of(pageno, pagesize,sort);
		Page<MedOrder> medOrders = medOrderrepo.findAll(p);
		List<MedOrder> medOrderList = medOrders.getContent();
		return medOrderList.stream().map(medorder->modelMapper.map(medorder,MedOrderDto.class)).collect(Collectors.toList());
	}

	
	
}
