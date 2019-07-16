package com.revature.request;

import javax.servlet.http.HttpServletRequest;

import com.revature.controller.EmployeeControllerAlpha;
import com.revature.controller.LoginControllerAlpha;


public class RequestHelper {
	
	private RequestHelper() {}
	
	public static Object  process(HttpServletRequest request) {
		switch(request.getRequestURI()) {
		case "/login.do":
			return LoginControllerAlpha.getInstance().login(request);
		case "/logout.do":
			return LoginControllerAlpha.getInstance().logout(request);
		case "/getAll.do":
			return EmployeeControllerAlpha.getInstance().getAllEmployees(request);
		default:
			return "not-implemented.html";
		}
	}
}