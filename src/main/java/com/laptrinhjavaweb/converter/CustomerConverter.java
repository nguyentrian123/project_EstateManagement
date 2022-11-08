package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.request.CustomerSearchDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;

@Component
public class CustomerConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public CustomerDTO converToDTO(CustomerEntity customerEntity)
	{
		CustomerDTO customerDTO = modelMapper.map(customerEntity, CustomerDTO.class);
		return customerDTO;
	}
	
	public CustomerEntity convertToEntity( CustomerDTO customerDTO) {
		CustomerEntity result = modelMapper.map(customerDTO, CustomerEntity.class);
		return result;
	}
	
	
	public CustomerSearchBuilder convertToBuilder(CustomerSearchDTO customerSearchDTO) {
		CustomerSearchBuilder customerSearchBuilder = new CustomerSearchBuilder.Builder()
															.setFullName(customerSearchDTO.getFullName())
															.setEmail(customerSearchDTO.getEmail())
															.setPhone(customerSearchDTO.getPhone())
															.setStaff(customerSearchDTO.getStaff())
															.build();
		return customerSearchBuilder;
	}
}
