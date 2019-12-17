package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.models.Admin;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeesAdminDAOImpl implements EmployeesAdminDAO{

	private static Logger logger = Logger.getLogger(EmployeesAdminDAOImpl.class);
	
	@Override
	public boolean employeeExists(String userName) {
		Employee e = new Employee();
		e.setUserName(userName);
		try (Connection conn = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM project0.employeesadmins WHERE username = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String dbUserName = rs.getString("username");
				boolean exists = false;
				if (userName.equals(dbUserName)) {
					exists = true;
					return exists;
				} else {
					return exists;
				}
			}
		} catch (SQLException ex) {
			logger.warn("Unable to verify if employee exists", ex);
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public Employee getEmployee(String userName, String password) {

		try (Connection conn = ConnectionUtil.getConnection()) {

			// prepared statement
			String sql = "SELECT * FROM project0.employeesadmins WHERE username = ? AND userpassword = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String dbUserName = rs.getString("username");
				String userPassword = rs.getString("userpassword");
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				boolean admin = rs.getBoolean("isadmin");

				if (admin) {
					rs.close();
					return new Admin(dbUserName, userPassword, firstName, lastName);
				} else {
					rs.close();
					return new Employee(dbUserName, userPassword, firstName, lastName);
				}
				 
			}
			rs.close();
		} catch (SQLException e) {
			logger.warn("Unable to get Employee from database", e);
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public Employee getEmployee(String userName) {

		try (Connection conn = ConnectionUtil.getConnection()) {

			// prepared statement
			String sql = "SELECT * FROM project0.employeesadmins WHERE username = ?;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, userName);
			

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String dbUserName = rs.getString("username");
				String userPassword = rs.getString("userpassword");
				String firstName = rs.getString("firstname");
				String lastName = rs.getString("lastname");
				boolean admin = rs.getBoolean("isadmin");

				if (admin) {
					rs.close();
					return new Admin(dbUserName, userPassword, firstName, lastName);
				} else {
					rs.close();
					return new Employee(dbUserName, userPassword, firstName, lastName);
				}
				 
			}
			rs.close();
		} catch (SQLException e) {
			logger.warn("Unable to get Employee from database", e);
			e.printStackTrace();
		}
		return null;
	}

}
