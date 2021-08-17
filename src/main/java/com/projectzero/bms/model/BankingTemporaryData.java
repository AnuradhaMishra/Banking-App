package com.projectzero.bms.model;

import java.io.Serializable;

public class BankingTemporaryData implements Serializable {
	private int id ;
	private String name ;
	private String password ;
	private int balance ;
	private String type;
	
	public BankingTemporaryData()
	{
		
	}
	
	public BankingTemporaryData(int id, String name, String password, int balance, String type) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.balance = balance;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getBalance() {
		return balance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "BankingTemporaryData [id=" + id + ", name=" + name + ", password=" + password + ", balance=" + balance
				+ ", type=" + type + "]";
	}

	
	
	
	

}
