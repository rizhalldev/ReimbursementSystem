package dev.mahmed.servicetests;

import static org.junit.Assert.*;

import org.junit.Test;

import dev.mahmed.entities.Employee;
import dev.mahmed.entities.Reimbursement;
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
	
	@Test
	public void getEmployeeReimbursements() {
		Employee employee = new Employee();
		employee.setUserName("cat");
		employee.setPassword("luck");
		employee = eserv.employeeLogin(employee);
		System.out.println(eserv.getEmployeeReimbursements(employee));
	}
	
	@Test
	public void getEmployeeManager() {
		Employee employee = new Employee();
		employee.setUserName("Snake");
		employee.setPassword("diarrhea");
		employee = eserv.employeeLogin(employee);
		System.out.println(eserv.getEmployeeManager(employee));
	}
	
	@Test
	public void submitRequest() {
		Reimbursement r = new Reimbursement();
		r.setCategory("Travel");
		r.setAmountRequested(2400);
		r.setEmployeeId(7);
		r.setDetails("This weekend I traveled to and from Wutai for a business trip. I stayed there for a total of 3 days. This reimbursement request is made to cover the plane, hotel, and food expenses during the trip.");
		Reimbursement r2 = eserv.submitReimbursement(r);
		System.out.println(r2);
	}

}
