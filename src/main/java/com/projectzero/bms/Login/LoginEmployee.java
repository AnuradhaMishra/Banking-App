package com.projectzero.bms.Login;

import java.util.List;
import java.util.Scanner;

import com.projectzero.bms.App.BankApp;
import com.projectzero.bms.App.RegisterCustomer;
//import com.projectzero.bms.dao.BankingEmployeeDAOImpl;
import com.projectzero.bms.dao.*;
import com.projectzero.bms.model.*;

public class LoginEmployee extends LoginEmployeePrompt {

	int choice = 0;
	Scanner sc = new Scanner(System.in);
	BankingEmployeeDAO BankingEmployeeDAO = new BankingEmployeeDAOImpl();
	boolean result;
	BankingEmployee BankingEmployee = new BankingEmployee();

	public void loginEmployeeDisplay() {

		while (true) {
			int employeeId = super.employeeIdPrompt();
			String password = super.employeePasswordPrompt();
			String employeePassword = BankingEmployeeDAO.checkEmployeePassword(employeeId);
			//System.out.println(employeePassword);
			if (password.equals(employeePassword)) {
				while (true) {
					
					System.out.println("\n\t\t\t\t##### E M P L O Y E E   L O G I N #####\n");

					System.out.println("1. Approve/Deny");
					System.out.println("2. View logs of all transaction");
					System.out.println("3. View all customer's Balance");
					System.out.println("4. Register Customer Account");
					System.out.println("5. Logout");
					System.out.println("9. Exit");

					System.out.println("Enter your choice : ");
					choice = sc.nextInt();

					switch (choice) {
					case 1:
						BankingEmployeeDAO.approveDenyRequest();
						

						break;
					case 2:
						ViewTransactionLogs viewTransaction = new ViewTransactionLogs();
						viewTransaction.viewTransactions();
						break;
					case 3:
						List<BankingCustomer> allCustomers = BankingEmployeeDAO.getAllCustomer();
						System.out.println(allCustomers);
						break;
					case 4:
						RegisterCustomer registerCustomer = new RegisterCustomer();
						registerCustomer.registerCustomerByEmployee();
						break;
					case 5:
						BankApp bankapp = new BankApp();
						bankapp.startBankingApp();
						break;
					case 9:
						System.out.println("##Thanks for using my Banking App");
						System.exit(0);
						break;
					}

				}
			} else {
				System.out.println("Invalid username or password.\nPlease enter correct username and password.\n\n");
			}
		}
	}

	public BankingEmployee acceptBankingEmployeeDetails() {
		System.out.println("Please enter Employee id : ");
		int EmployeeId = sc.nextInt();
		System.out.println("Please enter Employee name : ");
		String EmployeeName = sc.next();
		System.out.println("Please enter Password : ");
		String password = sc.next();
		

		BankingEmployee BankingEmployee = new BankingEmployee(EmployeeId, EmployeeName, password);
		return BankingEmployee;
	}

}
