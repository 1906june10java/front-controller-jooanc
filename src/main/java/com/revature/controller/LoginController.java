package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

public interface LoginController {
	
	public Object login(HttpServletRequest request);
	
	public Object logout(HttpServletRequest request);

}
