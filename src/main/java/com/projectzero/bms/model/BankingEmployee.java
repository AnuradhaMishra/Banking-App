package com.projectzero.bms.model;

import java.io.Serializable;

public class BankingEmployee implements Serializable {

	//FIELDS OF employee DETAILS
	private int employeeId ;
	private String employeeName ;
	private String password ;
	
	//DEFAULT CONSTRUCTOR
	public BankingEmployee() {
		// TODO Auto-generated constructor stub
	}

	//PARAMETERIZED CONSTRUCTOR
	public BankingEmployee(int employeeId, String employeeName, String password) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.password = password;
	}

	//GETTERS AND SETTERS - GENERATED
	public int getemployeeId() {
		return employeeId;
	}

	public void setemployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getemployeeName() {
		return employeeName;
	}

	public void setemployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	//ToString
	@Override
	public String toString() {
		return "Bankingemployee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", password=" + password
				+ "]";
	}

	
	//HASHCODES AND EQUALS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + employeeId;
		result = prime * result + ((employeeName == null) ? 0 : employeeName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		BankingEmployee other = (BankingEmployee) obj;
		if (employeeId != other.employeeId)
			return false;
		if (employeeName == null) {
			if (other.employeeName != null)
				return false;
		} else if (!employeeName.equals(other.employeeName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	
}
