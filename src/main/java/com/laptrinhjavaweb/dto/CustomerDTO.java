package com.laptrinhjavaweb.dto;

public class CustomerDTO  extends AbstractDTO<CustomerDTO>{

	private String fullName ; 
	private String phone ; 
	private String email ; 
	private Long staff ;
	private String fullNameStaff;
	private String companyName;
	private String need;
	private String note;
	
	
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getNeed() {
		return need;
	}
	public void setNeed(String need) {
		this.need = need;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getFullNameStaff() {
		return fullNameStaff;
	}
	public void setFullNameStaff(String fullNameStaff) {
		this.fullNameStaff = fullNameStaff;
	}
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getStaff() {
		return staff;
	}
	public void setStaff(Long staff) {
		this.staff = staff;
	} 
	
	
}
