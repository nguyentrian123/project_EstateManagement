package com.laptrinhjavaweb.converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.BuildingSearchDTO;
import com.laptrinhjavaweb.dto.response.BuildingResponseDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.RentAreaRepository;

@Component
public class BuildingConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RentAreaConverter rentAreaConverter;
	
	@Autowired
	private RentAreaRepository rentAreaRepository;


	public BuildingDTO convertToDTO(BuildingEntity buildingEntity) {
		BuildingDTO dto = modelMapper.map(buildingEntity, BuildingDTO.class);
		if(buildingEntity.getType() != null )
		{
			dto.setType(Arrays.asList(buildingEntity.getType().split(","))); // đưa xuống thì ta cần 1 cái chuỗi, nhưng lấy lên thì cần tách ra 1 cái list
		}
		String rentArea = buildingEntity.getRentAreas().stream().map(entity -> String.valueOf(entity.getValue()))
																.collect(Collectors.joining(","));
		dto.setRentArea(rentArea); //  hiển thị rentare theo định dạng value,value,
		return dto;
	}

	public BuildingResponseDTO convertToResponseDTO(BuildingEntity buildingEntity) {
		BuildingResponseDTO dto = modelMapper.map(buildingEntity, BuildingResponseDTO.class);
		if(buildingEntity.getType() != null )
		{
			dto.setType(Arrays.asList(buildingEntity.getType().split(",")));
		}
		String rentArea = buildingEntity.getRentAreas().stream().map(entity -> String.valueOf(entity.getValue()))
																.collect(Collectors.joining(","));

		dto.setRentArea(rentArea); //  hiển thị rentare theo định dạng value,value,
		return dto;
	}

	
	// toEntityWithEditWhenSave
	public BuildingEntity convertToEntity(BuildingDTO newBuilding) {	
		BuildingEntity buildingEntity = modelMapper.map(newBuilding, BuildingEntity.class);
		List<String> values = Arrays.asList(newBuilding.getRentArea().split(","));
		buildingEntity.setRentAreas(rentAreaConverter.convertToEntity(values, buildingEntity));
		
		if(newBuilding.getType() != null) {
			String  type  = String.join(",", newBuilding.getType());
			buildingEntity.setType(type);
		}
		
		return buildingEntity;
	}
	
	
	
	public BuildingSearchBuilder convertBuildingSearchBuilder(BuildingSearchDTO searchDTO) {
		BuildingSearchBuilder builder = new BuildingSearchBuilder.Builder()
											.setName(searchDTO.getName())
											.setStreet(searchDTO.getStreet())
											.setWard(searchDTO.getWard())
											.setDistrict(searchDTO.getDistrict())
											.setFloorArea(searchDTO.getFloorArea())
											.setNumberOfBasement(searchDTO.getNumberOfBasement())
											.setDirection(searchDTO.getDirection())
											.setLevel(searchDTO.getLevel())
											.setManagerName(searchDTO.getManagerName())
											.setManagerPhone(searchDTO.getManagerPhone())
											.setRentPriceFrom(searchDTO.getRentPriceFrom())
											.setRentPriceTo(searchDTO.getRentPriceTo())
											.setRentAreaFrom(searchDTO.getRentAreaFrom())
											.setRentAreaTo(searchDTO.getRentAreaTo())
											.setStaff(searchDTO.getStaff())
											.setRenttype(searchDTO.getRenttype())
											.build();
		
		return builder;
	}
	


}
