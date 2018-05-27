package com.ex.service;

import java.util.ArrayList;
import java.util.List;

import com.ex.dao.UserDao;
import com.ex.dao.UserDaoImpl;
import com.ex.pojos.Customer;

public class BankingService {
	
	static UserDao<Customer> customerdao =  new UserDaoImpl();
	
	
	public List<Customer> getAllCustomers(){
		return customerdao.getAllCustomers();
	}
	
	public boolean uniqueUsername (String username) {
		Customer dummy = new Customer();
		dummy.setUsername(username);
		
		return customerdao.isUniqueUsername(dummy);
		
	}
	
	public boolean uniquePassword(String password) {
		Customer dummy = new Customer();
		dummy.setPassword(password);
		return customerdao.isUniquePassword(dummy);
		
	}
	
	public Customer addCustomer(Customer obj) {
		return customerdao.addCustomer(obj);
	}
	
	
	
	public void depositFunds(Customer obj) {
		customerdao.depositMoney(obj);
		
	}
	
	public void jointAccount(Customer obj) {
		customerdao.multiAccount(obj);
	}
	
	


	
	
}
