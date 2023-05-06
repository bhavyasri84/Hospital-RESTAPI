package com.springboot.hospitalmanagement.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class BranchResponse {
	
	private List<BranchDto> branchDtos;
	private int pageNo;
	private int pageSize;
	private int totalPages;
	private long totalElements;
	private boolean isLast;
	
	
}
