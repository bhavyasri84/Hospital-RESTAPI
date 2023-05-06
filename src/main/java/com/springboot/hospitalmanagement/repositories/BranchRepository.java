package com.springboot.hospitalmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.hospitalmanagement.entitites.Branch;

public interface BranchRepository extends JpaRepository<Branch, Integer>{

}
