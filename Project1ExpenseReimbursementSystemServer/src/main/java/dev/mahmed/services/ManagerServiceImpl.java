package dev.mahmed.services;

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

public class ManagerServiceImpl implements ManagerService{
	
ManagerDAO mdao = new ManagerDAOmaria();
EmployeeDAO edao = new EmployeeDAOmaria();
ReimbursementDAO rdao = new ReimbursementDAOmaria();

	@Override
	public Manager managerLogin(Manager manager) {
		Manager check = mdao.getManagerByUsername(manager.getUsername());
		if (check.getUsername().equalsIgnoreCase(manager.getUsername()) && check.getPassword().equals(manager.getPassword()))
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

	@Override
	public List<Reimbursement> getManagerReimbursements(Manager manager) {
		return rdao.getReimbursementByManager(manager);
	}

	@Override
	public List<Reimbursement> getDivisionReimbursements(Manager manager) {
		return rdao.getReimbursementByDivision(manager.getDivision());
	}

	@Override
	public Employee getEmployee(int id) {
		return edao.getEmployeeById(id);
	}

	@Override
	public Reimbursement settleReimbursement(Reimbursement reimbursement, Employee employee, String details) {
		details = String.format("%1$-200s", details);
		String newDetails = reimbursement.getDetails() + details;
		if (reimbursement.getAmountPaid() == 0)
			reimbursement.setStatus("Declined");
		else if (reimbursement.getAmountPaid() == reimbursement.getAmountRequested())
			reimbursement.setStatus("Granted");
		else
			reimbursement.setStatus("Partial");
		reimbursement.setDetails(newDetails);
		employee.setBalance(employee.getBalance() + reimbursement.getAmountPaid());
		edao.updateEmployee(employee);
		reimbursement = rdao.updateReimbursement(reimbursement);
		return reimbursement;
	}

}
