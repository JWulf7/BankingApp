package com.revature.models;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class Bank {

	

	
	
	
	
	public void startHere() {
		//welcomeScreen();
		Scanner scan = new Scanner(System.in);
		System.out.println("Please press enter...");
		String input = scan.nextLine().split(" ")[0];
		
		while(!input.toUpperCase().equals("Q")) {
			welcomeScreen();
			 input = scan.nextLine().split(" ")[0];
			switch (input) {
			case "1" : 
				//scan.close();								// new close
				int employeeCheck = existingUserLogin();
				switch (employeeCheck) {
				case 1 :
					Customer signInCustomer = existingUserLoginCustomer();
					
					existingCustomerMainMenu(signInCustomer);
					break;
				case 2 :
					existingUserLoginEmployee();
					break;
				}
				break;
			case "2" :
			//	scan.close();								// new close
				Customer newCust = createNewCustomer();	// returns a new customer with a username and a password
				setNewUserFields(newCust);
				break;
			}
		}
		
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
		System.out.println("=                  or          2. New User                       =");
		System.out.println("=              or at any time  Q. Quit                           =");
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
		 return existingUserLogin1();
	}
	
	
	public int existingUserLogin1() {
		System.out.println("Please select:   1. Customer");
		System.out.println("       or        2. Employee");
		Scanner scan = new Scanner(System.in);
		//int employeeCheck = Integer.parseInt(scan.nextLine().split(" ")[0]);							// commented this out
		int employeeCheck = Integer.parseInt(scan.nextLine().split(" ")[0]);					// new nextint
		//scan.close();   														// new close
		if(employeeCheck == 1 || employeeCheck == 2) {
			//System.out.println("employee check returned a number");
			//scan.close();														// new comment out
			return employeeCheck;
		}
		else {
			while(employeeCheck != 1 || employeeCheck != 2) {
			//Scanner scan2 = new Scanner(System.in);
			System.out.println("Please enter an appropriate selection:");
			System.out.println("Please select:   1. Customer");
			System.out.println("       or        2. Employee");						// vv~~~~~ commented this out
			//employeeCheck = Integer.parseInt(scan2.nextLine().split(" ")[0]);	// also created new scanner and renamed this line scan2
			employeeCheck = Integer.parseInt(scan.nextLine().split(" ")[0]);
			//scan2.close(); 														// new close
			}
		}
		//scan.close();
		return 0;
	}
	
	public Customer existingUserLoginCustomer() {
		Customer thisCustomer = new Customer();
		System.out.println("please enter your username:");
		Scanner scan = new Scanner(System.in);
	//	if(scan.hasNextLine()) {
		String loginName = scan.nextLine();
		//scan.close(); 														// new close
		thisCustomer.setUserName(loginName);
	//	}
		System.out.println("please enter your password:");
		//if(scan.hasNextLine()) {
		//Scanner scan2 = new Scanner(System.in);								// new scanner
		String loginPass = scan.nextLine();								// renamed scan2
		//scan2.close();
		thisCustomer.setPassword(loginPass);
		//}
		System.out.println("returned a customer object with username and password");
		//scan.close();
		return thisCustomer;
	}
	
	public Employee existingUserLoginEmployee() {
		Employee thisEmployee = new Employee();
		System.out.println("please enter your username:");
		Scanner scan = new Scanner(System.in);
		System.out.println("I think it good till here");
		String loginName = scan.nextLine();
		//scan.close(); 													// new close
		
		
		thisEmployee.setUserName(loginName);
		System.out.println("please enter your password:");
		//Scanner scan2 = new Scanner(System.in); 							// new scanner
		String loginPass = scan.nextLine();								// renamed scan2
		//scan2.close(); 															// new close
		thisEmployee.setPassword(loginPass);
		System.out.println("returned a employee object with username and password");
		//scan.close();
		return thisEmployee;
		
	}
	
	public Customer createNewCustomer() {
		Customer customer = new Customer();
		System.out.println("Please select a username: \n"
				+ "*must be atleast 6 characters long");
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		//scan.close(); 														// new close
		while(!userNameIsAcceptable(input)) {
			System.out.println("Usernames must be atleast 6 characters long...");
			System.out.println("Please try another username:");
			//Scanner scan2 = new Scanner(System.in);							// new scanner
			input = scan.nextLine();										// renamed
			//scan2.close();													// new close
		}
		if(userNameIsAcceptable(input)) {
			customer.setUserName(input);
		}
		System.out.println("Please select a password: \n"
				+ "*must be atleast 6 characters long and contain: 1 uppercase letter, 1 lowercase letter, and 1 digit 0-9");
		//Scanner scan5 = new Scanner(System.in); 						// new scanner
		String input2 = scan.nextLine();								// renamed scan
		//scan5.close(); 														// new close
		while(!userPasswordIsAcceptable(input2)) {
			System.out.println("Passwords must be 6 characters long and contain atleast: 1 uppercase letter, 1 lowercase letter, and 1 digit 0-9...");
			System.out.println("Please try another password:");
			//Scanner scan4 = new Scanner(System.in);							// new scanner
			input2 = scan.nextLine();												// renamed scan, also changed input to input2
			//scan4.close(); 														// new close
		}
		if(userPasswordIsAcceptable(input2)) {
			System.out.println("Please verify password:");
			//Scanner scan3 = new Scanner(System.in);
			String input3 = scan.nextLine();
			//scan3.close(); 															// new close
			if(input2.equals(input3)) {
				customer.setPassword(input2);
			} else {
				System.out.println("Passwords did not match, please try again.");
			}
		}
	//	scan.close();
		return customer;
	}
	
	boolean userNameIsAcceptable(String nameTry) {
		if(nameTry.length() >5) {
			return true;
		}
		else return false;
	}
	
	boolean userPasswordIsAcceptable(String passTry) {
		if((passTry.length() > 5) && (passTry.contains("0") || passTry.contains("1") || passTry.contains("2") || passTry.contains("3") || passTry.contains("4") || 
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
	
	Customer setNewUserFields(Customer newCustomer) {
		System.out.println("All fields are required:");
		System.out.println("First Name:");
		Scanner scan = new Scanner(System.in);
		String typeFirstName = scan.nextLine();
		//scan.close(); 															// new close
		if(!typeFirstName.equals("")) {
			newCustomer.setFirstName(typeFirstName);
		} else {
			while(typeFirstName.equals("")) {
				System.out.println("Please enter your first name:");
				//Scanner scan2 = new Scanner(System.in);							// new scanner
				typeFirstName = scan.nextLine();											//renamed scan
				//scan2.close(); 														// new close
			}
		}
		System.out.println("Please enter your last name:");
		//Scanner scan3 = new Scanner(System.in);							// new scanner
		String typeLastName = scan.nextLine();							// renamed scan
		//scan3.close(); 														// new close
		if(!typeLastName.equals("")) {
			newCustomer.setLastName(typeLastName);
		} else {
			while(typeLastName.equals("")) {
				System.out.println("Please enter your last name:");
				//Scanner scan4 = new Scanner(System.in);					// new scanner
				typeLastName = scan.nextLine();						// renamed scan
				//scan4.close(); 														// new close
			}
		}
		//Scanner scan5 = new Scanner(System.in); 							// new scanner
		System.out.println("Please enter your address:");
		String typeAddress = scan.nextLine();								// renamed scan
		//scan5.close(); 													// new close
		if(!typeAddress.equals("")) {
			newCustomer.setAddress(typeAddress);
		} else {
			while(typeAddress.equals("")) {
				System.out.println("Please enter your address:");
				//Scanner scan6 = new Scanner(System.in); 							// new scanner
				typeAddress = scan.nextLine();													// renamed scan
				//scan6.close(); 																	// new close
			}
		}
		System.out.println("Your account is pending... admin will contact you when your account is approved or denied. \nThank You");
		System.out.println("Press enter to exit");
		scan.nextLine();
		return newCustomer;
	}

	public void existingCustomerMainMenu(Customer customer) {
		
		userFriendlyDate();
		//System.out.println("\n ");
		System.out.println("Welcome back, " + customer.getFirstName() + ".");
		//System.out.println("\n");
		System.out.println("Accounts:");
		existingCustomerAcountsDisplay(customer);
		System.out.println("\n");
	}
	
	public void userFriendlyDate() {
		Date dateObj = new Date();
		String dateFormat = "EEEE, MMM d, y      h:mm a ";
		SimpleDateFormat simpleDF = new SimpleDateFormat(dateFormat);
		System.out.println(simpleDF.format(dateObj));
	}
	
	public BankAccount existingCustomerAcountsDisplay(Customer customer) {
		System.out.println("________");
		int x = 1;
		System.out.println("Please select an account:");
		for(BankAccount account : customer.getUserAccounts()) {
			int accountName = account.getAccountNumber();
			double accountBalance = account.getAccountBalance();
			System.out.println(x + ".     " + accountName + "..............$" + accountBalance);
			System.out.println("___________________________________");
			x++;
		}
	}
//	public BankAccount customerChooseAccount(Customer customer) {
//		int x = 1;
//		for(BankAccount account : customer.getUserAccounts()) {
//			System.out.println(x + ".       " + account.getAccountNumber());
//		}
//	}
	
	public void customerAccountMenuOptions(BankAccount account) {
		System.out.println("Please select: \n+"
				+ "1. View Recent Transactions \n+"
				+ "2. Deposit \n+"
				+ "3. Withdraw \n+"
				+ "4. Transfer \n+"
				+ "");
	}
	
	
}
