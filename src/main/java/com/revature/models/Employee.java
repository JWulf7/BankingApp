package com.revature.models;

public class Employee extends User{

	//should be able to view all customer info
		// including bank account info
		// account balances
		// personal info
		// should be able to approve/deny open applications for accounts
	public boolean isAdmin = false;
	// To DO:
	// write methods for transactions (deposit, withdraw, transfer) - should take in 1 account or customer, or 2 for transfer
	
	// constructors
	Employee(){}
	
	Employee(String username, String password) {
		this.setUserName(username);
		this.setPassword(password);
	}
	
	
}
