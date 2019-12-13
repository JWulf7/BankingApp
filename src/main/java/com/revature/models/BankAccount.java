package com.revature.models;

public class BankAccount {

	// perhaps an array, so that 0000009 is not read as 9?? or could start first account at like 1000000
		// private int[] accountNumber;
	
	// maybe also a list of transactions?
	
	
		private int accountNumber;
		private final int  MIN = 200;
		private double accountBalance;
		
	// constructors
		public BankAccount(int number, double bal) {
			this.accountNumber = number;
			this.accountBalance = bal;
		}
		
		public BankAccount() {}
		
		public BankAccount(int number) {
			this.accountNumber = number;
		}
		
		
		
		
		
		
		
		
		// getters and setters
		public int getAccountNumber() {
			return accountNumber;
		}
		public void setAccountNumber(int accountNumber) {
			this.accountNumber = accountNumber;
		}
		public double getAccountBalance() {
			return accountBalance;
		}
		public void setAccountBalance(double d) {
			this.accountBalance = d;
		}
		public int getMIN() {
			return MIN;
		}
	
		
		// transaction methods
		
	public void deposit(double amount) {
		if (amount <= 0) {
			System.out.println("you have to deposit a positive amount.");
		} else {
			this.setAccountBalance((this.getAccountBalance() + amount));
			// probably need to add transaction to a list here too...
			// also at this point, need to update database with new balance
		}
	}
	
	
	public void withdraw(double amount) {
		if ((this.getAccountBalance() - this.getMIN()) >= amount) {
			this.setAccountBalance((this.getAccountBalance() - amount));
			// add transaction to a list here?
			// also maybe a while loop that only allows sufficient funds?
			// update database here to new balance
		} else {
			System.out.println("You do not have sufficient funds...");
		}
	}
		
	// transfer									//vv~~ not sure if this should be a bankaccount obj or just a number, probably an object, and in the actual implementation of the menu, i'll have to search for the bank account using its number in the database
	public void transfer(double amount, BankAccount otherAcct) {
				if((this.getAccountBalance() - this.getMIN()) >= amount) {
					this.withdraw(amount);
					otherAcct.deposit(amount);
					// update both accounts on the database here
					
					// need to finish this method with implementation
					// here i should withdraw from current account and deposit into another account
					// do i need to instantiate another customer what matches the otherAcctNum?.. then deposit into that Customers acct...
					// then maybe transaction to lists of both customers... ?
				}
			
		
	}	
	
	
	
		
}
