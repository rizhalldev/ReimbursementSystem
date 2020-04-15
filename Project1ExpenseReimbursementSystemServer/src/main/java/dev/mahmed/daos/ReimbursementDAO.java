package dev.mahmed.daos;

import java.util.List;

import dev.mahmed.entities.Employee;
import dev.mahmed.entities.Manager;
import dev.mahmed.entities.Reimbursement;

public interface ReimbursementDAO {
	
	// C
	Reimbursement createReimbursement(Reimbursement reimbursement); // Pass Reimbursement with employeeId, amountRequested, AmountRequested, Category, date=CURRENTDATE, AmountPaid=0, status="pending"
	
	// R
	Reimbursement getReimbursementById(int id); // Pass Reimbursement with reimbursementId
	List<Reimbursement> getReimbursementByManager(Manager manager); // Pass Manager with managerId
	List<Reimbursement> getReimbursementByDivision(String division); // Pass Manager with division
	List<Reimbursement> getReimbursementsByEmployee(Employee employee); // Pass Employee with employeeId
	List<Reimbursement> getReimbursementByStatus(String status); // Pass reimbursement with status, probably "pending"
	List<Reimbursement> getAllReimbursements();
	
	// U
	Reimbursement updateReimbursement(Reimbursement reimbursement); // Pass full reimbursement file
	
	// D - NOT IMPLEMENTED/UNNEEDED
	boolean deleteReimbursement(Reimbursement reimbursement);

}
