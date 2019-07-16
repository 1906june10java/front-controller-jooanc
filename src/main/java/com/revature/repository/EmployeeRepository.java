package com.revature.repository;

import java.util.List;

import com.revature.model.Employee;

public interface EmployeeRepository {
	public boolean create(Employee employee);
	public Employee selectById(Employee employee);
	public Employee findByEmployeeId(long employeeId);
	public List<Employee> findAll();
	public boolean update(Employee employee);
	public String getEmployeeHash(Employee employee);
}
