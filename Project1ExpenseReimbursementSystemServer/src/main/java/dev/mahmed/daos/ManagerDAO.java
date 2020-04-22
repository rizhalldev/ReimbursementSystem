package dev.mahmed.daos;

import java.util.List;

import dev.mahmed.entities.Employee;
import dev.mahmed.entities.Manager;

public interface ManagerDAO {
	
	// C
	Manager createManager(Manager manager);
	
	// R -- Manager returns null if unfound, List<Manager> returns empty list if unfound
	Manager getManagerbyId(int id); // pass in Manager with a managerId only
	Manager getManagerByEmployee(Employee employee);
	Manager getManagerByUsername(String username); // pass in Manager with a username only
	Manager getManagerByFullName(String firstName, String lastName); // pass in Manager with firstName & lastName
	List<Manager> getManagersByDivision(String division); // pass in manager object with division filled out
	List<Manager> getManagersByFirstName(String firstName); // pass in manager with a firstName object
	List<Manager> getAllManagers();
	
	// U
	Manager updateManager(Manager manager);
	
	// D
	boolean deleteManager(Manager manager);

}
