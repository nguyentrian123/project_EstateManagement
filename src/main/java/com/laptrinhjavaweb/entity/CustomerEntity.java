package com.laptrinhjavaweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;


@Entity
@Table(name="customer")
public class CustomerEntity extends BaseEntity {


	private static final long serialVersionUID = 1L;

		@Column(name = "fullname", nullable = false)
	    private String fullName;

	    @Column(name = "phone", nullable = false)
	    private String phone;

	    @Column(name = "email", unique = true)
	    private String email;
	    
	    @Column(name = "companyname")
	    private String companyName;
	    
	    @Column(name = "need")
	    private String need;
	    
	    @Column(name = "note")
	    private String note;
	    
	    @OneToMany(mappedBy="customer",  cascade ={CascadeType.PERSIST, CascadeType.MERGE} , orphanRemoval  = true )
	    private List<TransactionEntity> transactions = new ArrayList<>();
	    
	    @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable( name="assignmentcustomer",
	    			joinColumns = @JoinColumn(name = "customerid", nullable = false),
	    			inverseJoinColumns = @JoinColumn(name = "staffid" , nullable = false)
	    		)
	    private List<UserEntity> users = new ArrayList<>();

	    
	    
		public List<UserEntity> getUsers() {
			return users;
		}

		public void setUsers(List<UserEntity> users) {
			this.users = users;
		}

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

		public List<TransactionEntity> getTransactions() {
			return transactions;
		}

		public void setTransactions(List<TransactionEntity> transactions) {
			this.transactions = transactions;
		}

	
	    
}
