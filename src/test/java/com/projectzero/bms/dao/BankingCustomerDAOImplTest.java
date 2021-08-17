package com.projectzero.bms.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.projectzero.bms.model.BankingCustomer;
public class BankingCustomerDAOImplTest {
	private BankingCustomerDAO bankingCustomerdao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		bankingCustomerdao = new BankingCustomerDAOImpl();
	}

	@After
	public void tearDown() throws Exception {
		bankingCustomerdao = null;
	}

	@Test
	public void testAddCustomer() {
		// Testing adding a customer
				int customerIdToTest = 90;

				BankingCustomer addedcustomer = new BankingCustomer(customerIdToTest, "DemoCustomer", "abc",120);
				bankingCustomerdao.addCustomer(addedcustomer);

				int retrievedCustomer = bankingCustomerdao.viewCustomerId(90);

				assertEquals(customerIdToTest, retrievedCustomer);

				// deleting the product after testing
				bankingCustomerdao.deleteCustomer(customerIdToTest);
	}

	
	@Test
	public void testViewCustomerBalance() {
		fail("Not yet implemented");
	}

	@Test
	public void testTransferBalance() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckCustomerPassword() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewCustomerId() {
		int customerIdToTest = -999;
		BankingCustomer addedcustomer = new BankingCustomer(customerIdToTest, "DemoCustomer", "abc",120);
		bankingCustomerdao.addCustomer(addedcustomer);
		int originalCustomer = bankingCustomerdao.viewCustomerId(customerIdToTest);

		assertEquals(customerIdToTest, originalCustomer);

		bankingCustomerdao.deleteCustomer(customerIdToTest);	}

	@Test
	public void testDepositAmount() {
		int customerIdToTest = 90;
		int amountToTest =100;

		BankingCustomer addedcustomer = new BankingCustomer(customerIdToTest, "DemoCustomer", "abc",120);
		bankingCustomerdao.addCustomer(addedcustomer);
		bankingCustomerdao.depositAmount(customerIdToTest, amountToTest);
		int retrievedCustomer = bankingCustomerdao.viewCustomerBalance(90);

		assertEquals(amountToTest+120, retrievedCustomer);

		// deleting the product after testing
		bankingCustomerdao.deleteCustomer(customerIdToTest);
		}

	@Test
	public void testWithdrawAmount() {
		int customerIdToTest = 90;
		int amountToTest =100;

		BankingCustomer addedcustomer = new BankingCustomer(customerIdToTest, "DemoCustomer", "abc",120);
		bankingCustomerdao.addCustomer(addedcustomer);
		bankingCustomerdao.withdrawAmount(customerIdToTest, amountToTest);
		int retrievedCustomer = bankingCustomerdao.viewCustomerBalance(90);

		assertEquals(120-amountToTest, retrievedCustomer);

		// deleting the product after testing
		bankingCustomerdao.deleteCustomer(customerIdToTest);	}

	@Test
	public void testApplyCustomer() {
		fail("Not yet implemented");
	}
	

}
