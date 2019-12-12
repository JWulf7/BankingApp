package com.revature.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.revature.models.BankAccount;

public class Customer extends User{

	// To DO:
	// write methods for doing transactions(deposit, withdrawl, transfer)
	// also write queue with capacity?? for latest (5?) transactions??
	// maybe full list of transactions also?
	
	//customer fields
	private boolean approved = false;
	
	private String address;
	
	private Map<Integer, BankAccount> userAccounts = new TreeMap<>();
	
	// constructors
	public Customer() {}
	
	public Customer(String username, String pass) {
		this();
		this.userName = username;
		this.password = pass;
	}
	
	// transaction methods
	// deposit
	//public?? because customer objects will not be instantiated here, so method will be accessible in the bank?
//	public void deposit(int amount, int acctnum) {
//		if(amount <= 0) {
//			System.out.println("you have to deposit a positive amount.");
//		} else {
//			for(BankAccount acct : userAccounts) {
//				if(acct.getAccountNumber() == acctnum) {
//					acct.setAccountBalance((acct.getAccountBalance() + amount));
//					// probably need to add transaction to a list here too...
//				}
//			}
//		}
//	}
	// withdraw
//	public void withdraw(double amount, int acctnum) {
//		for(BankAccount acct : userAccounts) {
//			if(acct.getAccountNumber() == acctnum) {
//				if((acct.getAccountBalance() - acct.getMIN()) >= amount) {
//					acct.setAccountBalance((acct.getAccountBalance() - amount));
//					// add transaction to a list here?
//					// also maybe a while loop that only allows sufficient funds?
//				} else {
//					System.out.println("You do not have sufficient funds...");
//				}
//			}
//		}
//	}
	// transfer
//	public void transfer(int amount, int thisAcctNum, int otherAcctNum ) {
//		for(BankAccount acct : userAccounts) {
//			if(acct.getAccountNumber() == thisAcctNum) {
//				if((acct.getAccountBalance() - acct.getMIN()) >= amount) {
//					// need to finish this method with implementation
//					// here i should withdraw from current account and deposit into another account
//					// do i need to instantiate another customer what matches the otherAcctNum?.. then deposit into that Customers acct...
//					// then maybe transaction to lists of both customers... ?
//				}
//			}
//		}
//	}
	
	
	
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

	public Set<BankAccount> getUserAccounts() {
		return userAccounts;
	}

	public void setUserAccounts(Set<BankAccount> userAccounts) {
		this.userAccounts = userAccounts;
	}
	
	

	
	
}
