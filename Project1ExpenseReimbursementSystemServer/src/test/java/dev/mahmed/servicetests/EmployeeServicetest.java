package dev.mahmed.servicetests;

import static org.junit.Assert.*;

import org.junit.Test;

import dev.mahmed.entities.Employee;
import dev.mahmed.services.EmployeeService;
import dev.mahmed.services.EmployeeServiceImpl;

public class EmployeeServicetest {

	EmployeeService eserv = new EmployeeServiceImpl();
	
	@Test
	public void EmployeeLogin() {
		Employee employee = new Employee();
		employee.setUserName("cat");
		employee.setPassword("luck");
		System.out.println(eserv.employeeLogin(employee));
	}

}
