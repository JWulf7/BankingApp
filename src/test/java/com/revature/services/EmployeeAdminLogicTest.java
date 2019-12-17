package com.revature.services;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.models.Customer;
import com.revature.util.ConnectionUtil;

public class EmployeeAdminLogicTest {


	@Before
	public void setUp() throws Exception {
			try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "INSERT into project0.employeesadmins (username, userpassword, firstname, lastname, isadmin) " +
						"VALUES ('TestEmployee', 'Password1', 'Larry', 'Berry', false);" +
						"INSERT into project0.employeesadmins (username, userpassword, firstname, lastname, isadmin) " +
						"VALUES ('TestAdmin', 'Password1', 'Mary', 'Berry', true);" +
						"INSERT into project0.customers (username, userpassword, firstname, lastname, address, approved) " +
						"VALUES ('TestUserTrue', 'Password1', 'Larry', 'Berry', '123 Road', true);" + 
						"INSERT into project0.customers (username, userpassword, firstname, lastname, address, approved) " +
						"VALUES ('TestUserFalse', 'Password1', 'Larry', 'Berry', '123 Road', false);";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.execute();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	
	@After
	public void tearDown() throws Exception {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "DELETE from project0.employeesadmins where username = 'TestEmployee';" +
						"DELETE from project0.employeesadmins WHERE username = 'TestAdmin';" +
						"DELETE from project0.customers WHERE username = 'TestUserTrue';" + 
						"DELETE from project0.customers WHERE username = 'TestUserFalse';";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ********************EXISTINGEMPLOYEECHECK*********************************************
	 */
	
	@Test
	public void existingEmployeeCorrect() {
		EmployeeAdminLogic eaLogic = new EmployeeAdminLogic();
		assertNotNull(eaLogic.existingEmployeeCheck("TestEmployee", "Password1"));
	}
	@Test
	public void existingEmployeeBadName() {
		EmployeeAdminLogic eaLogic = new EmployeeAdminLogic();
		assertNull(eaLogic.existingEmployeeCheck("Wrong", "Password1"));
	}
	@Test
	public void existingEmployeeBadPass() {
		EmployeeAdminLogic eaLogic = new EmployeeAdminLogic();
		assertNull(eaLogic.existingEmployeeCheck("TestEmployee", "Wrong"));
	}

	/**
	 * ******************************SETAPPROVAL*****************************
	 */
	@Test
	public void setApprovalTrue() {
		CustomerLogic cLogic = new CustomerLogic();
		EmployeeAdminLogic eaLogic = new EmployeeAdminLogic();
		Customer startFalse = cLogic.grabWholeCustomer("TestUserFalse", "Password1");
		assertTrue(eaLogic.setApproval(startFalse, 1));
	}
	@Test
	public void setApprovalFalse() {
		CustomerLogic cLogic = new CustomerLogic();
		EmployeeAdminLogic eaLogic = new EmployeeAdminLogic();
		Customer startTrue = cLogic.grabWholeCustomer("TestUserTrue", "Password1");
		assertFalse(eaLogic.setApproval(startTrue, 2));
	}
	
	/**
	 * ***************OPENANACCOUNTCHECK*****************************
	 */
	@Test
	public void openAcctGood() {
		CustomerLogic cLogic = new CustomerLogic();
		EmployeeAdminLogic eaLogic = new EmployeeAdminLogic();
		Customer startTrue = cLogic.grabWholeCustomer("TestUserTrue", "Password1");
		assertTrue(eaLogic.openAnAccountCheck(startTrue, 3000));
	}
	
	@Test
	public void openAcctNO() {
		CustomerLogic cLogic = new CustomerLogic();
		EmployeeAdminLogic eaLogic = new EmployeeAdminLogic();
		Customer startTrue = cLogic.grabWholeCustomer("TestUserTrue", "Password1");
		assertFalse(eaLogic.openAnAccountCheck(startTrue, 13));
	}
	
	
	
	
	
	

}
