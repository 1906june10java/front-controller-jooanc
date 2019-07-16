package com.revature.service;

import java.util.List;

import com.revature.model.Employee;

public interface EmployeeService {

	public List<Employee> listAllEmployees();
	
	public Employee authenticate(Employee employee);
}
