package com.revature.models;

public class Employee extends User{

	//should be able to view all customer info
		// including bank account info
		// account balances
		// personal info
		// should be able to approve/deny open applications for accounts
	
	// constructors
	Employee(){}
	
	Employee(String username, String password) {
		this.setUserName(username);
		this.setPassword(password);
	}
	
	
}
