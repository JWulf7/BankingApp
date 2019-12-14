package com.revature.models;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

import com.revature.Driver;
import com.revature.repositories.CustomersDAO;
import com.revature.repositories.CustomersDAOImpl;

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
					if(signInCustomer == null) {
						break;
					}
					
					existingCustomerMainMenu(signInCustomer);
					break;
				case 2 :
					Employee signInEmployee = existingUserLoginEmployee();
					
					existingEmployeeMainMenu(signInEmployee);
					break;
				case 3 :
					Employee signInAdmin = existingUserLoginEmployee();			// might need to copy this method and just change it to return an admin.. but should be able to work with employee ref var
					
					existingAdminMainMenu(signInAdmin);
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
		 return existingUserLogin1();
	}
	
	
	public int existingUserLogin1() {
		System.out.println("Please select:   1. Customer");
		System.out.println("                 2. Employee");
		System.out.println("                 3. Admin");
		Scanner scan = new Scanner(System.in);
		//int employeeCheck = Integer.parseInt(scan.nextLine().split(" ")[0]);							// commented this out
		int employeeCheck = Integer.parseInt(scan.nextLine().split(" ")[0]);					// new nextint
		//scan.close();   														// new close
		if(employeeCheck == 1 || employeeCheck == 2 || employeeCheck == 3) {
			//System.out.println("employee check returned a number");
			//scan.close();														// new comment out
			return employeeCheck;
		}
		else {
			while(employeeCheck != 1 || employeeCheck != 2 || employeeCheck != 3) {
			//Scanner scan2 = new Scanner(System.in);
			System.out.println("Please enter an appropriate selection:");
			System.out.println("Please select:   1. Customer");
			System.out.println("                 2. Employee");		
			System.out.println("                 3. Admin");			// vv~~~~~ commented this out
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
		CustomersDAO cDaoObj = new CustomersDAOImpl();
		if (cDaoObj.customerExists(loginName)) {
			Customer check = cDaoObj.getCustomerByUserName(loginName, loginPass);
			if(check.getPassword().equals(loginPass)) {
				thisCustomer = check;
				return thisCustomer;
			} else {
				System.out.println("The password you provided was incorrect.");
				return null;
			}
		} else {
			System.out.println("Username is not valid.  Thank you.");
		}
		/*return thisCustomer;      // THIS IS WHERE I NEED TO TAKE THE INFO GIVEN, AND GRAB CUSTOMER FROM DB
		 for test, to undo... un comment above line, comment out below. */
		return null;
	}
	
	public Employee existingUserLoginEmployee() {
		Employee thisEmployee = new Employee();
		System.out.println("please enter your username:");
		Scanner scan = new Scanner(System.in);
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
				while(!input2.equals(input3)) {
					System.out.println("Passwords did not match, please try again.");
					input3 = scan.nextLine();
				}
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
		BankAccount actSelect = existingCustomerAccountsDisplay(customer);
		System.out.println("\n");
		int typeOfTrans;
		do {
		typeOfTrans = customerAccountMenuOptions(actSelect);
		doTransaction(actSelect, typeOfTrans);
		
		} while (typeOfTrans != 4);
	}
	
	public void userFriendlyDate() {
		Date dateObj = new Date();
		String dateFormat = "EEEE, MMM d, y      h:mm a ";
		SimpleDateFormat simpleDF = new SimpleDateFormat(dateFormat);
		System.out.println(simpleDF.format(dateObj));
	}
	
	public BankAccount existingCustomerAccountsDisplay(Customer customer) {
		System.out.println("________");
		System.out.println("Please select an account:");
		for(int actSelect = 1; actSelect <= customer.getUserAccounts().size(); actSelect++) {
			int accountName = customer.getUserAccounts().get(actSelect).getAccountNumber();
			double accountBalance = customer.getUserAccounts().get(actSelect).getAccountBalance();
			System.out.println(actSelect + ".      Acct. " + accountName + "............" + accountBalance);
			System.out.println("__________________________________");
		}
		Scanner scan = new Scanner(System.in);
		int acctChoice = Integer.parseInt(scan.nextLine().split(" ")[0]);
		while((!(acctChoice >= 1)) || !((acctChoice <= customer.getUserAccounts().size()))) {
			System.out.println("Please select a valid option.");
			acctChoice = Integer.parseInt(scan.nextLine().split(" ")[0]);
		}
		return customer.getUserAccounts().get(acctChoice);
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
	
	public int customerAccountMenuOptions(BankAccount account) {
		System.out.println("Acct. " + account.getAccountNumber() + " ..........$" + account.getAccountBalance() + "\n");
		System.out.println("Please select: \n"
				+ "1. Deposit \n"
				+ "2. Withdraw \n"
				+ "3. Transfer \n"
				+ "4. Go Back \n"
				+ "5. View Recent Transactions ** COMING SOON!**\n"
				+ "");
		Scanner scan = new Scanner(System.in);
		int customerSelection = Integer.parseInt(scan.nextLine().split(" ")[0]);	
		while((customerSelection < 1) || (customerSelection > 5)) {
			System.out.println("Please select a valid option.");
			customerSelection = Integer.parseInt(scan.nextLine().split(" ")[0]);	
		}
		System.out.println("returned customerSelection as an int");
		return customerSelection;
	}
	
	
	public void doTransaction(BankAccount account, int transType) {
		switch (transType) {
		case 1 : 
			System.out.println("How much would you like to deposit?");
			Scanner scanD = new Scanner(System.in);
			double amtD = Double.parseDouble(scanD.nextLine().split(" ")[0]);	
			account.deposit(amtD);
			System.out.println("You have deposited $" + amtD + "\nNew Balance: $" + account.getAccountBalance() );
			break;
		case 2 :
			System.out.println("How much would you like to withdraw");
			Scanner scanW = new Scanner(System.in);
			double amtW = Double.parseDouble(scanW.nextLine().split(" ")[0]);
			account.withdraw(amtW);
			System.out.println("You have withdrawn $" + amtW + "\nNew Balance: $" + account.getAccountBalance());
			break;
		case 3 :
			System.out.println("What account number will you transfer to?");
			Scanner scanT = new Scanner(System.in);
			int act2T2 = Integer.parseInt(scanT.nextLine().split(" ")[0]);
			// THIS IS WHERE I NEED TO IMPLEMENT GRABBING AN ACCOUNT OBJECT BASED ON ACCOUNT NUMBER
			// otherAccount is just a temporary place holder until i can search for an account on DB and return the 
			// account based on acct number
			BankAccount otherAccount = new BankAccount();
			System.out.println("How much do you want to transfer?");
			double amtT = Double.parseDouble(scanT.nextLine().split(" ")[0]);
			account.transfer(amtT, otherAccount);
		}
	}
	
	
	public void existingEmployeeMainMenu(Employee employee) {
		userFriendlyDate();
		System.out.println("Welcome back, " + employee.getFirstName() + ".");
		//System.out.println("Accounts:");
		//BankAccount actSelect = existingCustomerAccountsDisplay(customer);
		System.out.println("\n");
		System.out.println("Enter a customer to view their account:\n");
		Scanner scan = new Scanner(System.in);
		System.out.println("Username:");
		String custUN = scan.nextLine();
		System.out.println("Last Name:");
		String custLN = scan.nextLine();
		System.out.println("First Name:");
		String custFN = scan.nextLine();
		Customer currentCust = new Customer(custUN, custLN, custFN);
		/* THIS IS WHERE I NEED TO GRAB A CUSTOMER OBJECT FROM MY DATABASE GIVEN THE CURRENT INPUT*
		 INSERTING A TEST CUSTOMER FOR TESTING PURPOSES~~~V
		 */
		currentCust = Driver.testCustomer();  // change this later when i grab a real one
		employeeCustomerInfo(currentCust);
		System.out.println("---------------------");
		System.out.println("Please make a selection:");
		int typeOfTrans;
		
		do {
		typeOfTrans = employeeCustomerMenuOptions(currentCust);
		//doTransaction(actSelect, typeOfTrans);
			if(typeOfTrans == 1) {
				setCustomerApproval(currentCust);
				/*NEED TO SET APPROVAL HERE IN DB IMPLEMENTATION*/
			}
		} while (typeOfTrans != 2);
	}
	
	public int employeeCustomerMenuOptions(Customer customer) {
		System.out.println("1. Approve or Deny Customer");
		System.out.println("2. Go Back");
		Scanner scan = new Scanner(System.in);
		return Integer.parseInt(scan.nextLine().split(" ")[0]);
		
	}
	public void employeeCustomerInfo(Customer customer) {
		System.out.println("Name: " + customer.getLastName() + ", " + customer.getFirstName());
		System.out.println("Username: " + customer.getUserName());
		System.out.println("Address: " + customer.getAddress());
		System.out.println("Approved: " + customer.isApproved());
		System.out.println("-----------------------------------");
		System.out.println("Accounts:");
		System.out.println("-----------");
		for(int i = 1; i <= customer.getUserAccounts().size(); i++)
			System.out.println(i +"- " + customer.getUserAccounts().get(i).getAccountNumber() + ".........$" + customer.getUserAccounts().get(i).getAccountBalance());
		
	}
	public void setCustomerApproval(Customer customer) {
		System.out.println("Current Approval Status: " + customer.isApproved());
		System.out.println("1. Approve");
		System.out.println("2. Deny");
		Scanner scan = new Scanner(System.in);
		int approveDeny = Integer.parseInt(scan.nextLine().split(" ")[0]);
		while(!((approveDeny == 1) || (approveDeny == 2))) {
			System.out.println("Please enter an appropriate selection");
			approveDeny = Integer.parseInt(scan.nextLine().split(" ")[0]);
		}
		if(approveDeny == 1) {
			customer.setApproved(true);
		}
		else customer.setApproved(false);
	}
	
	
	public void existingAdminMainMenu(Employee employee) {
		userFriendlyDate();
		System.out.println("Welcome back, " + employee.getFirstName() + ".");
		//System.out.println("Accounts:");
		//BankAccount actSelect = existingCustomerAccountsDisplay(customer);
		System.out.println("\n");
		System.out.println("Enter a customer to view their account:\n");
		Scanner scan = new Scanner(System.in);
		System.out.println("Username:");
		String custUN = scan.nextLine();
		System.out.println("Last Name:");
		String custLN = scan.nextLine();
		System.out.println("First Name:");
		String custFN = scan.nextLine();
		Customer currentCust = new Customer(custUN, custLN, custFN);
		/* THIS IS WHERE I NEED TO GRAB A CUSTOMER OBJECT FROM MY DATABASE GIVEN THE CURRENT INPUT*
		 INSERTING A TEST CUSTOMER FOR TESTING PURPOSES~~~V
		 */
		currentCust = Driver.testCustomer();  // change this later when i grab a real one
		employeeCustomerInfo(currentCust);
		System.out.println("---------------------");
		System.out.println("Please make a selection:");
		int typeOfTrans;
		
		do {
		typeOfTrans = adminCustomerMenuOptions(currentCust);
		//doTransaction(actSelect, typeOfTrans);
			/*HERE I NEED TO INSERT AN IF STATEMENT
			 *  WITH A CREATE BANKACCOUNT OPTION THAT <<<<<<~~~~~~~~~~~~~~~~~~~~~~HEREEREREHERHEHREHREHE
			 * CREATES A NEW ACCOUNT AND 
			 * DEPOSITS THE MIN INTO THE ACCOUNT*/
			if(typeOfTrans == 4) {
				// create account here
				openAnAccount(currentCust);
			}
			if(typeOfTrans == 3) {
				BankAccount whichAcct= existingCustomerAccountsDisplay(currentCust);
				int trans;
				do {
					trans = customerAccountMenuOptions(whichAcct);
					doTransaction(whichAcct, trans);
				} while (trans != 4);
				//existingCustomerMainMenu(currentCust);
			}
			if(typeOfTrans == 2) {
				setCustomerApproval(currentCust);
				/*NEED TO SET APPROVAL HERE IN DB IMPLEMENTATION*/
			}
		} while (typeOfTrans != 1);
	}
	
	public int adminCustomerMenuOptions(Customer customer) {
		System.out.println("1. Go Back");
		System.out.println("2. Approve or Deny Customer");
		System.out.println("3. Enter Accounts");
		System.out.println("4. Open an Account");
		Scanner scan = new Scanner(System.in);
		return Integer.parseInt(scan.nextLine().split(" ")[0]);
		
	}
	
	public void openAnAccount(Customer customer) {
		if (customer.isApproved()) {
			BankAccount newAccount = new BankAccount();
			// set newAccount to new account from Accounts table, set the account number
			System.out.println("How much would you like to deposit?");
			Scanner scanD = new Scanner(System.in);
			double amtD = Double.parseDouble(scanD.nextLine().split(" ")[0]);
			if (amtD >= 200) {
				newAccount.deposit(amtD);
				System.out.println("You have deposited $" + amtD + "\nNew Balance: $" + newAccount.getAccountBalance());
			} else {
				System.out.println(
						"You must have deposit atleast $200 to meet the minimum requirement to open an account");
			}
		} else {
			System.out.println("This customer is not approved yet.");
		}
	}
}
