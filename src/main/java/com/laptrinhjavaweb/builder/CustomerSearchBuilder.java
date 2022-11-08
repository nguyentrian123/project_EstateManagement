package com.laptrinhjavaweb.builder;

public class CustomerSearchBuilder {

	private String fullName ; 
	private String phone ; 
	private String email ; 
	private Long staff ;
	
	public String getFullName() {
		return fullName;
	}
	public String getPhone() {
		return phone;
	}
	public String getEmail() {
		return email;
	}
	public Long getStaff() {
		return staff;
	}

	
	
	
	public CustomerSearchBuilder(Builder builder) {
		this.fullName = builder.fullName;
		this.phone = builder.phone;
		this.email = builder.email;
		this.staff = builder.staff;

	}



	public static class Builder{
		private String fullName ; 
		private String phone ; 
		private String email ; 
		private Long staff ;

		
		public Builder setFullName(String fullName) {
			this.fullName = fullName;
			return this;
		}
		public Builder setPhone(String phone) {
			this.phone = phone;
			return this;
		}
		public Builder setEmail(String email) {
			this.email = email;
			return this;
		}
		public Builder setStaff(Long staff) {
			this.staff = staff;
			return this;
		}
		
		public CustomerSearchBuilder build() {
			return new CustomerSearchBuilder(this);
		}
	}
	
	
	
	
}
