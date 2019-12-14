package com.revature.repositories;

import com.revature.models.BankAccount;

public interface BankAccountsDAO {
	
	public BankAccount getAccountFromNum(int accountNumber);
	
	public BankAccount getAccountFromUser(String userName);
	
	public boolean updateAccount(double accountBalance);
	
	public BankAccount createAccount(String userName);

}
