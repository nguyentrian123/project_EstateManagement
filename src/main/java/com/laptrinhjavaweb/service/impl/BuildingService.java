package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.laptrinhjavaweb.dto.response.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingAssignmentRequest;
import com.laptrinhjavaweb.dto.request.BuildingSearchDTO;
import com.laptrinhjavaweb.dto.response.BuildingResponseDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.security.utils.SecurityUtils;
import com.laptrinhjavaweb.service.IBuildingService;
import org.springframework.util.CollectionUtils;


@Service
public class BuildingService implements IBuildingService{

	@Autowired
	private BuildingConverter buildingConverter;
	
	@Autowired
	private BuildingRepository buildingRepository;

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public Map<String, String> getDistricts() {
		Map<String, String> districts = new HashMap<>();
		for (DistrictsEnum item: DistrictsEnum.values()) {
			districts.put(item.toString(), item.getDistrictValue());
		}
		return districts;
	}

	@Override
	public Map<String, String> getBuildingTypes() {
		Map<String, String> buildingTypes = new HashMap<>();
		for (BuildingTypesEnum item: BuildingTypesEnum.values()) {
			buildingTypes.put(item.toString(), item.getBuildingTypeValue());
		}
		return buildingTypes;
	}

	
	@Override
	@Transactional
	public BuildingDTO saveBuilding(BuildingDTO buildingDTO) {	
		BuildingEntity buildingEntity = new BuildingEntity();		
		
		if(buildingDTO.getId() != null)
		{
			BuildingEntity oldBuilding = buildingRepository.findById(buildingDTO.getId()).get();
			buildingEntity = buildingConverter.convertToEntity(buildingDTO);
			buildingEntity.setUsers(oldBuilding.getUsers()); // gửi lại các nv đang quản lý tòa nhà đó
		}
		else
		{
			buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		}
		return buildingConverter.convertToDTO(buildingRepository.save(buildingEntity));
	}
	
	
	
	@Override
	@Transactional
	public void assignmentBuilding(BuildingAssignmentRequest buildingAssignmentRequest) {
		List<UserEntity> userEntities = userRepository.findByIdIn(buildingAssignmentRequest.getStaffs());
		BuildingEntity buildingEntity = buildingRepository.findById(buildingAssignmentRequest.getBuildingId()).get();		
		buildingEntity.setUsers(userEntities);	
		buildingRepository.save(buildingEntity);	
	}
	
	
	@Override
	public ResponseDTO findBuilding(BuildingSearchDTO searchDTO, Pageable pageable) {
		ResponseDTO responseDTO = new ResponseDTO();
		List<BuildingResponseDTO> buildingResponseDTOs = new ArrayList<>();
		if(SecurityUtils.getAuthorities().contains(SystemConstant.STAFF_ROLE)) { // check , nếu staff thì chỉ đc xem tòa nhà mà nó ql
			searchDTO.setStaff(SecurityUtils.getPrincipal().getId());
		}
		BuildingSearchBuilder builder = buildingConverter.convertBuildingSearchBuilder(searchDTO);
		List<BuildingEntity> entities = buildingRepository.searchBuilding(builder,pageable.getPageNumber(),pageable.getPageSize())
																		.getContent();
		buildingResponseDTOs = convertToListBuildingDTO(entities);
		
		// getTotalPages
		int totalPages = 0 ; 
		int totalItems = getTotalItem(searchDTO, pageable.getPageNumber(),pageable.getPageSize());
		totalPages = totalItems / pageable.getPageSize();
		if(totalItems % pageable.getPageSize() != 0) {
			totalPages ++;
		}
		responseDTO.setData(buildingResponseDTOs);
		responseDTO.setTotalPages(totalPages);
		responseDTO.setPage(pageable.getPageNumber());
		return responseDTO;
	}

	
	private List<BuildingResponseDTO> convertToListBuildingDTO(List<BuildingEntity> entities) {
		List<BuildingResponseDTO> buildingResponseDTOs = new ArrayList<>();
		Map<String,String> districtMap = getDistricts();
		for(BuildingEntity  item : entities)
		{
			BuildingResponseDTO buildingResponseDTO = buildingConverter.convertToResponseDTO(item);
			buildingResponseDTO.setAddress(districtMap.entrySet().stream().filter(entry -> item.getDistrictCode().equals(entry.getKey()))
					.map(entry -> item.getStreet() +","+ item.getWard() +","+  entry.getValue())
					.collect(Collectors.joining()));
			buildingResponseDTOs.add(buildingResponseDTO);
		}
		return  buildingResponseDTOs;
	}


	@Override
	public int getTotalItem(BuildingSearchDTO searchDTO, int page , int limit) {
		BuildingSearchBuilder builder = buildingConverter.convertBuildingSearchBuilder(searchDTO);
		page = SystemConstant.zero;
		limit= SystemConstant.zero;
		int size = (int) buildingRepository.searchBuilding(builder,page, limit).getTotalElements();
		return size;
	}

	@Override
	public BuildingDTO findById(Long id) {
		BuildingEntity buildingEntity = buildingRepository.findById(id).orElse(null);
		BuildingDTO buildingDTO = buildingConverter.convertToDTO(buildingEntity);
		return buildingDTO;
	}
	

	@Override
	@Transactional
	public void deleteBuilding(Long[] ids) {			
		buildingRepository.deleteByIdIn(ids);
	}

	

	
}
