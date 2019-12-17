package com.revature.services;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.models.BankAccount;
import com.revature.models.Customer;
import com.revature.util.ConnectionUtil;

public class CustomerLogicTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "INSERT into project0.customers (username, userpassword, firstname, lastname, address, approved) " +
						"VALUES ('TestUser', 'Password1', 'Larry', 'Berry', '123 Road', true);" +
						"INSERT into project0.bankaccounts (accountnumber, accountbalance, usernam) " +
						"VALUES (100, 1000, 'TestUser');" +
						"INSERT into project0.bankaccounts (accountnumber, accountbalance, usernam) " +
						"VALUES (200, 1000, 'TestUser');";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.execute();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown() throws Exception {
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "DELETE from project0.bankaccounts where accountnumber = 200;" +
						"DELETE from project0.bankaccounts where accountnumber = 100;" +
						"DELETE from project0.customers WHERE username = 'TestUser';";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ************************EXISTING CUSTOMER CHECK*************
	 */

	@Test
	public void testExistingCustomerCheckUsername() {
		CustomerLogic cLogic = new CustomerLogic();
		
		Customer customer = new Customer();
		String userName;
		String userPassword;
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "SELECT username, userpassword FROM project0.customers WHERE username = ? and userpassword = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "TestUser");
			stmt.setString(2, "Password1");

			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				userName = rs.getString("username");
				userPassword = rs.getString("userpassword");
				customer.setUserName(userName);
				customer.setPassword(userPassword);
				
			}
			
			assertNotNull(cLogic.existingCustomerCheck(customer, "TestUser", "Password1"));
			rs.close();		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	/**
	 * ***************************************ACCEPTABLEFIRSTNAME****************
	 */
	@Test
	public void testFirstNameEmpty() {
		CustomerLogic cLogic = new CustomerLogic();
		assertFalse(cLogic.acceptableFirstName(""));
	}
	
	@Test
	public void testFirstNameLong() {
		CustomerLogic cLogic = new CustomerLogic();
		assertFalse(cLogic.acceptableFirstName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
	}
	
	@Test
	public void testFirstNameOK() {
		CustomerLogic cLogic = new CustomerLogic();
		assertTrue(cLogic.acceptableFirstName("Fred"));
	}
	
	/**
	 * ***************************************ACCEPTABLELASTNAME****************
	 */
	@Test
	public void testLastNameEmpty() {
		CustomerLogic cLogic = new CustomerLogic();
		assertFalse(cLogic.acceptableLastName(""));
	}
	
	@Test
	public void testLastNameLong() {
		CustomerLogic cLogic = new CustomerLogic();
		assertFalse(cLogic.acceptableLastName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
	}
	
	@Test
	public void testLastNameOK() {
		CustomerLogic cLogic = new CustomerLogic();
		assertTrue(cLogic.acceptableLastName("Flinstone"));
	}

	/**
	 * ***************************************ACCEPTABLEADDRESS****************
	 */
	@Test
	public void testAddressEmpty() {
		CustomerLogic cLogic = new CustomerLogic();
		assertFalse(cLogic.acceptableAddress(""));
	}
	
	@Test
	public void testAddressLong() {
		CustomerLogic cLogic = new CustomerLogic();
		assertFalse(cLogic.acceptableAddress("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
				+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
	}
	
	@Test
	public void testAddressOK() {
		CustomerLogic cLogic = new CustomerLogic();
		assertTrue(cLogic.acceptableAddress("12345 Prehistoric Way, Jurassic Park"));
	}
	
}
