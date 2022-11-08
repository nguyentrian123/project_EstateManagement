package com.laptrinhjavaweb.repository.custom;

import org.springframework.data.domain.Page;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.entity.CustomerEntity;

public interface CustomerRepositoryCustom {
	Page<CustomerEntity> searchCustomer(CustomerSearchBuilder customerSearchBuilder,int page , int limit);
}
