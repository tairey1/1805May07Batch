package com;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BankingEngine {
	
	final static String fileLocation = "C:\\Users\\Sharjeel Tahir\\Desktop\\src\\Banking\\src\\com\\users.txt";
	
	ArrayList<Customer> getAllCustomers() {
		DAO readAccessObj = new DAO();
		return readAccessObj.readCustomers();
	}
	
	boolean existsUsername (String username) {
		ArrayList<Customer> customers = getAllCustomers();
		return customers.stream().anyMatch( s -> s.getUsername().equalsIgnoreCase(username));
	}
	
	boolean existsPassword (String password) {
		ArrayList<Customer> customers = getAllCustomers();
		return customers.stream().anyMatch( s -> s.getPassword().equals(password));
	}
	void addCustomer(Customer s) {
		DAO writeAccessObject = new DAO();
		writeAccessObject.addCustomer(s);
		
	}
	
	void depositMoney(String username, Double amount) {
		ArrayList<Customer> customers = getAllCustomers();
		BufferedWriter writer1 = null;
		try {
			writer1 = new BufferedWriter(new FileWriter(fileLocation));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (customers.size() !=0) {
			for (Customer customer : customers) {
				if (customer.getUsername().equalsIgnoreCase(username)){
					customer.setBalance(amount);
					System.out.println("Amount " + amount + " has been added to you account" );
				}
			}

			
		if (customers.size() != 0 ) {
			for (Customer customer : customers) {		
				try {
					writer1.write(customer.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		}
		try {
			writer1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//customers.stream()(s -> {if(s.getUsername().equalsIgnoreCase(username)) return true;} );
	}
		
	

}
