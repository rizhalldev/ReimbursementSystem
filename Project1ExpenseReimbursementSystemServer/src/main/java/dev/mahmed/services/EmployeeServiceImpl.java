package dev.mahmed.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import dev.mahmed.daos.EmployeeDAO;
import dev.mahmed.daos.EmployeeDAOmaria;
import dev.mahmed.daos.ManagerDAO;
import dev.mahmed.daos.ManagerDAOmaria;
import dev.mahmed.daos.ReimbursementDAO;
import dev.mahmed.daos.ReimbursementDAOmaria;
import dev.mahmed.entities.Employee;
import dev.mahmed.entities.Manager;
import dev.mahmed.entities.Reimbursement;



public class EmployeeServiceImpl implements EmployeeService{
	
EmployeeDAO edao = new EmployeeDAOmaria();
ReimbursementDAO rdao = new ReimbursementDAOmaria();
ManagerDAO mdao = new ManagerDAOmaria();

	@Override
	public Employee employeeLogin(Employee employee){
		Employee check = edao.getEmployeeByUsername(employee.getUserName());
		if (check.getUserName().equalsIgnoreCase(employee.getUserName()) && check.getPassword().equals(employee.getPassword()))
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

	@Override
	public List<Reimbursement> getEmployeeReimbursements(Employee employee) {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		reimbursements = rdao.getReimbursementsByEmployee(employee);
		return reimbursements;
	}

	@Override
	public Manager getEmployeeManager(Employee employee) {
		Manager manager = mdao.getManagerByEmployee(employee);
		return manager;
	}

	@Override
	public Reimbursement cancelReimbursement(int id) {
		Reimbursement reimbursement = rdao.getReimbursementById(id);
		reimbursement.setStatus("Canceled");
		rdao.updateReimbursement(reimbursement);
		System.out.println(reimbursement);
		return reimbursement;
	}

	@Override
	public Reimbursement submitReimbursement(Reimbursement reimbursement) {
		Date d = new Date(System.currentTimeMillis());
		reimbursement.setDate(d);
		reimbursement = rdao.createReimbursement(reimbursement);
		return reimbursement;
	}

}
