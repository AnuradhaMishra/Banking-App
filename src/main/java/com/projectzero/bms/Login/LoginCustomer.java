package com.projectzero.bms.Login;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.projectzero.bms.App.BankApp;
import com.projectzero.bms.dao.*;
import com.projectzero.bms.model.*;

public class LoginCustomer extends LoginCustomerPrompt {

	int choice = 0;
	Scanner sc = new Scanner(System.in);
	BankingCustomerDAO BankingCustomerDAO = new BankingCustomerDAOImpl();
	boolean result;
	BankingCustomer BankingCustomer = new BankingCustomer();
	private static Logger transactionLog = Logger.getLogger("debugLogger");
	private static Logger eventsLog = Logger.getLogger("reportsLogger");

	public void loginCustomerDisplay() {

		while (true) {
			int customerId = super.customerIdPrompt();
			String password = super.customerPasswordPrompt();
			String customerPassword = BankingCustomerDAO.checkCustomerPassword(customerId);
			if (password.equals(customerPassword)) {
				while (true) {
					System.out.println("\t\t\t\t##### C U S T O M E R   L O G I N #####\n");

					System.out.println("1. View Balance");
					System.out.println("2. Transfer Amount");
					System.out.println("3. Deposit Amount");
					System.out.println("4. Withdraw Amount");
					System.out.println("5. Logout");
					System.out.println("9. Exit");

					System.out.println("Enter your choice : ");
					choice = sc.nextInt();

					switch (choice) {
					case 1:
						int customerBalance = BankingCustomerDAO.viewCustomerBalance(customerId);
						System.out.println("Your A/c Balance is : " + customerBalance);
						break;
					case 2:
						System.out.println("Please enter the Id to whom you want to transfer money:- ");
						int receiver = sc.nextInt();
						System.out.println("Please Enter the amount:- ");
						int amount = sc.nextInt();
						int customerBal = BankingCustomerDAO.viewCustomerBalance(customerId);
						int checkCustomerId = BankingCustomerDAO.viewCustomerId(receiver);
						if (customerBal >= amount && amount > 0 && checkCustomerId == receiver) {
							BankingCustomerDAO.transferBalance(customerId, receiver, amount);
						} else {
							transactionLog.trace(customerId + " transfer of INR " + amount + " to " + receiver + " on "
									+ new Date() + " was failed");

							if (checkCustomerId != receiver) {
								System.out.println("Invalid Id! Id does not exist");
							} else if (amount <= 0) {
								System.out.println("Invalid Amount!");
							} else
								System.out.println("Insufficient Balance");
						}
						break;
					case 3:
						System.out.println("Enter amount to deposit:- ");
						int depositAmount = sc.nextInt();
						if (depositAmount > 0) {

							boolean checkDeposit = BankingCustomerDAO.depositAmount(customerId, depositAmount);
							if (checkDeposit == true) {
								System.out.println("INR " + depositAmount + " Deposited Successfully");
								transactionLog.trace(customerId + " deposit of INR " + depositAmount + " on "
										+ new Date() + " was successful");

							} else {
								System.out.println("Some Error occurred please try again");
								transactionLog.trace(customerId + " deposit of INR " + depositAmount + " on "
										+ new Date() + " was failed");

							}
						} else {
							System.out.println("Please enter valid Amount to deposit:-");
							transactionLog.trace(customerId + " deposit of INR " + depositAmount + " on " + new Date()
									+ " was failed");

						}
						break;
					case 4:
						System.out.println("Enter the amount to withdraw:- ");
						int withdrawAmount = sc.nextInt();
						int customerBal1 = BankingCustomerDAO.viewCustomerBalance(customerId);

						if (withdrawAmount > 0&& withdrawAmount<=customerBal1) {
							boolean checkWithdraw = BankingCustomerDAO.withdrawAmount(customerId, withdrawAmount);
							if (checkWithdraw == true) {
								System.out.println("INR " + withdrawAmount + " Withdrawn Successfully");
								transactionLog.trace(customerId + " withdraw of INR " + withdrawAmount + " on "
										+ new Date() + " was successful");
							} else {
								System.out.println("Some Error occurred please try again");
								transactionLog.trace(customerId + " withdraw of INR " + withdrawAmount + " on "
										+ new Date() + " was failed");

							}

						} else {
							System.out.println("Please enter valid Amount to deposit:-");
							transactionLog.trace(customerId + " withdraw of INR " + withdrawAmount + " on " + new Date()
									+ " was failed");

						}
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

	public static void main(String[] args) {
		LoginCustomer lc = new LoginCustomer();
		lc.loginCustomerDisplay();
	}
}
