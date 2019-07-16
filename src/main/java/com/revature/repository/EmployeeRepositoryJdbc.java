package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.model.Employee;
import com.revature.model.Title;
import com.revature.util.ConnectionUtil;

public class EmployeeRepositoryJdbc implements EmployeeRepository {
	
	private static final Logger logger = Logger.getLogger(EmployeeRepositoryJdbc.class);
	
	private static EmployeeRepository employeeRepository;
	
	private EmployeeRepositoryJdbc() {}
	
	public static EmployeeRepository getInstance() {
		if(employeeRepository == null) {
			employeeRepository = new EmployeeRepositoryJdbc();
		}
		
		return employeeRepository;
	}

	@Override
	public boolean create(Employee employee) {
		logger.trace("Entering create method with parameter: " + employee);
		try(Connection connection = ConnectionUtil.getConnection()){
			int parameterIndex = 0;
			String sql = "INSERT INTO employee VALUES (?. ?, ?, ?, ?, ?)";
			
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(++parameterIndex, employee.getFirstName());
			statement.setString(++parameterIndex, employee.getLastName());
			statement.setString(++parameterIndex, employee.getUsername());
			statement.setString(++parameterIndex, employee.getPassword());		
			statement.setLong(++parameterIndex, employee.getTitle().getTitleId());		
			
			if(statement.executeUpdate() > 0) {
				return true;
			}
		}catch(SQLException e) {
			logger.error("Exception creating employee", e);
		}
		return false;
	}

	@Override
	public Employee findByEmployeeId(long employeeId) {
		try(Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE WHERE E_ID = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, employeeId);
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				Employee employee = new Employee(
						result.getLong("E_ID"),
						result.getString("FIRST_NAME"),
						result.getString("LAST_NAME"),
						result.getString("USERNAME"),
						result.getString("PASSWORD"),
						new Title(result.getLong("T_ID"), "")
				);
				if(employee.getTitle().getTitleId() == 1) {
					employee.getTitle().setTitleName("EMPLOYEE");
				} else {
					employee.getTitle().setTitleName("MANAGER");
				}
			} 
		} catch (SQLException e) {
			logger.error("Exception in finding employee", e);
		}
		return null;
	}

	@Override
	public List<Employee> findAll() {
		try(Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			
			List<Employee> employeeList = new ArrayList<>();
			while(result.next()) {
				employeeList.add(new Employee(
						result.getLong("E_ID"),
						result.getString("FIRST_NAME"),
						result.getString("LAST_NAME"),
						result.getString("USERNAME"),
						result.getString("PASSWORD"),
						new Title(result.getLong("T_ID"), "")
						));				
			}
		} catch (SQLException e) {
			
		}
		return new ArrayList<>();
	}

	@Override
	public boolean update(Employee employee) {
		try(Connection connection = ConnectionUtil.getConnection()) {
			int statementIndex = 0;
			String command = "INSERT INTO EMPLOYEE VALUES(NULL, ?, ?, ?, ?)";
			
			PreparedStatement statement = connection.prepareStatement(command);
			
			statement.setString(++statementIndex, employee.getFirstName().toUpperCase());
			statement.setString(++statementIndex, employee.getLastName().toUpperCase());			
			statement.setString(++statementIndex, employee.getUsername().toLowerCase());
			statement.setString(++statementIndex, employee.getPassword());
			
			if(statement.executeUpdate() > 0) {
			return true;
			}
		} catch (SQLException e){
			logger.warn("Exception creating a new customer");
		}
		return false;
	}

	@Override
	public String getEmployeeHash(Employee employee) {
		try(Connection connection = ConnectionUtil.getConnection()){
			int statementIndex = 0;
			String command = "SELECT GET_EMPLOYEE_HASH(?,?) AS HASH FROM DUAL";
			PreparedStatement statement = connection.prepareStatement(command);
			statement.setString(++statementIndex, employee.getUsername());
			statement.setString(++statementIndex, employee.getPassword());
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				return result.getString("HASH");
			}
		} catch(SQLException e) {
			logger.warn("Exception getting customer hash", e);
		}
		return new String();
	}

	@Override
	public Employee selectById(Employee employee) {
		try(Connection connection = ConnectionUtil.getConnection()){
			int statementIndex = 0;
			String sql = "SELECT * FROM EMPLOYEE WHERE USERNAME = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(++statementIndex, employee.getUsername());
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				return new Employee(
						result.getInt("E_ID"),
						result.getString("FIRST_NAME"),
						result.getString("LAST_NAME"),
						result.getString("USERNAME"),
						result.getString("PASSWORD"),
						new Title(result.getLong("T_ID"), "")
						);	
			}
		} catch(SQLException e) {
			logger.warn("Exception selecting an employee by ID", e);
		}
		return new Employee();
	}


}
