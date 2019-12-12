package com.revature.models;

public class BankAccount {

	// perhaps an array, so that 0000009 is not read as 9?? or could start first account at like 1000000
		// private int[] accountNumber;
	
	// maybe also a list of transactions?
	
	
		private int accountNumber;
		private final int  MIN = 200;
		private double accountBalance;
		
		
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
	
		
		
		
}
