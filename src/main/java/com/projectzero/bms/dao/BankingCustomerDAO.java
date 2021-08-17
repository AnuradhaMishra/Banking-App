package com.projectzero.bms.dao;

import java.util.List;

import com.projectzero.bms.model.BankingCustomer;

public interface BankingCustomerDAO {
	public boolean addCustomer(BankingCustomer customer);
	public int viewCustomerBalance(int customerId);
	public String checkCustomerPassword(int customerId);
	public void transferBalance(int senderId, int receiverId,int amount);
	public int viewCustomerId(int customerId);
	public boolean depositAmount(int customerId,int amount);
	public boolean withdrawAmount(int customerId,int amount);
	public boolean applyCustomer(BankingCustomer customer);
	public boolean deleteCustomer(int customerId);


}
