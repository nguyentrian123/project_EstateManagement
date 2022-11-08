package com.laptrinhjavaweb.repository.custom;

import org.springframework.data.domain.Page;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;

public interface BuildingRepositoryCustom {
	Page<BuildingEntity> searchBuilding(BuildingSearchBuilder builder, int page , int limit);
	
}
