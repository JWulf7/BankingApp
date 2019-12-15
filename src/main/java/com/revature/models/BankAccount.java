package com.revature.models;

import java.util.Queue;

public class BankAccount {

	
	
	
		private int accountNumber;
		private final int  MIN = 200;
		private double accountBalance;
		private String userName;
		private Queue<String> recentTransactions;
		
	// constructors
		public BankAccount(int number, double bal) {
			this.accountNumber = number;
			this.accountBalance = bal;
		}
		
		public BankAccount() {}
		
		public BankAccount(int number) {
			this.accountNumber = number;
		}
		
		public BankAccount(int num, double bal, String owner) {
			this.setAccountNumber(num);
			this.setAccountBalance(bal);
			this.setUserName(owner);
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
		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}
		
		
		
	

	public Queue<String> getRecentTransactions() {
			return recentTransactions;
		}

		public void setRecentTransactions(Queue<String> recentTransactions) {
			this.recentTransactions = recentTransactions;
		}
		
		// transaction methods
	public void deposit(double amount) {
		if (amount <= 0) {
			System.out.println("you have to deposit a positive amount.");
		} else {
			this.setAccountBalance((this.getAccountBalance() + amount));
			// probably need to add transaction to a list here too...
		}
	}
	
	
	public void withdraw(double amount) {
		if ((this.getAccountBalance() - this.getMIN()) >= amount) {
			this.setAccountBalance((this.getAccountBalance() - amount));
			// add transaction to a list here?
		} else {
			System.out.println("You do not have sufficient funds...");
		}
	}
		
	public void transfer(double amount, BankAccount otherAcct) {
				if((this.getAccountBalance() - this.getMIN()) >= amount) {
					otherAcct.deposit(amount);
					this.withdraw(amount);
					
					// add transaction to a list here?
				} else {
					System.out.println("Transfer did not go through.\n" +
							"Minimum balance required is $200");
				}
			
		
	}	
	
	
	
		
}
