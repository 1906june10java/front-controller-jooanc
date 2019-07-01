package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import com.revature.model.Customer;
import com.revature.service.CustomerService;

public class CustomerController {
	
	/*
	 * Dependency
	 */
	private static CustomerService customerService = new CustomerService();
	
	public static Object getAllCustomers(HttpServletRequest request) {
		return customerService.listAllCustomers();
	}
	
	public static Object register(HttpServletRequest request) {
		if(request.getMethod().equals("GET")) {
			return "register.html";
		}
		
		/* At this point it was POST */
		
		customerService.register(new Customer(0, request.getParameter(""), request.getParameter("firstName"), request.getParameter("username"), request.getParameter("password")));
		
		return "REGISTRATION_SUCCESSFUL";
	}
	
	
}
