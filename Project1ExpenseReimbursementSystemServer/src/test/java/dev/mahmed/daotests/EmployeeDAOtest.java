package dev.mahmed.daotests;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

import dev.mahmed.daos.EmployeeDAO;
import dev.mahmed.daos.EmployeeDAOmaria;
import dev.mahmed.entities.Employee;
import dev.mahmed.entities.Manager;

public class EmployeeDAOtest {

	EmployeeDAO edao = new EmployeeDAOmaria();
	static Scanner keyboard = new Scanner(System.in);
	
	@Test
	public void createEmployee() {
		Employee employee = new Employee();
		employee.setFirstName(keyboard.nextLine());
		employee.setLastName(keyboard.nextLine());
		employee.setBalance(0);
		employee.setUserName(keyboard.nextLine());
		employee.setPassword(keyboard.nextLine());
		employee.setManagerId(3);
		edao.createEmployee(employee);
	}
	
	@Test
	public void getEmployeeById() {
		System.out.println(edao.getEmployeeById(Integer.parseInt(keyboard.nextLine())));
	}
	
	@Test
	public void getEmployeeByUsername() {
		System.out.println(edao.getEmployeeByUsername(keyboard.nextLine()));
	}
	
	@Test
	public void getEmployeeByFullName() {
		System.out.println(edao.getEmployeeByFullName(keyboard.nextLine(), keyboard.nextLine()));
	}
	
	@Test
	public void getEmployeesByFirstName() {
		System.out.println(edao.getEmployeesByFirstName(keyboard.nextLine()));
	}
	
	@Test
	public void getEmployeesByDivision() {
		System.out.println(edao.getEmployeesByDivision(keyboard.nextLine()));
	}
	
	@Test
	public void getEmployeesByManager() {
		Manager manager = new Manager();
		manager.setManagerId(1);
		System.out.println(edao.getEmployeesByManager(manager));
	}
	
	@Test
	public void getAllEmployees() {
		System.out.println(edao.getAllEmployees());
	}
	
	@Test
	public void updateEmployee() {
		Employee employee = edao.getEmployeeById(2);
		employee.setBalance(200);
		System.out.println(employee);
		System.out.println(edao.updateEmployee(employee));
	}

}
