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
import org.junit.Ignore;
import org.junit.Test;

import com.revature.models.BankAccount;
import com.revature.repositories.TransactionsDAOImpl;
import com.revature.util.ConnectionUtil;

public class AccountsLogicTest {

	

	@Before
	public void setUp() throws Exception {
		Logger logger = Logger.getLogger(AccountsLogicTest.class);
		
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
			logger.warn("Unable to establish connection for testing AcountsLogic", e);
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
		
Logger logger = Logger.getLogger(AccountsLogicTest.class);
		
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "DELETE from project0.bankaccounts where accountnumber = 200;" +
						"DELETE from project0.bankaccounts where accountnumber = 100;" +
						"DELETE from project0.customers WHERE username = 'TestUser';";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.execute();
			
		} catch (SQLException e) {
			logger.warn("Unable to establish connection for testing AcountsLogic", e);
			e.printStackTrace();
		}
	}

	/**
	 * ******************************DEPOSIT**************************************
	 */
	@Test
	public void testDepositNegative() {
		AccountsLogic aLogic = new AccountsLogic();
		Logger logger = Logger.getLogger(AccountsLogicTest.class);
		double accountBal;
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "SELECT accountbalance FROM project0.bankaccounts WHERE accountnumber = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 100);

			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				accountBal = rs.getDouble("accountbalance");
				BankAccount account = new BankAccount();
				account.setAccountBalance(accountBal);
				assertEquals(0,aLogic.deposit(account, -500.98), 0);
			}
			rs.close();		
		} catch (SQLException e) {
			logger.warn("Unable to establish connection for testing AcountsLogic", e);
			e.printStackTrace();
		}
	}

	
	
	
	@Test
	public void testDepositDecimal() {
		AccountsLogic aLogic = new AccountsLogic();
		Logger logger = Logger.getLogger(AccountsLogicTest.class);
		double accountBal;
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "SELECT accountbalance FROM project0.bankaccounts WHERE accountnumber = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 100);

			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				accountBal = rs.getDouble("accountbalance");
				BankAccount account = new BankAccount();
				account.setAccountBalance(accountBal);
				assertEquals(.25,aLogic.deposit(account, 0.2555555), .25);
			}
			rs.close();
		} catch (SQLException e) {
			logger.warn("Unable to establish connection for testing AcountsLogic", e);
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void testDepositSuccessful() {
		AccountsLogic aLogic = new AccountsLogic();
		Logger logger = Logger.getLogger(AccountsLogicTest.class);
		double accountBal;
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "SELECT accountbalance FROM project0.bankaccounts WHERE accountnumber = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 100);

			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				accountBal = rs.getDouble("accountbalance");
				BankAccount account = new BankAccount();
				account.setAccountBalance(accountBal);
				assertEquals(80,aLogic.deposit(account, 80), 80);
			}
			rs.close();
		} catch (SQLException e) {
			logger.warn("Unable to establish connection for testing AcountsLogic", e);
			e.printStackTrace();
		}
	}
	
	/**
	 ****************************************WITHDRAW********************************
	 */
	@Test
	public void testWithdrawNegative() {
		AccountsLogic aLogic = new AccountsLogic();
		Logger logger = Logger.getLogger(AccountsLogicTest.class);
		double accountBal;
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "SELECT accountbalance FROM project0.bankaccounts WHERE accountnumber = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 100);

			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				accountBal = rs.getDouble("accountbalance");
				BankAccount account = new BankAccount();
				account.setAccountBalance(accountBal);
				assertEquals(0,aLogic.withdraw(account, -500.98), 0);
			}
			rs.close();		
		} catch (SQLException e) {
			logger.warn("Unable to establish connection for testing AcountsLogic", e);
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testWithdrawMinimum() {
		AccountsLogic aLogic = new AccountsLogic();
		Logger logger = Logger.getLogger(AccountsLogicTest.class);
		double accountBal;
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "SELECT accountbalance FROM project0.bankaccounts WHERE accountnumber = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 100);

			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				accountBal = rs.getDouble("accountbalance");
				BankAccount account = new BankAccount();
				account.setAccountBalance(accountBal);
				assertEquals(0,aLogic.withdraw(account, 999999), 0);
			}
			rs.close();		
		} catch (SQLException e) {
			logger.warn("Unable to establish connection for testing AcountsLogic", e);
			e.printStackTrace();
		}
	}
	
	@Test
	public void testWithdrawSuccessful() {
		AccountsLogic aLogic = new AccountsLogic();
		Logger logger = Logger.getLogger(AccountsLogicTest.class);
		double accountBal;
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "SELECT accountbalance FROM project0.bankaccounts WHERE accountnumber = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 100);

			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				accountBal = rs.getDouble("accountbalance");
				BankAccount account = new BankAccount();
				account.setAccountBalance(accountBal);
				assertEquals(150,aLogic.withdraw(account, 150), 150);
			}
			rs.close();		
		} catch (SQLException e) {
			logger.warn("Unable to establish connection for testing AcountsLogic", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * ******************************TRANSFER********************************************
	 */
	@Test
	public void testTransferNegative() {
		AccountsLogic aLogic = new AccountsLogic();
		Logger logger = Logger.getLogger(AccountsLogicTest.class);
		double accountBal;
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "SELECT accountbalance FROM project0.bankaccounts WHERE accountnumber = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 100);
			BankAccount otherAccount = new BankAccount();
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				accountBal = rs.getDouble("accountbalance");
				BankAccount account = new BankAccount();
				account.setAccountBalance(accountBal);
				assertEquals(0,aLogic.transfer(account, -45, otherAccount), 0);
			}
			rs.close();		
		} catch (SQLException e) {
			logger.warn("Unable to establish connection for testing AcountsLogic", e);
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testTransferMin() {
		AccountsLogic aLogic = new AccountsLogic();
		Logger logger = Logger.getLogger(AccountsLogicTest.class);
		double accountBal;
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "SELECT accountbalance FROM project0.bankaccounts WHERE accountnumber = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 100);
			BankAccount otherAccount = new BankAccount();
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				accountBal = rs.getDouble("accountbalance");
				BankAccount account = new BankAccount();
				account.setAccountBalance(accountBal);
				assertEquals(0,aLogic.transfer(account, 999999, otherAccount), 0);
			}
			rs.close();		
		} catch (SQLException e) {
			logger.warn("Unable to establish connection for testing AcountsLogic", e);
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTransferValid() {
		AccountsLogic aLogic = new AccountsLogic();
		Logger logger = Logger.getLogger(AccountsLogicTest.class);
		double accountBal;
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "SELECT accountbalance FROM project0.bankaccounts WHERE accountnumber = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, 100);
			BankAccount otherAccount = new BankAccount();
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				accountBal = rs.getDouble("accountbalance");
				BankAccount account = new BankAccount();
				account.setAccountBalance(accountBal);
				assertEquals(200,aLogic.transfer(account, 200, otherAccount), 200);
			}
			rs.close();		
		} catch (SQLException e) {
			logger.warn("Unable to establish connection for testing AcountsLogic", e);
			e.printStackTrace();
		}
	}
	
}
