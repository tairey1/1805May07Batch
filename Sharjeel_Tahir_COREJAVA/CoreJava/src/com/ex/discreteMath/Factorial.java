package com.ex.discreteMath;

import java.util.Scanner;

public class Factorial {

	public static void main(String[] args) {
		
		int factorial = 1;
		Scanner number = new Scanner (System.in);
		System.out.println("Enter Number:");
		
		int num = number.nextInt();
		number.close();
		
		for (int i=1; i<=num; i++) {
			factorial = factorial*i;
		}
		
		System.out.println("the factorial of 10" + num + " is " + factorial);
		
		

	}

}
