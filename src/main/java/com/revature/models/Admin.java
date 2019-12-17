package com.revature.models;

public class Admin extends Employee{

	
	public boolean isAdmin = true;

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public Admin(String username, String password, String fname, String lname) {
		this.setUserName(username);
		this.setPassword(password);
		this.setFirstName(fname);
		this.setLastName(lname);
	}

	@Override
	public String toString() {
		return "Admin [isAdmin=" + isAdmin + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isAdmin ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		if (isAdmin != other.isAdmin)
			return false;
		return true;
	}
	
	
}
