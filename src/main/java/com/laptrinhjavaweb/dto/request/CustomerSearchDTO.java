package com.laptrinhjavaweb.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSearchDTO extends AbstractSearchDTO{
	private String fullName ; 
	private String phone ; 
	private String email ; 
	private Long staff ;
	

	
}
