package dev.mahmed.daos;

import dev.mahmed.entities.Manager;

public interface ManagerDAO {
	
	// C
	Manager createManager(Manager manager);
	
	// R -- Manager returns null if unfound, List<Manager> returns empty list if unfound
	Manager getManagerbyId(Manager manager); // pass in Manager with a managerId only
	Manager getManagerByUsername(Manager manager); // pass in Manager with a username only
	Manager getManagerByFullName(Manager manager); // pass in Manager with firstName & lastName
	<List>Manager getManagersByDivision(Manager manager); // pass in manager object with division filled out
	<List>Manager getManagersByFirstName(Manager manager); // pass in manager with a firstName object
	<List>Manager getAllManagers();
	
	// U
	Manager updateManager(Manager manager);
	
	// D
	boolean deleteManager(Manager manager);

}
