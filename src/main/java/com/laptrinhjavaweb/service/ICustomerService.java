package com.laptrinhjavaweb.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.request.CustomerAssignmentRequest;
import com.laptrinhjavaweb.dto.request.CustomerSearchDTO;
import com.laptrinhjavaweb.dto.response.ResponseDTO;

public interface ICustomerService {

	Map<String, String> getTransactionType();
	ResponseDTO searchCustomer(CustomerSearchDTO customerSearchDTO,Pageable pageable );
	CustomerDTO findById(Long id);
	void assignmentCustomer(CustomerAssignmentRequest customerAssignmentRequest);
	CustomerDTO save(CustomerDTO customerDTO);
	void deleteCustomer(Long [] ids);
	int getTotalItem(CustomerSearchDTO searchDTO,int page,  int limit);
}
