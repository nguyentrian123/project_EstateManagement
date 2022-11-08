package com.laptrinhjavaweb.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.exception.FieldNotFoundException;
import com.laptrinhjavaweb.repository.RentAreaRepository;

@Component
public class RentAreaConverter {

	public List<RentAreaEntity> convertToEntity(List<String> values , BuildingEntity buildingEntity){		
		List<RentAreaEntity> rentAreaEntities = new ArrayList<>();
		for(String item : values){
			RentAreaEntity rentAreaEntity = new RentAreaEntity();
			rentAreaEntity.setBuilding(buildingEntity);
			rentAreaEntity.setValue(Integer.parseInt(item));
			rentAreaEntities.add(rentAreaEntity);
		}
		return rentAreaEntities;
	}
	


	
}
