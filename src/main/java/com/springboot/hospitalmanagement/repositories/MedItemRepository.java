package com.springboot.hospitalmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.hospitalmanagement.entitites.MedItem;

public interface MedItemRepository extends JpaRepository<MedItem, Integer>{

}
