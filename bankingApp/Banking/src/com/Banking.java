package com;

import java.util.ArrayList;
import java.util.Scanner;

public class Banking {
	
	static BankingEngine engine = new BankingEngine();
	
	public static void main(String[] args) {
		
		int x = 512;
		short test = (short)x;
		System.out.println(test);
		
		
		
		/*
		ArrayList<Customer> listOfCustomers = engine.getAllCustomers();
		if (listOfCustomers.size() != 0) {
			for (Customer s : listOfCustomers) {
				System.out.println(s.toString());
			}
		}
		start();
		*/

		}
	
	static void start() {
		System.out.println("Welcome to Bank of Cayman Islands"
				+ "\nWhat would you like to do"
				+ "\nFor LogIn, Press 1"
				+ "\nFor SignUp, Press 2"
				+ "\nTo deposite funds, Press 3");
		
		Scanner scan = new Scanner(System.in);
		int op1 = 0;
		op1 = Integer.parseInt(scan.nextLine());	
		switch(op1) {
		case 1: logIn(); break;
		case 2: signUp(); break;
		case 3: deposit(); break;
		default: 
		System.out.println("Sorry, that is not an option. Please try again!");
		start();
		}
		scan.close();
		return;
		
	}
	
	static void logIn() {
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to LogIn. Please enter your username");
		String username = in.nextLine();
		if(engine.existsUsername(username)) {
			System.out.println("Username found");
			System.out.println("Please enter your password:");
			String password = in.nextLine();
			if(engine.existsPassword(password)) {
				System.out.println("You are logged in!");
			}
		}
		else {
			int op2 = 0;
			System.out.println("You are not a user. Would you like to sign up?"
					+ "\n For SignUp, Press 1"
					+ "\n Press 2 to exit");
			op2 = Integer.parseInt(in.nextLine());
			switch(op2) {
			case 1: signUp(); break;
			case 2:
				System.out.println("We hope you will come back!");
				break;
			default: 
				System.out.println("Sorry, this is not an option. Please try again!");
			}
		}
		return;
	}
	
	static void signUp() {
		Scanner scan = new Scanner(System.in);
		Customer tempCustomer = new Customer();
		System.out.println("Please enter your username");
		String username = scan.nextLine();
		
		if(!engine.existsUsername(username)){
			System.out.println("Please enter your password");
			String password = scan.nextLine();
			System.out.println("Please enter your First name");
			String firstName = scan.nextLine();
			System.out.println("Please enter your Last name");
			String lastName =  scan.nextLine();
			Double balance = 0.0;
			tempCustomer.setUsername(username);
			tempCustomer.setPassword(password);
			tempCustomer.setFirstname(firstName);
			tempCustomer.setLastname(lastName);
			tempCustomer.setBalance(balance);
			
			engine.addCustomer(tempCustomer);
			System.out.println("Your account has been created");
			
		}
		
		scan.close();
	}
	
	static void deposit() {
		Scanner scan1 = new Scanner(System.in);
		System.out.println("To deposite funds, please Log-in");
		System.out.println("Welcome to Log-in. Please enter your username");
		String username = scan1.nextLine();
		if(engine.existsUsername(username)) {
			System.out.println("Username found");
			System.out.println("Please enter your password:");
			String password = scan1.nextLine();
			if(engine.existsPassword(password)) {
				System.out.println("Password found");
				System.out.println("Enter the amount you would like to deposit");
				Double depositAmount = Double.parseDouble(scan1.nextLine());
				System.out.println("Please place the cash and checks in the slot");
				engine.depositMoney(username, depositAmount);
				
			}
		}
		else {
			System.out.println("No Account exists with that username, please sign-up");
			start();
		}
		
		
		scan1.close();
	}
	
	
}
