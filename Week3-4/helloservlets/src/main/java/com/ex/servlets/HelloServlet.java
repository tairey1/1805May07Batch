package com.ex.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloServlet extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) 
			throws ServletException, IOException {
		
		/*
		 * in order to accept input from the user, we need to use the 
		 * request object
		 * 
		 * if we want to provide a response to the user
		 * we need to use the response object 
		 * 
		 * the code that we write for the service method will be 
		 * considered the definition for the process 
		 */
		System.out.println("IN GENERIC SERVLET SERVICE() METHOD");
		
		PrintWriter out = res.getWriter();
		out.println("HELLO WORLD! WE COME IN PEACE FROM JAVA SERVLETS!");		
		
	}
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		System.out.println("INITIALIZING GENERIC SERVLET");
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		
		System.out.println("DESTROYING GENERIC SERVLET");
	}

}
