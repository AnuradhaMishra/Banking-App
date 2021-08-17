package com.projectzero.bms.App;

import java.util.Scanner;

import com.projectzero.bms.dao.BankingCustomerDAO;
import com.projectzero.bms.dao.BankingCustomerDAOImpl;
import com.projectzero.bms.dao.BankingEmployeeDAO;
import com.projectzero.bms.dao.BankingEmployeeDAOImpl;
import com.projectzero.bms.model.BankingCustomer;
import com.projectzero.bms.model.BankingEmployee;

public class ApplyAccounts {
	
	boolean result;
	BankingCustomerDAO bankingCustomerDAO = new BankingCustomerDAOImpl();
	BankingEmployeeDAO bankingEmployeeDAO = new BankingEmployeeDAOImpl();

	Scanner sc = new Scanner(System.in);
	BankingCustomer bankingcustomer = new BankingCustomer();
	BankingEmployee bankingemployee = new BankingEmployee();

	
	public void applyCustomer() {
		bankingcustomer = applyCustomerDetails();
		if(bankingcustomer.getCustomerBalance() >= 0) {
		result = bankingCustomerDAO.applyCustomer(bankingcustomer);
		if(result) {
			System.out.println("Account with customer name : " + bankingcustomer.getCustomerName() + " and Customer Id : " + bankingcustomer.getCustomerId() + " has been saved successfully.");
		}
		else
		{
			System.out.println("Account with customer name : " + bankingcustomer.getCustomerName() + " and Customer Id : " + bankingcustomer.getCustomerId() + " has not been saved successfully.");
		}
		}
		else {
			System.out.println("Invalid Amount! Amount cannot be less than Zero");
		}
	}

	public BankingCustomer applyCustomerDetails() {
		System.out.println("Enter the CustomerId:- ");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the Customer Name:- ");
		String name = sc.nextLine();
		System.out.println("Enter the Password:- ");
		String password = sc.nextLine();
		System.out.println("Enter the Starting Balance:- ");
		int balance = sc.nextInt();
		
		BankingCustomer bankingcustomer = new BankingCustomer(id,name,password,balance);
		return bankingcustomer;
		
		
		


		
	}
	
	public void applyEmployee() {
		bankingemployee = applyEmployeeDetails();
		
		result = bankingEmployeeDAO.applyEmployee(bankingemployee);
		if(result) {
			System.out.println("Account with employee name : " + bankingemployee.getemployeeName() + " and Employee Id : " + bankingemployee.getemployeeId() + " has been saved successfully.");
		}
		else
		{
			System.out.println("Account with employee name : " + bankingemployee.getemployeeName() + " and Employee Id : " + bankingemployee.getemployeeId() + " has not been saved successfully.");
		}
		
	}

	public BankingEmployee applyEmployeeDetails() {
		System.out.println("Enter the Employee Id:- ");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the Employee Name:- ");
		String name = sc.nextLine();
		System.out.println("Enter the Password:- ");
		String password = sc.nextLine();
		
		
		BankingEmployee bankingemployee = new BankingEmployee(id,name,password);
		return bankingemployee;
		
		
		


		
	}



}
