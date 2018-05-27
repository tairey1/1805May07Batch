package com;

import java.util.ArrayList;
import java.io.*;



public class DAO {

	final static String fileLocation = "C:\\Users\\Sharjeel Tahir\\Desktop\\src\\Banking\\src\\com\\users.txt";
	
	ArrayList<Customer> readCustomers(){
		ArrayList<Customer> masterCustomerList =  new ArrayList<Customer>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileLocation));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				String[] data = line.split("\t");
				Customer tempCustomer = new Customer();
				tempCustomer.setFirstname(data[0]);
				tempCustomer.setLastname(data[1]);
				tempCustomer.setUsername(data[2]);
				tempCustomer.setPassword(data[3]);
				tempCustomer.setBalance(Double.parseDouble(data[4]));
				masterCustomerList.add(tempCustomer);
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}

		return masterCustomerList;	
	}
	
	void addCustomer(Customer s) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileLocation, true));
			writer.write(s.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	

		
	
	
}
