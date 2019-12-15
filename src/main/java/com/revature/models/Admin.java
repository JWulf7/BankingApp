package com.revature.models;

public class Admin extends Employee{

	//should be able to view and manipulate all User fields
	// should be able to approve users to open accounts
	// cancel accounts
	// perform all transactions without restrictions
	
	public boolean isAdmin = true;

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public Admin(String username, String password, String fname, String lname) {
		this.setUserName(username);
		this.setPassword(password);
		this.setFirstName(fname);
		this.setLastName(lname);
	}
}
