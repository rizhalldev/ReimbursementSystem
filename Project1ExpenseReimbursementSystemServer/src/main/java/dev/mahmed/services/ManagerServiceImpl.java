package dev.mahmed.services;

import java.util.List;

import dev.mahmed.daos.EmployeeDAO;
import dev.mahmed.daos.EmployeeDAOmaria;
import dev.mahmed.daos.ManagerDAO;
import dev.mahmed.daos.ManagerDAOmaria;
import dev.mahmed.entities.Employee;
import dev.mahmed.entities.Manager;

public class ManagerServiceImpl implements ManagerService{
	
ManagerDAO mdao = new ManagerDAOmaria();
EmployeeDAO edao = new EmployeeDAOmaria();

	@Override
	public Manager managerLogin(Manager manager) {
		Manager check = mdao.getManagerByUsername(manager.getUsername());
		if (check.getUsername().equals(manager.getUsername()) && check.getPassword().equals(manager.getPassword()))
			return check;
		else
		{
			Manager bad = new Manager();
			bad.setDivision("");
			bad.setFirstName("");
			bad.setLastName("");
			bad.setManagerId(0);
			bad.setPassword("");
			bad.setUsername("");
			return bad;
		}
	}

	@Override
	public List<Employee> getManagerEmployees(Manager manager) {
		return edao.getEmployeesByManager(manager);
	}

}
