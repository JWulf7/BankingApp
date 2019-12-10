package com.revature;

import java.util.Scanner;

import com.revature.models.Bank;

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
				Scanner scan = new Scanner(System.in);
				
				Bank bank = new Bank();
				bank.startHere();
				
				scan.close();

	}

}
