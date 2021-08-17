package com.projectzero.bms.dao;

import java.util.List;

import com.projectzero.bms.model.BankingCustomer;
import com.projectzero.bms.model.BankingEmployee;

public interface BankingEmployeeDAO {
	public boolean addEmployee(BankingEmployee employee);
	public boolean addCustomer(BankingCustomer customer);
	public List<BankingCustomer> getAllCustomer();
	public String checkEmployeePassword(int employeeId);
	public boolean applyEmployee(BankingEmployee employee);
	public void approveDenyRequest();
}
