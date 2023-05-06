package com.springboot.hospitalmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.hospitalmanagement.entitites.MedOrder;

public interface MedOrderRepository extends JpaRepository<MedOrder, Integer> {

}
