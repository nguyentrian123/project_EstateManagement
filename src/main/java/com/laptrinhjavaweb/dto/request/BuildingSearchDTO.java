package com.laptrinhjavaweb.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildingSearchDTO extends AbstractSearchDTO{
	
	public String name ; 
	private String street;
	private String ward ; 
	private String district;
	private Integer numberOfBasement;
	private String direction;
	private String level ;
	private Integer rentPriceTo;
	private Integer rentPriceFrom;
	private String managerName;
	private String managerPhone;
	private Long staff;
	private Integer floorArea;
	private List<String> renttype;
	private Integer rentAreaTo;
	private Integer rentAreaFrom;
	


}
