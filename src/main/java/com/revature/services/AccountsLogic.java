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
		
public class AccountsLogic {
	
	Date date = new Date();
	long time = date.getTime();
	//Timestamp ts = new Timestamp(time);
	private static Logger logger = Logger.getLogger(AccountsLogic.class);
	BankAccountsDAO baDAO = new BankAccountsDAOImpl();
	
	public void doTransaction(BankAccount account, int transType) {
		switch (transType) {
		case 1 : 
			System.out.println("How much would you like to deposit?");
			Scanner scanD = new Scanner(System.in);
			double amtD = Double.parseDouble(scanD.nextLine().split(" ")[0]);
			amtD = ((double)((int)(amtD * 100)))/100;
			if((account.getAccountBalance() + amtD) > 999999999999999999999999999999999999999999999999.99) {
				System.out.println("Account maximum is $999999999999999999999999999999999999999999999999.99\n" +
						"Good for you, but take your money somewhere else :)");
				break;
			} else if(amtD < 0) {
				System.out.println("Nice try.... but no.");
				break;
			}
			account.deposit(amtD);
			baDAO.updateAccount(account.getAccountNumber(), account.getAccountBalance());
			System.out.println("You have deposited $" + amtD + "\nNew Balance: $" + account.getAccountBalance() );
			logger.info(time + ": Acct. No. " + account.getAccountNumber() + ", Desposit: +$" + amtD + " , UserName: " + account.getUserName());
			break;
		case 2 :
			System.out.println("How much would you like to withdraw");
			Scanner scanW = new Scanner(System.in);
			double amtW = Double.parseDouble(scanW.nextLine().split(" ")[0]);
			amtW = ((double)((int)(amtW * 100)))/100;
			if(amtW < 0) {
				System.out.println("You cannot withdraw a negative amount.");
				break;
			}
			account.withdraw(amtW);
			baDAO.updateAccount(account.getAccountNumber(), account.getAccountBalance());
			System.out.println("You have withdrawn $" + amtW + "\nNew Balance: $" + account.getAccountBalance());
			logger.info(time + ": Acct. No. " + account.getAccountNumber() + ", Withdraw: +$" + amtW + " , UserName: " + account.getUserName());
			break;
		case 3 :
			System.out.println("What account number will you transfer to?");
			Scanner scanT = new Scanner(System.in);
			int act2T2 = Integer.parseInt(scanT.nextLine().split(" ")[0]);
			BankAccount otherAccount = baDAO.getAccountFromNum(act2T2);
			System.out.println("How much do you want to transfer?");
			double amtT = Double.parseDouble(scanT.nextLine().split(" ")[0]);
			amtT = ((double)((int)(amtT * 100)))/100;
			if((otherAccount.getAccountBalance() + amtT ) > 999999999999999999999999999999999999999999999999.99) {
				System.out.println("Account maximum is $999999999999999999999999999999999999999999999999.99\n" +
						"This transfer is currently too large... Tell them to open a new account. :)");
				break;
			} else if (amtT < 0) {
				System.out.println("You shouldn't try to steal... shame on you!");
				break;
			}
			if(account.getAccountNumber() == otherAccount.getAccountNumber()) {
				logger.info(time + ": From Acct. No. " + account.getAccountNumber() + " To Acct. No. " + otherAccount.getAccountNumber() + " , Transfer: $" + amtT);
				break;
			}
			account.transfer(amtT, otherAccount);
			baDAO.updateAccount(account.getAccountNumber(), account.getAccountBalance());
			baDAO.updateAccount(otherAccount.getAccountNumber(), otherAccount.getAccountBalance());
			logger.info(time + ": From Acct. No. " + account.getAccountNumber() + " To Acct. No. " + otherAccount.getAccountNumber() + " , Transfer: $" + amtT);
			break;
		}
	}
	
}
