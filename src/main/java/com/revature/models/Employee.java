package com.revature.models;

public class Employee extends User{

	public boolean isAdmin = false;
	
	// constructors
	public Employee(){}
	
	public Employee(String username, String password) {
		this.setUserName(username);
		this.setPassword(password);
	}
	public Employee(String username) {
		this.setUserName(username);
	}
	public Employee(String username, String password, String fName, String lname) {
		this.setUserName(username);
		this.setPassword(password);
		this.setFirstName(fName);
		this.setLastName(lname);
	}

	@Override
	public String toString() {
		return "Employee [isAdmin=" + isAdmin + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (isAdmin ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (isAdmin != other.isAdmin)
			return false;
		return true;
	}
	
	
	
}
