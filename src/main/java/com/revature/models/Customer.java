package com.revature.models;

import java.util.HashSet;
import java.util.Set;

import com.revature.models.BankAccount;

public class Customer extends User{

	//customer fields
	private boolean approved = false;
	
	private String address;
	
	private Set<BankAccount> userAccounts = new HashSet<>();
	
	// constructors
	public Customer() {}
	
	public Customer(String username, String pass) {
		this();
		this.userName = username;
		this.password = pass;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<BankAccount> getUserAccounts() {
		return userAccounts;
	}

	public void setUserAccounts(Set<BankAccount> userAccounts) {
		this.userAccounts = userAccounts;
	}
	
	

	
	
}
