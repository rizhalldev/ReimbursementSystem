package dev.mahmed.daos;

import java.util.List;

import dev.mahmed.entities.Employee;
import dev.mahmed.entities.Manager;

public interface EmployeeDAO {
	
	// C
	Employee createEmployee(Employee employee);
	
	// R
	Employee getEmployeeById(Employee employee); // pass in Employee with ID only
	Employee getEmployeeByUsername(Employee employee); // pass in Employee with username only
	Employee getEmployeeByFullName(Employee employee); // pass in Employee with firstName & lastName
	List<Employee> getEmployeesByFirstName(Employee employee); // pass in Employee with first name only
	List<Employee> getEmployeesByDivision(Manager manager); // pass in Manager with division only
	List<Employee> getEmployeesByManager(Manager manager); // pass in manager with managerId only
	List<Employee> getAllEmployees();
	
	// U
	Employee updateEmployee(Employee employee);
	
	// D
	boolean deleteEmployee(Employee employee);

}
