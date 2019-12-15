package com.revature.repositories;

import com.revature.models.Customer;

public interface CustomersDAO {

	public Customer getCustomerByUserName(String userName, String password);
	
	public Customer getCustomerByUserNameOnly(String userName);
	
	public boolean updateCustomer(Customer customer);
	
	public boolean customerExists(String userName);
	
	public boolean createCustomer(Customer customer);
	
}
