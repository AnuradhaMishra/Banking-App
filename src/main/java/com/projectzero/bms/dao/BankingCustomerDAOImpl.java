package com.projectzero.bms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.projectzero.bms.model.BankingCustomer;
import com.projectzero.bms.util.DBConnection;

public class BankingCustomerDAOImpl implements BankingCustomerDAO {
	
	private static Logger transactionLog = Logger.getLogger("debugLogger");
	private static Logger eventsLog = Logger.getLogger("reportsLogger");

	Connection connection = DBConnection.getDBconnection();
	private final String ADD_CUSTOMER_QUERY = "insert into BankingApp.customer values(?,?,?,?);";
	private final String VIEW_CUSTOMER_BALANCE_QUERY = "select customerbalance from BankingApp.customer where customerid = ?;";
	//private final String TRANSFER_BALANCE_CUSTOMER_QUERY = "insert into BankingApp.customer values(?,?,?,?);";
	private final String CHECK_CUSTOMER_PASSWORD_QUERY = "select password from BankingApp.customer where customerid = ?;";
	private final String CHECK_CUSTOMER_NAME_QUERY = "select customername from BankingApp.customer where customerid = ?;";
	private final String VIEW_CUSTOMER_ID_QUERY = "select customerId from BankingApp.customer where customerid = ?;";
	private final String DEPOSIT_AMOUNT_CUSTOMER_QUERY = "update BankingApp.customer set customerbalance = customerbalance+? where customerid = ?;";
	private final String WITHDRAW_AMOUNT_CUSTOMER_QUERY = "update BankingApp.customer set customerbalance = customerbalance-? where customerid = ?;";
	private final String APPLY_CUSTOMER_QUERY = "insert into BankingApp.temporarydata values(?,?,?,?,?);";
	private final String DELETE_CUSTOMER_QUERY = "delete from BankingApp.customer where customerId = ?";


	public boolean addCustomer(BankingCustomer customer) {

		int res = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(ADD_CUSTOMER_QUERY);
			statement.setInt(1, customer.getCustomerId());
			statement.setString(2, customer.getCustomerName());
			statement.setString(3, customer.getPassword());
			statement.setInt(4, customer.getCustomerBalance());

			res = statement.executeUpdate(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (res == 0)
			return false;
		else
			return true;
	}

	public int viewCustomerBalance(int customerId) {
		List<BankingCustomer> customers = new ArrayList<BankingCustomer>();
		int out=0 ;
		ResultSet res ;
		try {
			PreparedStatement statement = connection.prepareStatement(VIEW_CUSTOMER_BALANCE_QUERY);
			statement.setInt(1, customerId);
			res = statement.executeQuery();

			while (res.next()) {
				BankingCustomer customer = new BankingCustomer();
				customer.setCustomerBalance(res.getInt(1));
				customers.add(customer);
				out = customer.getCustomerBalance();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return out;
	}

	public void transferBalance(int senderId, int receiverId, int amount) {
		
		List<BankingCustomer> customers = new ArrayList<BankingCustomer>();
		String out = null;
		ResultSet res ;
		try {
			PreparedStatement statement = connection.prepareStatement(CHECK_CUSTOMER_NAME_QUERY);
			statement.setInt(1, receiverId);
			res = statement.executeQuery();

			while (res.next()) {
				BankingCustomer customer = new BankingCustomer();
				customer.setPassword(res.getString(1));
				customers.add(customer);
				out = customer.getPassword();
			}
			 CallableStatement statement1 = connection.prepareCall("call bankingapp.transferamount(?,?,?)");
			 statement1.setInt(1,senderId);
			 statement1.setInt(2,receiverId);
			 statement1.setInt(3,amount);
			 statement1.execute();
			 }
			catch (SQLException e) {
				e.printStackTrace();
			}
		System.out.println("Successfully Transferred INR "+amount+" to "+out);
		transactionLog.trace(senderId+" transfer of INR "+amount+" to "+receiverId+" on "+new Date()+" was successful");

	}
	/*
	public static void main(String[] args) {
		BankingCustomerDAOImpl impl = new BankingCustomerDAOImpl();
		//System.out.print(impl.viewCustomerBalance(12));
		//List<BankingCustomer> out = impl.viewCustomerBalance(12);
		
		System.out.println(impl.viewCustomerBalance(12));
		
	} */

	public String checkCustomerPassword(int customerId) {
		List<BankingCustomer> customers = new ArrayList<BankingCustomer>();
		String out = null;
		ResultSet res ;
		try {
			PreparedStatement statement = connection.prepareStatement(CHECK_CUSTOMER_PASSWORD_QUERY);
			statement.setInt(1, customerId);
			res = statement.executeQuery();

			while (res.next()) {
				BankingCustomer customer = new BankingCustomer();
				customer.setPassword(res.getString(1));
				customers.add(customer);
				out = customer.getPassword();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return out;	
	}

	public int viewCustomerId(int customerId) {
		List<BankingCustomer> customers = new ArrayList<BankingCustomer>();
		int out=0 ;
		ResultSet res ;
		try {
//			Statement statement = connection.createStatement();
//			ResultSet res = statement.executeQuery(VIEW_CUSTOMER_BALANCE_QUERY);
			PreparedStatement statement = connection.prepareStatement(VIEW_CUSTOMER_ID_QUERY);
			statement.setInt(1, customerId);
			res = statement.executeQuery();

			while (res.next()) {
				BankingCustomer customer = new BankingCustomer();
				customer.setCustomerId(res.getInt(1));
				customers.add(customer);
				out = customer.getCustomerId();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return out;
	}

	public boolean depositAmount(int customerId, int amount) {
		//List<BankingCustomer> customer = new ArrayList<BankingCustomer>();
		int res = 0;

		try {
			PreparedStatement statement = connection.prepareStatement( DEPOSIT_AMOUNT_CUSTOMER_QUERY);
			statement.setInt(1, amount);
			statement.setInt(2, customerId);
			

			res = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (res == 0)
			return false;
		else
			return true;	}

	public boolean withdrawAmount(int customerId, int amount) {
		//List<BankingCustomer> customer = new ArrayList<BankingCustomer>();
		int res = 0;

		try {
			PreparedStatement statement = connection.prepareStatement( WITHDRAW_AMOUNT_CUSTOMER_QUERY);
			statement.setInt(1, amount);
			statement.setInt(2, customerId);
			

			res = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (res == 0)
			return false;
		else
			return true;		
	}

	public boolean applyCustomer(BankingCustomer customer) {
		int res = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(APPLY_CUSTOMER_QUERY);
			statement.setInt(1, customer.getCustomerId());
			statement.setString(2, customer.getCustomerName());
			statement.setString(3, customer.getPassword());
			statement.setInt(4, customer.getCustomerBalance());
			statement.setString(5, "c");


			res = statement.executeUpdate(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (res == 0)
			return false;
		else
			return true;
		
	}

	public boolean deleteCustomer(int customerId) {
		boolean result = false;

		try {
			PreparedStatement stat = connection.prepareStatement(DELETE_CUSTOMER_QUERY);
			stat.setInt(1, customerId);

			stat.executeUpdate();
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;		
	}

	
}
