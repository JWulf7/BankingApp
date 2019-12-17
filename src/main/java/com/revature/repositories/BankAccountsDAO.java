package com.revature.repositories;

import java.util.TreeMap;

import com.revature.models.BankAccount;

public interface BankAccountsDAO {
	
	public BankAccount getAccountFromNum(int accountNumber);
	
	public TreeMap<Integer, BankAccount> getAccountFromUser(String userName);
	
	public boolean updateAccount(int accountNumber, double accountBalance);
	
	public boolean createAccount(String userName);
	
	public BankAccount getNewAccount(String userName);
	
	public boolean deleteAccount(int accountNumber);
	
	

}
