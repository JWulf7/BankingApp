package com.revature.models;

import java.util.Scanner;

public class Bank {

	

	
	
	
	
	public void startHere() {
		//welcomeScreen();
		Scanner scan = new Scanner(System.in);
		System.out.println("Please press enter...");
		String input = scan.nextLine();
		
		while(!input.toUpperCase().equals("Q")) {
			welcomeScreen();
			 input = scan.next();
			switch (input) {
			case "1" : 
				int employeeCheck = existingUserLogin();
				switch (employeeCheck) {
				case 1 :
					existingUserLoginCustomer();
				case 2 :
					existingUserLoginEmployee();
					break;
				}
				break;
			case "2" :
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
		int employeeCheck = scan.nextInt();
		if(employeeCheck == 1 || employeeCheck == 2) {
			//System.out.println("employee check returned a number");
			scan.close();
			return employeeCheck;
		}
		else {
			while(employeeCheck != 1 || employeeCheck != 2) {
			System.out.println("Please enter an appropriate selection:");
			System.out.println("Please select:   1. Customer");
			System.out.println("       or        2. Employee");
			employeeCheck = scan.nextInt();
			}
		}
		scan.close();
		return 0;
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
		
		System.out.println("returned a customer object with username and password");
		scan.close();
		return thisCustomer;
	}
	
	public Employee existingUserLoginEmployee() {
		Employee thisEmployee = new Employee();
		System.out.println("please enter your username:");
		Scanner scan = new Scanner(System.in);
		System.out.println("I think it good till here");
		String loginName = scan.nextLine();
		
		
		thisEmployee.setUserName(loginName);
		System.out.println("please enter your password:");
		String loginPass = scan.nextLine();
		thisEmployee.setPassword(loginPass);
		System.out.println("returned a employee object with username and password");
		scan.close();
		return thisEmployee;
		
	}
	
	public Customer createNewCustomer() {
		Customer customer = new Customer();
		System.out.println("Please select a username: \n"
				+ "*must be atleast 6 characters long");
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		while(!userNameIsAcceptable(input)) {
			System.out.println("Usernames must be atleast 6 characters long...");
			System.out.println("Please try another username:");
			input = scan.nextLine();
		}
		if(userNameIsAcceptable(input)) {
			customer.setUserName(input);
		}
		System.out.println("Please select a password: \n"
				+ "*must be atleast 6 characters long and contain: 1 uppercase letter, 1 lowercase letter, and 1 digit 0-9");
		String input2 = scan.nextLine();
		while(!userPasswordIsAcceptable(input2)) {
			System.out.println("Passwords must be 6 characters long and contain atleast: 1 uppercase letter, 1 lowercase letter, and 1 digit 0-9...");
			System.out.println("Please try another password:");
			input = scan.nextLine();
		}
		if(userPasswordIsAcceptable(input2)) {
			System.out.println("Please verify password:");
			Scanner scan3 = new Scanner(System.in);
			String input3 = scan3.nextLine();
			if(input2.equals(input3)) {
				customer.setPassword(input2);
			} else {
				System.out.println("Passwords did not match, please try again.");
			}
		}
		scan.close();
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
		if(!typeFirstName.equals("")) {
			newCustomer.setFirstName(typeFirstName);
		} else {
			while(typeFirstName.equals("")) {
				System.out.println("Please enter your first name:");
				typeFirstName = scan.nextLine();
			}
		}
		String typeLastName = scan.nextLine();
		if(!typeLastName.equals("")) {
			newCustomer.setLastName(typeLastName);
		} else {
			while(typeLastName.equals("")) {
				System.out.println("Please enter your last name:");
				typeLastName = scan.nextLine();
			}
		}
		String typeAddress = scan.nextLine();
		if(!typeAddress.equals("")) {
			newCustomer.setAddress(typeAddress);
		} else {
			while(typeAddress.equals("")) {
				System.out.println("Please enter your address:");
				typeAddress = scan.nextLine();
			}
		}
		return newCustomer;
	}
	
}
