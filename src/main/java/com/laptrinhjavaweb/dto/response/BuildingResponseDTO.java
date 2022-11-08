package com.laptrinhjavaweb.dto.response;

import java.util.List;

import com.laptrinhjavaweb.dto.AbstractDTO;

public class BuildingResponseDTO extends AbstractDTO<BuildingResponseDTO> {

	private String name;
	private String rentArea;
    private Integer floorArea;
  	private Integer rentPrice;
	private String serviceFee;
	private String managerName;
	private String managerPhone;
	private String brokerageFee;
	private String address;
	private List<String> type;
	
	
	
	public List<String> getType() {
		return type;
	}
	public void setType(List<String> type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRentArea() {
		return rentArea;
	}
	public void setRentArea(String rentArea) {
		this.rentArea = rentArea;
	}
	public Integer getFloorArea() {
		return floorArea;
	}
	public void setFloorArea(Integer floorArea) {
		this.floorArea = floorArea;
	}
	public Integer getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(Integer rentPrice) {
		this.rentPrice = rentPrice;
	}
	public String getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}
	public String getBrokerageFee() {
		return brokerageFee;
	}
	public void setBrokerageFee(String brokerageFee) {
		this.brokerageFee = brokerageFee;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
