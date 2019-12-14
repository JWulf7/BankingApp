package com.revature.repositories;

import com.revature.models.Employee;

public interface EmployeesAdminDAO {

	public boolean employeeExists(String userName);
	
	public Employee getEmployee(String userName, String password);
	
}
