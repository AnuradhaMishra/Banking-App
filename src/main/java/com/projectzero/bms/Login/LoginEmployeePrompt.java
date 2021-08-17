package com.projectzero.bms.Login;

import java.util.Scanner;
import com.projectzero.bms.dao.*;
import com.projectzero.bms.model.*;

public class LoginEmployeePrompt {

	Scanner sc = new Scanner(System.in);

	public int employeeIdPrompt() {
		int employeeId;
		System.out.println("Enter Employee ID :- ");
		employeeId = sc.nextInt();
		return employeeId;
	}

	public String employeePasswordPrompt() {
		String password;
		System.out.println("Enter your Password :- ");
		password = sc.next();
		return password;
	}

	public static void main(String[] args) {
		// LoginCustomerPrompt c = new LoginCustomerPrompt();
		// c.customerPrompt();
	}
}
