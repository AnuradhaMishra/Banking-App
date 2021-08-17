package com.projectzero.bms.App;

// CREATE MAIN SCREEN LAYOUT HERE

import java.util.List;
import java.util.Scanner;

import com.projectzero.bms.Login.LoginCustomer;
import com.projectzero.bms.Login.LoginEmployee;

public class BankApp {

	int choice = 0;
	char type;
	Scanner sc = new Scanner(System.in);

	public void startBankingApp() {

		while (true) {
			System.out.println("\t\t\t#$$$#$$$#$$$#$$$# ANURADHA BANK OF GHAZIABAD #$$$#$$$#$$$#$$$#\n\n");

			System.out.println("\t\t\t\t##### M E N U #####\n\n");
			System.out.println("1. Login");
			System.out.println("2. Apply for an Account");
			System.out.println("3. Know about this Application");
			System.out.println("4. Know about Developer");
			System.out.println("9. Exit");

			System.out.println("Enter your choice :- ");
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				
				type = 'A';
				while ((type != 'C' && type != 'c') && (type != 'E' && type != 'e')) {
					System.out.println("\t\t\t\t##### L O G I N   P R O M P T #####\n\n");

					System.out.println("Please enter the type of your Account (C/E) : ");
					type = sc.next().charAt(0);

					if (type == 'C' || type == 'c') {

						LoginCustomer loginCustomer = new LoginCustomer();
						loginCustomer.loginCustomerDisplay();
						break;
					} else if (type == 'E' || type == 'e') {
						LoginEmployee loginEmployee = new LoginEmployee();
						loginEmployee.loginEmployeeDisplay();
					} else {
						System.out.println("Please enter valid input!!!");
					}
				}
				break;
			case 2:
				type = 'A';
				while ((type != 'C' && type != 'c') && (type != 'E' && type != 'e')) {
					System.out.println("\t\t\t\t##### C R E A T E   A N   A C C O U N T   P R O M P T #####\n\n");

					System.out.println("Please enter the type of your Account (C/E) : ");
					type = sc.next().charAt(0);
					ApplyAccounts applyaccount = new ApplyAccounts();
					if (type == 'C' || type == 'c') {
						applyaccount.applyCustomer();

					} else if (type == 'E' || type == 'e') {
						applyaccount.applyEmployee();

					} else {
						System.out.println("Please enter valid input!!!");
					}
				}
				break;
			case 3:
				KnowApp knowApp = new KnowApp();
				knowApp.appDetails();
				break;
			case 4:
				KnowDev knowDev = new KnowDev();
				knowDev.devDetails();
				break;
			case 9:
				System.out.println("##Thanks for using this Banking app");
				System.exit(0);
				break;
			}
		}
	}
}
