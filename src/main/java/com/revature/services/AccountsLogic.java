package com.revature.services;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.models.BankAccount;
import com.revature.repositories.BankAccountsDAO;
import com.revature.repositories.BankAccountsDAOImpl;
import com.revature.repositories.TransactionsDAO;
import com.revature.repositories.TransactionsDAOImpl;
		
public class AccountsLogic {
	
	Date date = new Date();
	long time = date.getTime();
	//Timestamp ts = new Timestamp(time);
	private static Logger logger = Logger.getLogger(AccountsLogic.class);
	BankAccountsDAO baDAO = new BankAccountsDAOImpl();
	TransactionsDAO tDAO = new TransactionsDAOImpl();
	TransactionsLogic tLogic = new TransactionsLogic();
	
	public void doTransaction(BankAccount account, int transType) {
		switch (transType) {
		case 1 : 
			System.out.println("How much would you like to deposit?");
			Scanner scanD = new Scanner(System.in);
			double amtD = Double.parseDouble(scanD.nextLine().split(" ")[0]);
			deposit(account, amtD);
			tDAO.addTransaction(tLogic.transactionTime(), account.getAccountNumber(), "Deposit: +$" + amtD);
			account.setRecentTransactions(tDAO.getTransactions(account.getAccountNumber()));
			break;
		case 2 :
			System.out.println("How much would you like to withdraw");
			Scanner scanW = new Scanner(System.in);
			double amtW = Double.parseDouble(scanW.nextLine().split(" ")[0]);
			withdraw(account, amtW);
			tDAO.addTransaction(tLogic.transactionTime(), account.getAccountNumber(), "Withdraw: -$" + amtW);
			account.setRecentTransactions(tDAO.getTransactions(account.getAccountNumber()));
			break;
		case 3 :
			System.out.println("What account number will you transfer to?");
			Scanner scanT = new Scanner(System.in);
			int act2T2 = Integer.parseInt(scanT.nextLine().split(" ")[0]);
			BankAccount otherAccount = baDAO.getAccountFromNum(act2T2);
			System.out.println("How much do you want to transfer?");
			double amtT = Double.parseDouble(scanT.nextLine().split(" ")[0]);
			transfer(account, amtT, otherAccount);
			tDAO.addTransaction(tLogic.transactionTime(), account.getAccountNumber(), "Outgoing Transaction: -$" + amtT);
			tDAO.addTransaction(tLogic.transactionTime(), otherAccount.getAccountNumber(), "Incoming Transaction: +$" + amtT);
			account.setRecentTransactions(tDAO.getTransactions(account.getAccountNumber()));
			otherAccount.setRecentTransactions(tDAO.getTransactions(otherAccount.getAccountNumber()));
			break;
		}
	}
	
	public double deposit(BankAccount account, double amtD) {
		
		amtD = ((double)((int)(amtD * 100)))/100;
		if((account.getAccountBalance() + amtD) > 999999999999999999999999999999999999999999999999.99) {
			System.out.println("Account maximum is $999999999999999999999999999999999999999999999999.99\n" +
					"Good for you, but take your money somewhere else :)");
			return 0;
		} else if(amtD < 0) {
			System.out.println("Nice try.... but no.");
			return 0;
		}
		account.deposit(amtD);
		baDAO.updateAccount(account.getAccountNumber(), account.getAccountBalance());
		
		System.out.println("You have deposited $" + amtD + "\nNew Balance: $" + account.getAccountBalance() );
		logger.info(time + ": Acct. No. " + account.getAccountNumber() + ", Desposit: +$" + amtD + " , UserName: " + account.getUserName());
		return amtD;
		
	}
	
	public double withdraw(BankAccount account, double amtW) {
		amtW = ((double)((int)(amtW * 100)))/100;
		if(amtW < 0) {
			System.out.println("You cannot withdraw a negative amount.");
			return 0;
		}
		if((account.getAccountBalance() - amtW) < 200) {
			System.out.println("Minimum account balance is $200.00\n"
					+ "You cannot withdraw that much.");
			return 0;
		}
		account.withdraw(amtW);
		baDAO.updateAccount(account.getAccountNumber(), account.getAccountBalance());
		
		System.out.println("You have withdrawn $" + amtW + "\nNew Balance: $" + account.getAccountBalance());
		logger.info(time + ": Acct. No. " + account.getAccountNumber() + ", Withdraw: +$" + amtW + " , UserName: " + account.getUserName());
		return amtW;
	}
	
	public double transfer(BankAccount account, double amtT, BankAccount otherAccount) {
		
		amtT = ((double)((int)(amtT * 100)))/100;
		if((otherAccount.getAccountBalance() + amtT ) > 999999999999999999999999999999999999999999999999.99) {
			System.out.println("Account maximum is $999999999999999999999999999999999999999999999999.99\n" +
					"This transfer is currently too large... Tell them to open a new account. :)");
			return 0;
		} else if (amtT < 0) {
			System.out.println("You shouldn't try to steal... shame on you!");
			return 0;
		}
		if(account.getAccountNumber() == otherAccount.getAccountNumber()) {
			logger.info(time + ": From Acct. No. " + account.getAccountNumber() + " To Acct. No. " + otherAccount.getAccountNumber() + " , Transfer: $" + amtT);
			return 0;
		}
		if((account.getAccountBalance() - amtT) < 200) {
			System.out.println("Minimum account balance is $200.00\n"
					+ "You cannot transfer that much.");
			return 0;
		}
		account.transfer(amtT, otherAccount);
		baDAO.updateAccount(account.getAccountNumber(), account.getAccountBalance());
		baDAO.updateAccount(otherAccount.getAccountNumber(), otherAccount.getAccountBalance());
	
		logger.info(time + ": From Acct. No. " + account.getAccountNumber() + " To Acct. No. " + otherAccount.getAccountNumber() + " , Transfer: $" + amtT);
		return amtT;
	}
	
}
