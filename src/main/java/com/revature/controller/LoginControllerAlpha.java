package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import com.revature.ajax.ClientMessage;
import com.revature.model.Employee;
import com.revature.service.EmployeeServiceAlpha;

public class LoginControllerAlpha implements LoginController {
	
	private static LoginController loginController = new LoginControllerAlpha();
	
	private LoginControllerAlpha() {}
	
	public static LoginController getInstance() {
		return loginController;
	}

	@Override
	public Object login(HttpServletRequest request) {
		if(request.getMethod().equals("GET")) {
			return "login.html";
		}
		
		Employee loggedEmployee = EmployeeServiceAlpha.getInstance().authenticate(
				new Employee(request.getParameter("username"),
							request.getParameter("password"))
				);
		if(loggedEmployee == null) {
			return new ClientMessage("AUTHENTICATION FAILED");
		}
		request.getSession().setAttribute("loggedEmployee", loggedEmployee);
		return loggedEmployee;
	}

	@Override
	public Object logout(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
