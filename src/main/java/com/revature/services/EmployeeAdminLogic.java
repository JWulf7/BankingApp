package com.revature.services;

import java.util.Scanner;

import com.revature.Driver;
import com.revature.models.BankAccount;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.repositories.BankAccountsDAO;
import com.revature.repositories.BankAccountsDAOImpl;
import com.revature.repositories.CustomersDAO;
import com.revature.repositories.CustomersDAOImpl;
import com.revature.repositories.EmployeesAdminDAO;
import com.revature.repositories.EmployeesAdminDAOImpl;

public class EmployeeAdminLogic {

	AccountsLogic aLogic = new AccountsLogic();
	CustomerLogic cLogic = new CustomerLogic();
	CustomersDAO cDAO = new CustomersDAOImpl();
	EmployeesAdminDAO eaDAO = new EmployeesAdminDAOImpl();
	BankAccountsDAO bDAO = new BankAccountsDAOImpl();
	
	
	public Employee existingUserLoginEmployee() {
		Employee thisEmployee = new Employee();
		
		System.out.println("please enter your username:");
		Scanner scan = new Scanner(System.in);
		String loginName = scan.nextLine();
		thisEmployee.setUserName(loginName);
		
		System.out.println("please enter your password:");
		String loginPass = scan.nextLine();								
		thisEmployee.setPassword(loginPass);
		
		return existingEmployeeCheck(loginName, loginPass);
	}
	
	
	public void existingEmployeeMainMenu(Employee employee) {
		Scanner scan = new Scanner(System.in);
		String custUN;
		 do {
		Bank.userFriendlyDate();
		System.out.println("Welcome back, " + employee.getFirstName() + ".");

		System.out.println("\n");
		System.out.println("Enter a customer to view their account:");
		System.out.println("or to quit, type: Q\n\n");
		
		System.out.println("Username:");
		custUN = scan.nextLine();
		if (cDAO.customerExists(custUN)) {
			int typeOfTrans;
			do {
			Customer currentCust = cDAO.getCustomerByUserNameOnly(custUN);
			currentCust = cLogic.grabWholeCustomer(currentCust.getUserName(), currentCust.getPassword());
			employeeCustomerInfo(currentCust);
			System.out.println("---------------------");
			System.out.println("Please make a selection:");
			

			
				typeOfTrans = employeeCustomerMenuOptions(currentCust);
				if (typeOfTrans == 1) {
					setCustomerApproval(currentCust);
				}
			} while (typeOfTrans != 2);
		} else if((custUN.equals("Q")) || (custUN.equals("q"))) {
			System.out.println("Goodbye " + employee.getFirstName() +"!");
			return;
		}
		else {
			System.out.println("Could not find customer with that username.");
		} 
		 }while((!custUN.equals("Q")) || (!custUN.equals("q")));
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
		//customer = cDAO.getCustomerByUserNameOnly(customer.getUserName());
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
		setApproval(customer, approveDeny);

	}
	
	
	public void existingAdminMainMenu(Employee employee) {
		Scanner scan = new Scanner(System.in);
		String custUN;
		do {
		Bank.userFriendlyDate();
		System.out.println("Welcome back, " + employee.getFirstName() + ".");

		System.out.println("\n");
		System.out.println("Enter a customer to view their account:");
		System.out.println("or to quit, type: Q\n\n");
		
		System.out.println("Username:");
		custUN = scan.nextLine();
		if (cDAO.customerExists(custUN)) {
			Customer currentCust = cDAO.getCustomerByUserNameOnly(custUN);
			currentCust = cLogic.grabWholeCustomer(currentCust.getUserName(), currentCust.getPassword());
			int typeOfTrans;

			do {
				currentCust = cLogic.grabWholeCustomer(currentCust.getUserName(), currentCust.getPassword());
				employeeCustomerInfo(currentCust);
			System.out.println("---------------------");
			System.out.println("Please make a selection:");
			
				
				typeOfTrans = adminCustomerMenuOptions(currentCust);
				
				if (typeOfTrans == 5) {
					BankAccount whichAcct = Bank.existingCustomerAccountsDisplay(currentCust);
					bDAO.deleteAccount(whichAcct.getAccountNumber());
				}
				if (typeOfTrans == 4) {
					openAnAccount(currentCust);
				}
				if (typeOfTrans == 3) {
					BankAccount whichAcct = Bank.existingCustomerAccountsDisplay(currentCust);
					int trans;
					do {
						trans = Bank.customerAccountMenuOptions(whichAcct);
						aLogic.doTransaction(whichAcct, trans);
					} while (trans != 4);
				}
				if (typeOfTrans == 2) {
					setCustomerApproval(currentCust);
				}
			} while (typeOfTrans != 1);
		} else if((custUN.equals("Q")) || (custUN.equals("q"))) {
			System.out.println("Goodbye " + employee.getFirstName() + "!");
			return;
		} else {
			System.out.println("Could not find customer with that username.");
		}
		} while((!custUN.equals("Q")) || (!custUN.equals("q")));

	}
	
	public int adminCustomerMenuOptions(Customer customer) {
		System.out.println("1. Go Back");
		System.out.println("2. Approve or Deny Customer");
		System.out.println("3. Enter Accounts");
		System.out.println("4. Open an Account");
		System.out.println("5. Close an Account");
		Scanner scan = new Scanner(System.in);
		return Integer.parseInt(scan.nextLine().split(" ")[0]);
		
	}
	
	public void openAnAccount(Customer customer) {
		if (customer.isApproved()) {
			
			System.out.println("How much would you like to deposit?");
			Scanner scanD = new Scanner(System.in);
			double amtD = Double.parseDouble(scanD.nextLine().split(" ")[0]);
			openAnAccountCheck(customer, amtD);
		} else {
			System.out.println("This customer is not approved yet.");
		}
	}
	
	public Employee existingEmployeeCheck(String loginName, String password) {
		if(eaDAO.employeeExists(loginName)) {
			Employee employee = eaDAO.getEmployee(loginName);
			if(employee.getPassword().equals(password)) {
				return employee;
			}else {
				System.out.println("Incorrect password");
				return null;
			}
		}else {
			System.out.println("Cannot find employee in database");
		}
		return null;
	}
	
	public boolean setApproval(Customer customer, int approveDeny) {
		if(approveDeny == 1) {
			customer.setApproved(true);
			cDAO.updateCustomer(customer);
			return true;
		}
		else {
			customer.setApproved(false);
			cDAO.updateCustomer(customer);
			return false;
		}
	}
	
	public boolean openAnAccountCheck(Customer customer, double amtD) {
		if (amtD >= 200) {
			bDAO.createAccount(customer.getUserName());	
		BankAccount newAccount = bDAO.getNewAccount(customer.getUserName());
			bDAO.updateAccount(newAccount.getAccountNumber(), 0);
			newAccount.deposit(amtD);
			bDAO.updateAccount(newAccount.getAccountNumber(), newAccount.getAccountBalance());
			System.out.println("You have deposited $" + amtD + "\nNew Balance: $" + newAccount.getAccountBalance());
			return true;
		} else {
			System.out.println(
					"You must have deposit atleast $200 to meet the minimum requirement to open an account");
			return false;
		}
	}
}
