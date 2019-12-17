package com.revature.services;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

import com.revature.Driver;
import com.revature.models.Admin;
import com.revature.models.BankAccount;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.repositories.CustomersDAO;
import com.revature.repositories.CustomersDAOImpl;

public class Bank {

	public Bank(){
		super();
		//startHere();
	}

	CustomerLogic cLogic = new CustomerLogic();
	AccountsLogic aLogic = new AccountsLogic();
	EmployeeAdminLogic eaLogic = new EmployeeAdminLogic();
	static TransactionsLogic tLogic = new TransactionsLogic();
	
	
	public void startHere() {
		// welcomeScreen();
		Scanner scan = new Scanner(System.in);
		System.out.println("Please press enter...");
		String input = scan.nextLine().split(" ")[0];
		do {
			welcomeScreen();
			try {
				input = scan.nextLine().split(" ")[0];
				switch (input) {
				case "1":
					// scan.close(); // new close
					int employeeCheck = existingUserLogin();
					switch (employeeCheck) {
					case 1:
						Customer signInCustomer = cLogic.existingUserLoginCustomer();
						if (signInCustomer == null) {
							break;
						}

						existingCustomerMainMenu(signInCustomer);
						break;
					case 2:
						Employee signInEmployee = eaLogic.existingUserLoginEmployee();
						if (signInEmployee != null) {
							eaLogic.existingEmployeeMainMenu(signInEmployee);
						}
						break;
					case 3:
						try {
							Admin signInAdmin = (Admin) eaLogic.existingUserLoginEmployee();
							if (signInAdmin != null) {
								if (signInAdmin.isAdmin) {
									eaLogic.existingAdminMainMenu(signInAdmin);
								} else {
									System.out.println("You do not have Admin privelages.  Thank you.");
								}
							}
						} catch (ClassCastException e) {
							System.out.println("You do not have Admin privelages. Thank you.");
						}
					case 0:
						input = "r";
						break;
					}
					break;
				case "2":
					Customer newCust = createNewCustomer();
					cLogic.setNewUserFields(newCust);
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Not an acceptable input. Goodbye");
				input.equals("Q");
			} catch (NullPointerException e) {
				System.out.println("Seriously.. come back when you have some manners...");
			}

		} while (!input.toUpperCase().equals("Q"));

		goodByeScreen();
		scan.close();
	}
	
	public void welcomeScreen() {
		System.out.println("==================================================================");
		System.out.println("=                                                                =");
		System.out.println("=                                                                =");
		System.out.println("=                                                                =");
		System.out.println("=                   WELCOME TO THE BANK                          =");
		System.out.println("=                                                                =");
		System.out.println("=                                                                =");
		System.out.println("=                                                                =");
		System.out.println("=              Please select : 1. Existing Member                =");
		System.out.println("=                              2. New User                       =");
		System.out.println("=                              Q. Quit                           =");
		System.out.println("==================================================================");
	}
	
	public void goodByeScreen() {
		System.out.println("==================================================================");
		System.out.println("=                                                                =");
		System.out.println("=                                                                =");
		System.out.println("=                                                                =");
		System.out.println("=                           GOODBYE                              =");
		System.out.println("=                                                                =");
		System.out.println("=                                                                =");
		System.out.println("=                                                                =");
		System.out.println("=                                                                =");
		System.out.println("=                                                                =");
		System.out.println("=                                                                =");
		System.out.println("==================================================================");
	}
	
	
	
	
	public int existingUserLogin() {
		System.out.println("Please select:   1. Customer");
		System.out.println("                 2. Employee");
		System.out.println("                 3. Admin");
		System.out.println("                 0. Back");
		Scanner scan = new Scanner(System.in);
		int employeeCheck = Integer.parseInt(scan.nextLine().split(" ")[0]);					
		if(employeeCheck == 1 || employeeCheck == 2 || employeeCheck == 3 || employeeCheck == 0) {
			return employeeCheck;
		}
		//else if(employeeCheck == 0) {
			
		//}
		
		else{
			while(employeeCheck != 1 || employeeCheck != 2 || employeeCheck != 3) {
			System.out.println("Please enter an appropriate selection:");
			System.out.println("Please select:   1. Customer");
			System.out.println("                 2. Employee");		
			System.out.println("                 3. Admin");
			System.out.println("                 0. Back");
			employeeCheck = Integer.parseInt(scan.nextLine().split(" ")[0]);
			}
		}
		return 0;
	}
	
	
	
	
	
	public Customer createNewCustomer() {
		Customer customer = new Customer();
		System.out.println("Please select a username: \n"
				+ "*must be atleast 6 characters long, no longer than 50");
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		while(!userNameIsAcceptable(input)) {
			System.out.println("Usernames must be atleast 6 characters long, no longer than 50...");
			System.out.println("Please try another username:");
			input = scan.nextLine();										
		}
		if(userNameIsAcceptable(input)) {
			customer.setUserName(input);
		}
		System.out.println("Please select a password: \n"
				+ "*must be atleast 6 - 50 characters long and contain: 1 uppercase letter, 1 lowercase letter, and 1 digit 0-9");
		String input2 = scan.nextLine();							
		while(!userPasswordIsAcceptable(input2)) {
			System.out.println("Passwords must be 6 characters long and contain atleast: 1 uppercase letter, 1 lowercase letter, and 1 digit 0-9...");
			System.out.println("Please try another password:");
			input2 = scan.nextLine();												
		}
		if(userPasswordIsAcceptable(input2)) {
			System.out.println("Please verify password:");
			String input3 = scan.nextLine();
			//scan3.close(); 															
			if(input2.equals(input3)) {
				customer.setPassword(input3);
			} else {
				while(!input2.equals(input3)) {
					System.out.println("Passwords did not match, please try again.");
					input3 = scan.nextLine();
				}
			}
		}
		return customer;
	}
	
	boolean userNameIsAcceptable(String nameTry) {
		if((nameTry.length() >5) && (nameTry.length() <= 50)) {
			return true;
		}
		else return false;
	}
	
	boolean userPasswordIsAcceptable(String passTry) {
		if((passTry.length() <= 50) && (passTry.length() > 5) && (passTry.contains("0") || passTry.contains("1") || passTry.contains("2") || passTry.contains("3") || passTry.contains("4") || 
				passTry.contains("5") || passTry.contains("6") || passTry.contains("7") || passTry.contains("8") || passTry.contains("9")) &&
				(passTry.contains("A") || passTry.contains("B") || passTry.contains("C") || passTry.contains("D") || passTry.contains("E") || passTry.contains("F") || 
						passTry.contains("G") || passTry.contains("H") || passTry.contains("I") || passTry.contains("J") || passTry.contains("K") || passTry.contains("L") || 
						passTry.contains("M") || passTry.contains("N") || passTry.contains("O") || passTry.contains("P") || passTry.contains("Q") || passTry.contains("R") || 
						passTry.contains("S") || passTry.contains("T") || passTry.contains("U") || passTry.contains("V") || passTry.contains("W") || passTry.contains("X") || 
						passTry.contains("Y") || passTry.contains("Z")) &&
				(passTry.contains("a") || passTry.contains("b") || passTry.contains("c") || passTry.contains("d") || passTry.contains("e") || passTry.contains("f") || 
						passTry.contains("g") || passTry.contains("h") || passTry.contains("i") || passTry.contains("j") || passTry.contains("k") || passTry.contains("l") || 
						passTry.contains("m") || passTry.contains("n") || passTry.contains("o") || passTry.contains("p") || passTry.contains("q") || passTry.contains("r") || 
						passTry.contains("s") || passTry.contains("t") || passTry.contains("u") || passTry.contains("v") || passTry.contains("w") || passTry.contains("x") || 
						passTry.contains("y") || passTry.contains("z"))) {
			return true;
		}
		else return false;
	}
	
	

	public void existingCustomerMainMenu(Customer customer) {
		int rep = 1;
		try {
		do {
		userFriendlyDate();
		
		System.out.println("Welcome back, " + customer.getFirstName() + ".");
		
		System.out.println("Accounts:");
		BankAccount actSelect = existingCustomerAccountsDisplay(customer);
		System.out.println("\n");
		int typeOfTrans;
		if(actSelect == null) {
			rep = 0;
		}
		do {
		typeOfTrans = customerAccountMenuOptions(actSelect);
		aLogic.doTransaction(actSelect, typeOfTrans);
		
		} while (typeOfTrans != 4);
		} while (rep != 0);
		} catch (NullPointerException e) {
			return;
		}
	}
	
	public static void userFriendlyDate() {
		Date dateObj = new Date();
		String dateFormat = "EEEE, MMM d, y      h:mm a ";
		SimpleDateFormat simpleDF = new SimpleDateFormat(dateFormat);
		System.out.println(simpleDF.format(dateObj));
	}
	
	public static BankAccount existingCustomerAccountsDisplay(Customer customer) {
		int acctChoice;
		try {
		//do {
		System.out.println("________");
		System.out.println("Please select an account:");
		for(int actSelect = 1; actSelect <= customer.getUserAccounts().size(); actSelect++) {
			int accountName = customer.getUserAccounts().get(actSelect).getAccountNumber();
			double accountBalance = customer.getUserAccounts().get(actSelect).getAccountBalance();
			System.out.println(actSelect + ".      Acct.#" + accountName + "............$" + accountBalance);
			System.out.println("__________________________________");
		}
		System.out.println("0. Go Back");
		Scanner scan = new Scanner(System.in);
		
		acctChoice = Integer.parseInt(scan.nextLine().split(" ")[0]);
		while((!(acctChoice >= 0)) || !((acctChoice <= customer.getUserAccounts().size()))) {
			System.out.println("Please select a valid option.");
			acctChoice = Integer.parseInt(scan.nextLine().split(" ")[0]);
		}
		
		if (acctChoice == 0) {
			return null;
		}
		
		return customer.getUserAccounts().get(acctChoice);
		//} while (acctChoice != 0);
		}catch(NumberFormatException e) {
			System.out.println("Invalid input.");
			return null;
		}
	}
	
	
	
//	public BankAccount existingCustomerAcountsDisplay(Customer customer) {
//		System.out.println("________");
//		int x = 1;
//		System.out.println("Please select an account:");
//		for(BankAccount account : customer.getUserAccounts()) {
//			int accountName = account.getAccountNumber();
//			double accountBalance = account.getAccountBalance();
//			System.out.println(x + ".     " + accountName + "..............$" + accountBalance);
//			System.out.println("___________________________________");
//			x++;
//		}
//	}
//	public BankAccount customerChooseAccount(Customer customer) {
//		int x = 1;
//		for(BankAccount account : customer.getUserAccounts()) {
//			System.out.println(x + ".       " + account.getAccountNumber());
//		}
//	}
	
	public static int customerAccountMenuOptions(BankAccount account) {
		System.out.println("Acct. " + account.getAccountNumber() + " ..........$" + account.getAccountBalance() + "\n");
		tLogic.transDisplay(account.getRecentTransactions());
		System.out.println("Please select: \n"
				+ "1. Deposit \n"
				+ "2. Withdraw \n"
				+ "3. Transfer \n"
				+ "4. Go Back \n"
				+ "");
		Scanner scan = new Scanner(System.in);
		int customerSelection = Integer.parseInt(scan.nextLine().split(" ")[0]);	
		while((customerSelection < 1) || (customerSelection > 5)) {
			System.out.println("Please select a valid option.");
			customerSelection = Integer.parseInt(scan.nextLine().split(" ")[0]);	
		}
		
		return customerSelection;
	}
	
	

	
	
	
	
	
	
	
}
