package com.ex.dao;

import java.util.ArrayList;
import java.util.Random;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ex.pojos.Customer;
import com.ex.util.ConnectionFactory;

public class UserDaoImpl implements UserDao <Customer> {

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection();){
			String query = "select * from users";
			
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				Customer temp = new Customer();
				temp.setId(rs.getInt(1));
				temp.setFirstname(rs.getString(2));
				temp.setLastname(rs.getString(3));
				temp.setUsername(rs.getString(4));
				temp.setPassword(rs.getString(5));
				temp.setBalance(rs.getDouble(6));
				temp.setZipcode(rs.getInt(7));
				customers.add(temp);				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return customers;
	}

	@Override
	public Customer addCustomer(Customer obj) {
		Customer newCustomer = new Customer();
		int customerID = 0;
		try(Connection conn = ConnectionFactory.getInstance().getConnection();){
			conn.setAutoCommit(false);
			String query = "insert into users(firstname, lastname, username, password, balance, zipcode)" + "values(?,?,?,?,?,?)";
			
			String[] keys = new String[1];
			keys[0] = "userid";
			
			PreparedStatement ps = conn.prepareStatement(query, keys);
			
			ps.setString(1, obj.getFirstname());
			ps.setString(2, obj.getLastname());
			ps.setString(3, obj.getUsername());
			ps.setString(4, obj.getPassword());
			ps.setDouble(5, obj.getBalance());
			ps.setInt(6, obj.getZipcode());
			
			int rows = ps.executeUpdate();
			System.out.println("\n------ System Message: Trigger Return. Userid rows returned " + rows);
				
			if(rows != 0) {
				ResultSet pk = ps.getGeneratedKeys();
				while(pk.next()) {
					newCustomer.setId(pk.getInt(1));
					customerID = pk.getInt(1);
				}
				
			Random dice = new Random();
			int accountNumber = 1 + dice.nextInt(1000000000);
			System.out.println("------ Message for User: Your Accountn number is: " + accountNumber + " ------");
			System.out.println("------ System Message: DB Secondary AccountID " + customerID + "------");
			String query2 = "insert into accounts(accountnumber, accounttype, userid)" + "values(?,?,?)";
			String[] keys2 = new String[1];
			keys2[0] = "accountid";
			PreparedStatement ps2 = conn.prepareStatement(query2, keys2);
			ps2.setInt(1, accountNumber);
			ps2.setInt(2, 1);
			ps2.setInt(3, customerID);
			
			int rows2 = ps2.executeUpdate();
			System.out.println("------ System Message: Trigger Return. Accountid Rows returned " + rows2);
			
			
			conn.commit();
			}		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
		return newCustomer;
	}

	@Override
	public boolean isUniqueUsername(Customer obj) {
		String username = obj.getUsername();
		boolean exists = false;
		try(Connection conn = ConnectionFactory.getInstance().getConnection();){
			String query = "select * from users where username = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, username);
			
			ResultSet info = ps.executeQuery();
			System.out.println("\n------System Message: In unqiue Username------");
			exists = info.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return exists;
		
	}

	@Override
	public boolean isUniquePassword(Customer obj) {
		String password = obj.getPassword();
		boolean exists = false;
		try(Connection conn = ConnectionFactory.getInstance().getConnection();){
			String query = "select * from users where password = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, password);
			
			ResultSet info = ps.executeQuery();
			System.out.println("\n------System Message: in unique password-------");
			exists = info.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return exists;
	}

	@Override
	public void depositMoney(Customer obj) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection();){
			String query = "{call deposit_by_user(?,?) }";
			
			CallableStatement cs = conn.prepareCall(query);
			
			cs.setString(1, obj.getUsername());
			cs.setDouble(2, obj.getBalance());
			cs.execute();
			
			 	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void multiAccount(Customer obj) {
		int idOfJoint = 0;
		try(Connection conn = ConnectionFactory.getInstance().getConnection();){
			String query = "select userid from users where username = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, obj.getUsername());
			ResultSet info = ps.executeQuery();
			
			while (info.next()) {
				idOfJoint = info.getInt(1);
			}
			
			// System.out.println(idOfJoint);
			
			Random dice = new Random();
			int accountNumber = 1 + dice.nextInt(1000000000);
			System.out.println("\n------ Message for User: Your Accountn number is: " + accountNumber + " ------");
			System.out.println("\n------ System Message: DB Secondary AccountID " + idOfJoint + " ------");
			
			String query2 = "insert into accounts(accountnumber, accounttype, userid)" + "values(?,?,?)";
			
			String[] keys3 = new String[1];
			keys3[0] = "accountid";
			
			PreparedStatement ps2 = conn.prepareStatement(query2, keys3);
			ps2.setInt(1, accountNumber);
			ps2.setInt(2, 1);
			ps2.setInt(3, idOfJoint);
			
			int rows3 = ps2.executeUpdate();
			System.out.println("\n ------ System Message: Trigger return. Accountid Rows returned " + rows3);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
