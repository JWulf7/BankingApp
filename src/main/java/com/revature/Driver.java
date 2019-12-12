package com.revature;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.revature.models.Bank;
import com.revature.models.BankAccount;
import com.revature.models.Customer;

public class Driver {

	public static void main(String[] args) {

		// probably instantiate a bank first?
				// instantiate a Scanner
				// also ask if a returning user or a new user?
					// if new user, ask for userName and password, first and last name to setup a 'User' account for review.
					// this User needs to be approved by Admin OR Employee to acquire approval (boolean) to open a BankAccount
				// if returning, ask for their userName and password
				// check if type of employee or customer
					// if employee give them employee access
					// if customer, give them access to view their account, transactions, and deposit, withdraw, and transfer
				//Scanner scan = new Scanner(System.in);   				commented out
				
				Bank bank = new Bank();
				//test customer.. take out later
				testCustomer();
				
				bank.startHere();
				
				//scan.close();											commented out

	}

	public static void testCustomer() {
		Customer fred = new Customer();
				BankAccount act1 = new BankAccount();
				act1.setAccountNumber(12345678);
				act1.setAccountBalance(805.12);
				BankAccount act2 = new BankAccount();
				act2.setAccountNumber(12345678);
				act2.setAccountBalance(805.12);
				fred.setAddress("123 Sesame Street");
				fred.setApproved(true);
				fred.setFirstName("Fred");
				fred.setLastName("Clause");
				fred.setPassword("Xmas123");
				Set<BankAccount> useraccounts = new HashSet<>();
				useraccounts.add(act1);
				useraccounts.add(act2);
				fred.setUserAccounts(useraccounts);
				fred.setUserName("Fred123");
	}
	
}
