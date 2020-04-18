package dev.mahmed.services;

import dev.mahmed.daos.EmployeeDAO;
import dev.mahmed.daos.EmployeeDAOmaria;
import dev.mahmed.entities.Employee;



public class EmployeeServiceImpl implements EmployeeService{
	
EmployeeDAO edao = new EmployeeDAOmaria();

	@Override
	public Employee employeeLogin(Employee employee) {
		Employee check = edao.getEmployeeByUsername(employee.getUserName());
		if (check.getUserName().equals(employee.getUserName()) && check.getPassword().equals(employee.getPassword()))
			return check;
		else
		{
			Employee bad = new Employee();
			bad.setEmployeeId(0);
			bad.setBalance(0);
			bad.setManagerId(0);
			bad.setFirstName("");
			bad.setLastName("");
			bad.setPassword("");
			bad.setUserName("");
			return bad;
		}
	}

}
