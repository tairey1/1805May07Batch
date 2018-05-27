package com.ex.main;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import com.ex.pojos.Customer;
import com.ex.service.BankingService;

public class App {

	static BankingService bs = new BankingService();
	
	
	public static void main(String[] args) {
			
		List<Customer> custs = bs.getAllCustomers();
		for (Customer a : custs) {
			
			System.out.println(a.toString());
		}
		//System.out.println("Working");
		start();
	}
	
	
	
	static void start() {
		
		System.out.println("----------------------------------------------------"
						+"\n                                                   |"
						+"\n      WELCOME TO THE BANK OF CAYMAN ISLANDS        |"
						+"\n                                                   |"
						+"\n----------------------------------------------------");
		
		
		System.out.println("\nSelect from the following options"
				+ "\n>> For LogIn          Press 1"
				+ "\n>> For SignUp         Press 2"
				+ "\n>> To deposite funds  Press 3");
		
		Scanner scan = new Scanner(System.in);
		int op1 = 0;
		op1 = Integer.parseInt(scan.nextLine());	
		switch(op1) {
		case 1: logIn(); break;
		case 2: signUp(); break;
		default: 
		System.out.println("Sorry, that is not an option. Please try again!");
		start();
		}
		scan.close();
		return;
		
	}
	
	
	static void logIn() {
		Scanner in = new Scanner(System.in);
		System.out.println("\nWelcome to LogIn. Please enter your username");
		String username = in.nextLine();
		if (bs.uniqueUsername(username)) {
			//System.out.println("\n------ System Message: Username found");
			System.out.println("Please enter your password:");
			String password = in.nextLine();
			if(bs.uniquePassword(password)) {
				//System.out.println("\n ------ System Message: Password found");
				int op3 = 0;
				System.out.println("\n Select from the following options:"
						+ "\n>> To deposit funds  			Press 1"
						+ "\n>> To add another account 		Press 2"
						+ "\n>> To Log off				Press 3");
				op3 = Integer.parseInt(in.nextLine());
				switch(op3) {
				case 1: deposit(username); break;
				case 2: secondaryAccount(username); break;
				case 3: System.out.println("You are logged out"); break;
				default:
					System.out.println("Sorry, this is not an option. Please try again");
				}			
			}
		}
		else {
			int op2 = 0;
			System.out.println("\nYou are not a user. Would you like to sign up?"
					+ "\n>> For SignUp		 Press 1"
					+ "\n>> To log out       Press 3");
			op2 = Integer.parseInt(in.nextLine());
			switch(op2) {
			case 1: signUp(); break;
			case 2:
				System.out.println("We hope you will come back!");
				break;
			default: 
				System.out.println("Sorry, this is not an option. Please try again");
			}
		}
		return;		
	}
	
	
	static void signUp() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter your username");
		String username = scan.nextLine();
		if(!bs.uniqueUsername(username)) {
			System.out.println("------ System Message: Username is unique ------");
			System.out.println("\nPlease enter your password");
			String password = scan.nextLine();
			System.out.println("Please enter your First name");
			String firstName = scan.nextLine();
			System.out.println("Please enter your Last name");
			String lastName =  scan.nextLine();
			System.out.println("Please enter your zipcode");
			int zipcode = Integer.parseInt(scan.nextLine());
			Double balance = 0.0;
			Customer newCustomer = new Customer(firstName, lastName, username, password, balance, zipcode);
			Customer custDB = bs.addCustomer(newCustomer);
			//System.out.println(custDB.getId());
			
		}
		
	}
	
	static void deposit(String idname){
		Scanner scan1 = new Scanner(System.in);
		System.out.println("Enter the amount you would like to deposit");
		Double depositAmount = Double.parseDouble(scan1.nextLine());
		System.out.println("Please place the cash and checks in the slot");
		Customer newDeposit = new Customer(idname, depositAmount);
		bs.depositFunds(newDeposit);
		
	}
	
	static void secondaryAccount(String idname) {
		Double balance = 0.0;
		Customer moreAccount = new Customer(idname, balance);
		bs.jointAccount(moreAccount);
		System.out.println("Account created");
		
		
	}
	
}
