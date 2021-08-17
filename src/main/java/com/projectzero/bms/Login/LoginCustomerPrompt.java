package com.projectzero.bms.Login;

import java.util.Scanner;
import com.projectzero.bms.dao.*;
import com.projectzero.bms.model.*;

public class LoginCustomerPrompt {

	Scanner sc = new Scanner(System.in);

	public int customerIdPrompt() {
		int customerId;
		System.out.println("Enter Customer ID :- ");
		customerId = sc.nextInt();
		return customerId;
	}

	public String customerPasswordPrompt() {
		String password;
		System.out.println("Enter your Password :- ");
		password = sc.next();
		return password;
	}

	
}
