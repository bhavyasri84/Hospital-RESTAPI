package com.springboot.hospitalmanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.hospitalmanagement.entitites.Address;
import com.springboot.hospitalmanagement.entitites.Branch;

public interface AddressRepository extends JpaRepository<Address, Integer>{
	public List<Address> findByBranch(Branch branch);

}
