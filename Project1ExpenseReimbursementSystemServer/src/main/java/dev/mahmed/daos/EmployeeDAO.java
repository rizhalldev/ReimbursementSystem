package dev.mahmed.daos;

import java.util.List;

import dev.mahmed.entities.Employee;
import dev.mahmed.entities.Manager;

public interface EmployeeDAO {
	
	// C
	Employee createEmployee(Employee employee);
	
	// R
	Employee getEmployeeById(int id); // pass in Employee with ID only
	Employee getEmployeeByUsername(String username); // pass in Employee with username only
	Employee getEmployeeByFullName(String firstName, String lastName); // pass in Employee with firstName & lastName
	List<Employee> getEmployeesByFirstName(String firstName); // pass in Employee with first name only
	List<Employee> getEmployeesByDivision(String division); // pass in Manager with division only
	List<Employee> getEmployeesByManager(Manager manager); // pass in manager with managerId only
	List<Employee> getAllEmployees();
	
	// U
	Employee updateEmployee(Employee employee);
	
	// D - NOT IMPLEMENTED
	boolean deleteEmployee(Employee employee);

}
