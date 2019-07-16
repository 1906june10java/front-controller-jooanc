package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import com.revature.model.Employee;
import com.revature.service.EmployeeServiceAlpha;

public class EmployeeControllerAlpha implements EmployeeController {
	
	private static EmployeeController employeeController = new EmployeeControllerAlpha();
	
	private EmployeeControllerAlpha() {}
	
	public static EmployeeController getInstance() {
		return employeeController;
	}

	@Override
	public Object getAllEmployees(HttpServletRequest request) {
		Employee loggedEmployee = (Employee) request.getSession().getAttribute("loggedEmployee");
		
		if (loggedEmployee == null) {
			return "login.html";
		} else if(request.getParameter("fetch") == null) {
			return "all-employees.html";
		} else {
			return EmployeeServiceAlpha.getInstance().listAllEmployees();
		}
	}

}
