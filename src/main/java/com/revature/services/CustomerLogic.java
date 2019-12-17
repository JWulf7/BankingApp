package com.revature.services;

import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;

import com.revature.models.BankAccount;
import com.revature.models.Customer;
import com.revature.repositories.BankAccountsDAO;
import com.revature.repositories.BankAccountsDAOImpl;
import com.revature.repositories.CustomersDAO;
import com.revature.repositories.CustomersDAOImpl;
import com.revature.repositories.TransactionsDAO;
import com.revature.repositories.TransactionsDAOImpl;

public class CustomerLogic {
	
		CustomersDAO cDAO = new CustomersDAOImpl();
		TransactionsDAO tDAO = new TransactionsDAOImpl();
		
		
	public Customer grabWholeCustomer(String userName, String password) {
		Customer customer = cDAO.getCustomerByUserName(userName, password);
		BankAccountsDAO baDAO = new BankAccountsDAOImpl();
		TreeMap<Integer, BankAccount> userAccounts = baDAO.getAccountFromUser(userName);
		customer.setUserAccounts(userAccounts);
		for(int i = 1; i <= userAccounts.size(); i++) {
			ArrayBlockingQueue<String> trans = tDAO.getTransactions(userAccounts.get(i).getAccountNumber());
			userAccounts.get(i).setRecentTransactions(trans);
		}
		return customer;
	}
	
	
public Customer existingUserLoginCustomer() {
		
		Customer thisCustomer = new Customer();
		
		System.out.println("please enter your username:");
		Scanner scan = new Scanner(System.in);
		String loginName = scan.nextLine();
		thisCustomer.setUserName(loginName);
		
		System.out.println("please enter your password:");
		String loginPass = scan.nextLine();							
		thisCustomer.setPassword(loginPass);
		
		return existingCustomerCheck(thisCustomer,loginName,loginPass);
		
	}

	Customer setNewUserFields(Customer newCustomer) {
		System.out.println("All fields are required:");
		System.out.println("First Name  (50 characters max):");
		Scanner scan = new Scanner(System.in);
		String typeFirstName = scan.nextLine();

		if (acceptableFirstName(typeFirstName)) {
			newCustomer.setFirstName(typeFirstName);
		} else {
			while (acceptableFirstName(typeFirstName)) {
				System.out.println("Please enter your first name (50 characters max):");
				typeFirstName = scan.nextLine();
			}
		}
		System.out.println("Please enter your last name (50 characters max):");
		String typeLastName = scan.nextLine();
		
		if (acceptableLastName(typeLastName)) {
			newCustomer.setLastName(typeLastName);
		} else {
			while (acceptableLastName(typeLastName)) {
				System.out.println("Please enter your last name (50 characters max):");
				typeLastName = scan.nextLine();
			}
		}
		System.out.println("Please enter your address (150 characters max):");
		String typeAddress = scan.nextLine();
		if (acceptableAddress(typeAddress)) {
			newCustomer.setAddress(typeAddress);
		} else {
			while (acceptableAddress(typeAddress)) {
				System.out.println("Please enter your address (150 characters max):");
				typeAddress = scan.nextLine();
			}
		}
		newCustomer.setApproved(false);
		//
		cDAO.createCustomer(newCustomer);
		//
		System.out.println(
				"Your account is pending... admin will contact you when your account is approved or denied. \nThank You");
		System.out.println("Press enter to exit");
		scan.nextLine();
		return newCustomer;
	}
	
	public Customer existingCustomerCheck(Customer thisCustomer, String loginName, String loginPass) {
		CustomersDAO cDaoObj = new CustomersDAOImpl();
		if (cDaoObj.customerExists(loginName)) {
			Customer check = cDaoObj.getCustomerByUserName(loginName, loginPass);
			if(check.getPassword().equals(loginPass)) {
				thisCustomer = check;
				return grabWholeCustomer(loginName, loginPass);
			} else {
				System.out.println("The password you provided was incorrect.");
				return null;
			}
		} else {
			System.out.println("Username is not valid.  Thank you.");
		}
		return null;
	}
	
	public boolean acceptableFirstName(String typeFirstName) {
		if ((!typeFirstName.equals("")) && (typeFirstName.length() <= 50)) {
			return true;
		} else return false;
	}

	public boolean acceptableLastName(String typeLastName) {
		if ((!typeLastName.equals("")) && (typeLastName.length() <= 50)) {
			return true;
		} else return false;
	}
	public boolean acceptableAddress(String typeAddress) {
		if ((!typeAddress.equals("")) && (typeAddress.length() <= 150)) {
			return true;
		} else return false;
	}
	
}
