package com.projectzero.bms.dao;

import java.sql.Connection;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.projectzero.bms.model.BankingCustomer;
import com.projectzero.bms.model.BankingEmployee;
import com.projectzero.bms.model.BankingTemporaryData;
import com.projectzero.bms.util.DBConnection;

public class BankingEmployeeDAOImpl implements BankingEmployeeDAO {

	Connection connection = DBConnection.getDBconnection();
	private final String ADD_CUSTOMER_QUERY = "insert into BankingApp.customer values(?,?,?,?);";
	// private final String VIEW_CUSTOMER_BALANCE_QUERY = "select customerbalance
	// from BankingApp.customer;";
	// private final String TRANSFER_BALANCE_CUSTOMER_QUERY = "insert into
	// BankingApp.customer values(?,?,?,?);";
	private final String FIND_ALL_CUSTOMER_QUERY = "select * from BankingApp.customer;";
	private final String CHECK_EMPLOYEE_PASSWORD_QUERY = "select password from BankingApp.employee where employeeid = ?;";
	private final String APPLY_EMPLOYEE_QUERY = "insert into BankingApp.temporarydata values(?,?,?,null,?);";
	private final String VIEW_TEMPORARYDATA_QUERY = "select * from BankingApp.temporarydata;";
	private final String ADD_CUSTOMERTEMPORARYDATA_QUERY = "INSERT INTO bankingapp.customer SELECT id,name,password,balance FROM bankingapp.temporarydata WHERE id=? and type='c';";
	private final String ADD_EMPLOYEETEMPORARYDATA_QUERY = "INSERT INTO bankingapp.employee SELECT id,name,password FROM bankingapp.temporarydata WHERE id=? and type='e';";
	private final String DELETE_TEMPORARYDATA_QUERY = "DELETE FROM bankingapp.temporarydata WHERE id=? and type=?;";

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
			e.printStackTrace();
		}

		if (res == 0)
			return false;
		else
			return true;
	}

	/*
	 * public boolean viewCustomerBalance(int customerId) { // TODO Auto-generated
	 * method stub return false; }
	 * 
	 * public boolean transferBalance(int senderId, int receiverId, int amount) { //
	 * TODO Auto-generated method stub return false; }
	 */

	public List<BankingCustomer> getAllCustomer() {

		List<BankingCustomer> customers = new ArrayList<BankingCustomer>();

		try {
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery(FIND_ALL_CUSTOMER_QUERY);

			while (res.next()) {
				BankingCustomer customer = new BankingCustomer();
				customer.setCustomerId(res.getInt(1));
				customer.setPassword(res.getString(3));
				customer.setCustomerBalance(res.getInt(4));
				customer.setCustomerName(res.getString(2));
				customers.add(customer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return customers;
	}

	public boolean addEmployee(BankingEmployee employee) {
		int res = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(ADD_CUSTOMER_QUERY);
			statement.setInt(1, employee.getemployeeId());
			statement.setString(2, employee.getemployeeName());
			statement.setString(3, employee.getPassword());

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

	public String checkEmployeePassword(int employeeId) {
		List<BankingEmployee> employees = new ArrayList<BankingEmployee>();
		String out = null;
		ResultSet res;
		try {
			PreparedStatement statement = connection.prepareStatement(CHECK_EMPLOYEE_PASSWORD_QUERY);
			statement.setInt(1, employeeId);
			res = statement.executeQuery();

			while (res.next()) {
				BankingEmployee employee = new BankingEmployee();
				employee.setPassword(res.getString(1));
				employees.add(employee);
				out = employee.getPassword();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return out;
	}

	public boolean applyEmployee(BankingEmployee employee) {
		int res = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(APPLY_EMPLOYEE_QUERY);
			statement.setInt(1, employee.getemployeeId());
			statement.setString(2, employee.getemployeeName());
			statement.setString(3, employee.getPassword());
			statement.setString(4, "e");

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

	public void approveDenyRequest() {
		List<BankingTemporaryData> temps = new ArrayList<BankingTemporaryData>();

		try {
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery(VIEW_TEMPORARYDATA_QUERY);

			while (res.next()) {
				BankingTemporaryData temp = new BankingTemporaryData();
				temp.setId(res.getInt(1));
				temp.setPassword(res.getString(3));
				temp.setBalance(res.getInt(4));
				temp.setName(res.getString(2));
				temp.setType(res.getString(5));

				temps.add(temp);
				System.out.println(temp);
				
				String request="B";
				while (!(request.equals("a") || request.equals("A")||request.equals("D")||request.equals("d"))) {
				Scanner sc = new Scanner(System.in);
				
				System.out.println("Do you want to approve or deny? (A/D) : ");
				request = sc.next();
				

				if (request.equals("A") || request.equals("a")) {
					int id = temp.getId();
					String type = temp.getType();
					String fullType = null;

					if (type.equals("C") || type.equals("c")) {
						int res1 = 0;
						fullType = "Customer";

						PreparedStatement statement1 = connection.prepareStatement(ADD_CUSTOMERTEMPORARYDATA_QUERY);
						statement1.setInt(1, temp.getId());
						res1 = statement1.executeUpdate();

						PreparedStatement stat = connection.prepareStatement(DELETE_TEMPORARYDATA_QUERY);
						stat.setInt(1, temp.getId());
						stat.setString(2, temp.getType());

						stat.executeUpdate();

					} else {
						int res1 = 0;
						fullType = "Employee";

						PreparedStatement statement1 = connection.prepareStatement(ADD_EMPLOYEETEMPORARYDATA_QUERY);
						statement1.setInt(1, temp.getId());
						res1 = statement1.executeUpdate();

						PreparedStatement stat = connection.prepareStatement(DELETE_TEMPORARYDATA_QUERY);
						stat.setInt(1, temp.getId());
						stat.setString(2, temp.getType());


						stat.executeUpdate();

					}
					System.out.println("You have sucessfully approved person with Id : "+temp.getId()+" with Name : "+temp.getName()+ " to "+fullType+"\n");
					
				} else if (request.equals("D") || request.equals("d")) {

					PreparedStatement stat = connection.prepareStatement(DELETE_TEMPORARYDATA_QUERY);
					stat.setInt(1, temp.getId());
					stat.setString(2, temp.getType());

					stat.executeUpdate();
					
					
					System.out.println("You have sucessfully disapproved person with Id : "+temp.getId()+" with Name : "+temp.getName()+"\n");
				}
				else {
					System.out.println("Sorry, you have entered a wrong input. Please type valid input (A/D)!!!");
				}
			}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
