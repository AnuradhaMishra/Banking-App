package com.projectzero.bms.model;

import java.io.Serializable;

public class BankingCustomer implements Serializable {

	//FIELDS OF CUSTOMER DETAILS
	private int customerId ;
	private String customerName ;
	private String password ;
	private int customerBalance ;
	
	//DEFAULT CONSTRUCTOR
	public BankingCustomer() {
		// TODO Auto-generated constructor stub
	}

	//PARAMETERIZED CONSTRUCTOR
	public BankingCustomer(int customerId, String customerName, String password, int customerBalance) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.password = password;
		this.customerBalance = customerBalance;
	}

	//GETTERS AND SETTERS - GENERATED
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCustomerBalance() {
		return customerBalance;
	}

	public void setCustomerBalance(int customerBalance) {
		this.customerBalance = customerBalance;
	}

	//ToString
	@Override
	public String toString() {
		return "\ncustomerId=" + customerId + ", customerName=" + customerName +
				", customerBalance=" + customerBalance;
	}

	
	//HASHCODES AND EQUALS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + customerBalance;
		result = prime * result + customerId;
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
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
		BankingCustomer other = (BankingCustomer) obj;
		if (customerBalance != other.customerBalance)
			return false;
		if (customerId != other.customerId)
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	
}
