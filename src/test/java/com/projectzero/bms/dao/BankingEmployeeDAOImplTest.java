package com.projectzero.bms.dao;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.projectzero.bms.model.BankingCustomer;

public class BankingEmployeeDAOImplTest {
	private BankingEmployeeDAO bankingEmployeedao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		bankingEmployeedao = new BankingEmployeeDAOImpl();	}

	@After
	public void tearDown() throws Exception {
		bankingEmployeedao = null;
	}

	@Test
	public void testAddCustomer() {
		int customerIdToTest = 90;

		BankingCustomerDAO bankingCustomerdao = new BankingCustomerDAOImpl();

		BankingCustomer addedcustomer = new BankingCustomer(customerIdToTest, "DemoCustomer", "abc",120);
		bankingCustomerdao.addCustomer(addedcustomer);

		int retrievedCustomer = bankingCustomerdao.viewCustomerId(90);

		assertEquals(customerIdToTest, retrievedCustomer);

		// deleting the product after testing
		bankingCustomerdao.deleteCustomer(customerIdToTest);	
		}

	@Test
	public void testGetAllCustomer() {

		int customerIdToTest = -999;
		BankingCustomerDAO bankingCustomerdao = new BankingCustomerDAOImpl();
		List<BankingCustomer> originalCustomers1 = bankingEmployeedao.getAllCustomer();
		bankingCustomerdao.addCustomer(new BankingCustomer(customerIdToTest, "DemoProduct", "abcdefg", 60));
		List<BankingCustomer> originalCustomers2 = bankingEmployeedao.getAllCustomer();

		assertEquals(originalCustomers2.size(), originalCustomers1.size() + 1);

		bankingCustomerdao.deleteCustomer(customerIdToTest);
	}

	@Test
	public void testAddEmployee() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckEmployeePassword() {
		fail("Not yet implemented");
	}

	@Test
	public void testApplyEmployee() {
		fail("Not yet implemented");
	}

}
