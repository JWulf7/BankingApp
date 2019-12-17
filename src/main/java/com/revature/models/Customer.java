package com.revature.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.revature.models.BankAccount;

public class Customer extends User{

	
	//customer fields
	private boolean approved = false;
	
	private String address;
	
	private Map<Integer, BankAccount> userAccounts = new TreeMap<>();
	
	// constructors
	public Customer() {}
	
	public Customer(String username) {
		this();
		this.setUserName(username);
	}
	public Customer(String username, String pass) {
		this();
		this.setUserName(username);
		this.setPassword(pass);
	}
	
	public Customer(String username, String last, String first) {
		this();
		this.setUserName(username);
		this.setLastName(last);
		this.setFirstName(first);
	}
	
	public Customer(String userName, String userPassword, String firstName, String lastName, String address, boolean approved) {
		this();
		this.setUserName(userName);
		this.setPassword(userPassword);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setAddress(address);
		this.setApproved(approved);
	}
	
	
	
	
	// getters and setters
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

	public Map<Integer, BankAccount> getUserAccounts() {
		return userAccounts;
	}

	public void setUserAccounts(Map<Integer, BankAccount> userAccounts) {
		this.userAccounts = userAccounts;
	}

	@Override
	public String toString() {
		return "Customer [approved=" + approved + ", address=" + address + ", userAccounts=" + userAccounts + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + (approved ? 1231 : 1237);
		result = prime * result + ((userAccounts == null) ? 0 : userAccounts.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (approved != other.approved)
			return false;
		if (userAccounts == null) {
			if (other.userAccounts != null)
				return false;
		} else if (!userAccounts.equals(other.userAccounts))
			return false;
		return true;
	}
	
	

	
	
}
