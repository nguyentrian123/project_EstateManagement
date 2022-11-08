package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.converter.CustomerConverter;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.request.CustomerAssignmentRequest;
import com.laptrinhjavaweb.dto.request.CustomerSearchDTO;
import com.laptrinhjavaweb.dto.response.ResponseDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.enums.TransactionTypeEnum;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.security.utils.SecurityUtils;
import com.laptrinhjavaweb.service.ICustomerService;

@Service
public class CustomerService implements ICustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CustomerConverter customerConverter;


	@Override
	public Map<String, String> getTransactionType() {
		Map<String,String> transactionTypes = new HashMap<String, String>();	
		for(TransactionTypeEnum item : TransactionTypeEnum.values()) {
			transactionTypes.put(item.toString(), item.getTransactionTypeValue());
		}	
		return transactionTypes;
	}

	@Override
	public ResponseDTO searchCustomer(CustomerSearchDTO searchDTO,Pageable pageable ) {
		ResponseDTO responseDTO = new ResponseDTO();
		List<CustomerDTO> rerults = new ArrayList<>();
		
		if(!SecurityUtils.getAuthorities().contains(SystemConstant.MANAGER_ROLE)) {
			searchDTO.setStaff(SecurityUtils.getPrincipal().getId());
		}
		CustomerSearchBuilder customerSearchBuilder = customerConverter.convertToBuilder(searchDTO);	
		List<CustomerEntity> entities = customerRepository.searchCustomer(customerSearchBuilder,pageable.getPageNumber(),pageable.getPageSize())
																			.getContent();
		
		for(CustomerEntity item : entities) {	
			CustomerDTO customer = customerConverter.converToDTO(item);
			List<UserEntity> userEntities = userRepository.findByStatusAndRoles_CodeAndCustomers_Id(1, SystemConstant.STAFF, item.getId());
			List<String> nameStaffs = new ArrayList<>();
			
			for(UserEntity entity : userEntities) {
				nameStaffs.add(entity.getFullName());
			}
			customer.setFullNameStaff(String.join(",", nameStaffs));
			rerults.add(customer);
		}
		
		// getTotalPages
		int totalPages = 0 ; 
		int totalItems = getTotalItem(searchDTO, pageable.getPageNumber(),pageable.getPageSize());
		totalPages = totalItems / pageable.getPageSize();
		if(totalItems % pageable.getPageSize() != 0) {
			totalPages ++;
		}
		responseDTO.setData(rerults);
		responseDTO.setTotalPages(totalPages);
		responseDTO.setPage(pageable.getPageNumber());
		
		return responseDTO;
	}
	
	@Override
	public int getTotalItem(CustomerSearchDTO searchDTO, int page, int limit) {
		CustomerSearchBuilder customerSearchBuilder = customerConverter.convertToBuilder(searchDTO);
		page = SystemConstant.zero;
		limit= SystemConstant.zero;
		int size = (int) customerRepository.searchCustomer(customerSearchBuilder,page, limit).getTotalElements();
		return size;
	}
	

	@Override
	public CustomerDTO findById(Long id) {
		CustomerEntity customerEntity = customerRepository.findById(id).get();
		CustomerDTO customerDTO = customerConverter.converToDTO(customerEntity);

		return customerDTO;
	}
	
	@Override
	@Transactional
	public CustomerDTO save(CustomerDTO customerDTO) {
		CustomerEntity customerEntity = new CustomerEntity();
		
		if(customerDTO.getId() != null) {
			CustomerEntity oldCustomer = customerRepository.findById(customerDTO.getId()).get();
			customerEntity = customerConverter.convertToEntity(customerDTO);
			customerEntity.setUsers(oldCustomer.getUsers());
			customerEntity.setTransactions(oldCustomer.getTransactions());
		}
		else {
			customerEntity = customerConverter.convertToEntity(customerDTO);
		}
		return customerConverter.converToDTO(customerRepository.save(customerEntity));

	}
	

	@Override
	@Transactional
	public void assignmentCustomer(CustomerAssignmentRequest customerAssignmentRequest) {
		List<UserEntity> userEntities = userRepository.findByIdIn(customerAssignmentRequest.getStaffs());
		CustomerEntity customerEntity = customerRepository.findById(customerAssignmentRequest.getCustomerId()).get();
		customerEntity.setUsers(userEntities);
		customerRepository.save(customerEntity);
	}

	@Override
	public void deleteCustomer(Long[] ids) {
		customerRepository.deleteByIdIn(ids);	
	}


	

	

	
	
	

	
}
