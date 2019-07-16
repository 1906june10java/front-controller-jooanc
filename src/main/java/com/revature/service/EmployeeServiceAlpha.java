package com.revature.service;

import java.util.List;

import com.revature.model.Employee;
import com.revature.repository.EmployeeRepositoryJdbc;
import com.revature.controller.LoginController;

public class EmployeeServiceAlpha implements EmployeeService{
	
	private static EmployeeService employeeService = new EmployeeServiceAlpha();
	
	private EmployeeServiceAlpha() {};
	
	public static EmployeeService getInstance() {
		return employeeService;
	}
	
	@Override
	public List<Employee> listAllEmployees() {
		return EmployeeRepositoryJdbc.getInstance().findAll();
	}

	@Override
	public Employee authenticate(Employee employee) {
		Employee loggedEmployee = EmployeeRepositoryJdbc.getInstance().selectById(employee);
		
		if(loggedEmployee.getPassword().equals(EmployeeRepositoryJdbc.getInstance().getEmployeeHash(employee))) {
			return loggedEmployee;
		}
		return new Employee();
	}
}
