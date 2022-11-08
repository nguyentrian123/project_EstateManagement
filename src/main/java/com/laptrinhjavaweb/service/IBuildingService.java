package com.laptrinhjavaweb.service;

import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingAssignmentRequest;
import com.laptrinhjavaweb.dto.request.BuildingSearchDTO;
import com.laptrinhjavaweb.dto.response.ResponseDTO;

public interface IBuildingService {

	Map<String, String> getDistricts();
	Map<String, String> getBuildingTypes();
	BuildingDTO saveBuilding(BuildingDTO buildingDTO);
	ResponseDTO findBuilding(BuildingSearchDTO searchDTO, Pageable pageable);
	int getTotalItem(BuildingSearchDTO searchDTO,int page,  int limit);
	BuildingDTO findById(Long id);
	void assignmentBuilding(BuildingAssignmentRequest bRequest);
	void deleteBuilding(Long[] ids);
	
}
