package com;

public class Customer {
	
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private double balance;
	
	public Customer() {}
	
	private Customer(String firstname, String lastname, String username, String password, double balance) {
		super();
		this.firstname = firstname;
		this.lastname  = lastname;
		this.username  = username;
		this.password  = password;
		this.balance   = balance;	
 		
	}

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance += balance;
	}
	
	@Override
	public String toString() {
		return firstname + "\t" + lastname + "\t" + username + "\t" + password + "\t" + balance + "\n";
	}
	
	
	
	
	
	
	
}
