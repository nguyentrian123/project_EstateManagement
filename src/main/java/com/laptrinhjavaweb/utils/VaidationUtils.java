package com.laptrinhjavaweb.utils;

import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.AbstractSearchDTO;
import com.laptrinhjavaweb.dto.request.BuildingSearchDTO;
import com.laptrinhjavaweb.exception.FieldNotFoundException;

public class VaidationUtils {
	
	public static void Validate(BuildingDTO buildingDTO) throws FieldNotFoundException
	{
	
		if( buildingDTO.getName() == null || buildingDTO.getName() == "")
		{
			throw new FieldNotFoundException("Name is required");
		}
		if( buildingDTO.getRentArea() == null || buildingDTO.getRentArea() == "")
		{
			throw new FieldNotFoundException("RentArea is required");
		}
		if( buildingDTO.getStreet() == null || buildingDTO.getStreet() == "")
		{
			throw new FieldNotFoundException("Street is required");
		}
		if( buildingDTO.getDistrictCode() == null || buildingDTO.getDistrictCode() == "")
		{
			throw new FieldNotFoundException("District is required");
		}
		if( buildingDTO.getWard() == null || buildingDTO.getWard() == "")
		{
			throw new FieldNotFoundException("Ward is required");
		}
	}

	public static void validatePage(AbstractSearchDTO searchDTO ,ModelAndView mav ){
		if(searchDTO.getPage() < 1 ){
			mav.setViewName("404page");
		}
	}

}
