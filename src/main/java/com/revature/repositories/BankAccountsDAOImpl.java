package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.revature.models.BankAccount;
import com.revature.models.Customer;
import com.revature.util.ConnectionUtil;

public class BankAccountsDAOImpl implements BankAccountsDAO{
	
	private static Logger logger = Logger.getLogger(BankAccountsDAOImpl.class);

	@Override
	public BankAccount getAccountFromNum(int accountNumber) {

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM project0.bankaccounts WHERE accountnumber = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, accountNumber);

			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int accountNum = rs.getInt("accountnumber");
				double accountBal = rs.getDouble("accountbalance");
				String accountOwner = rs.getString("usernam");
				
				
				BankAccount bankAccount = new BankAccount(accountNum, accountBal, accountOwner);
				rs.close();
				return bankAccount;
			}
			rs.close();
			
		} catch (SQLException e) {
			logger.warn("Unable to get bank account", e);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public TreeMap<Integer, BankAccount> getAccountFromUser(String userName) {
		TreeMap<Integer, BankAccount> accounts = new TreeMap<>();
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM project0.bankaccounts WHERE usernam = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);

			
			ResultSet rs = stmt.executeQuery();
			int i = 1;
			while(rs.next()) {
				int accountNum = rs.getInt("accountnumber");
				double accountBal = rs.getDouble("accountbalance");
				String accountOwner = rs.getString("usernam");
				
				BankAccount bankAccount = new BankAccount(accountNum, accountBal, accountOwner);
				accounts.put(i, bankAccount);
				i++;
				
//				rs.close();
//				return accounts;
			}
			rs.close();
			
		} catch (SQLException e) {
			logger.warn("Unable to get User's Bank Accounts", e);
			e.printStackTrace();
		}
		return accounts;
		
	}

	@Override
	public boolean updateAccount(int accountNumber, double accountBalance) {

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "UPDATE project0.bankaccounts SET accountbalance = ? WHERE accountnumber = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, accountBalance);
			stmt.setInt(2, accountNumber);
			return stmt.execute();

		} catch (SQLException e) {
			logger.warn("Unable to update customer information", e);
			e.printStackTrace();
		}
		return false;

	}

	@Override	
	public boolean createAccount(String userName) {

		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql ="INSERT into project0.bankaccounts (usernam) " +
						"VALUES (?);";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			return stmt.execute();
			
			
			
		
			
		} catch (SQLException e) {
			logger.warn("Unable to open new account", e);
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public BankAccount getNewAccount(String userName) {
		
		try (Connection conn = ConnectionUtil.getConnection()) {

			
			String sql = "SELECT accountnumber FROM project0.bankaccounts WHERE usernam = ? AND accountbalance = 0;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			
			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int accountNum = rs.getInt("accountnumber");
				
				
				BankAccount bankAccount = new BankAccount(accountNum, 0, userName);
				rs.close();
				return bankAccount;
			}
			rs.close();
			
		} catch (SQLException e) {
			logger.warn("Unable to retrieve new account", e);
			e.printStackTrace();
		}
		System.out.println("Unable to retrieve new account.");
		return null;
	}
	
	
	@Override
	public boolean deleteAccount(int accountNumber) {
		
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "DELETE FROM project0.bankaccounts WHERE accountnumber = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, accountNumber);
			return stmt.execute();

		} catch (SQLException e) {
			logger.warn("Unable to delete customer bank account on database", e);
			e.printStackTrace();
		}
		return false;
	}
	
	

}
