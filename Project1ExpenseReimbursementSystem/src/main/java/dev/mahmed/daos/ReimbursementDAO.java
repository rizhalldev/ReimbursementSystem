package dev.mahmed.daos;

import java.util.List;

import dev.mahmed.entities.Employee;
import dev.mahmed.entities.Manager;
import dev.mahmed.entities.Reimbursement;

public interface ReimbursementDAO {
	
	// C
	Reimbursement createReimbursement(Reimbursement reimbursement); // Pass Reimbursement with employeeId, amountRequested, AmountRequested, Category, date=CURRENTDATE, AmountPaid=0, status="pending"
	
	// R
	Reimbursement getReimbursementById(Reimbursement reimbursement); // Pass Reimbursement with reimbursementId
	Reimbursement getReimbursementByEmployee(Employee employee); // Pass Employee with employeeId
	Reimbursement getReimbursementByManager(Manager manager); // Pass Manager with managerId
	Reimbursement getReimbursementByDivision(Manager manager); // Pass Manager with division
	List<Reimbursement> getReimbursementByStatus(Reimbursement reimbursement); // Pass reimbursement with status, probably "pending"
	List<Reimbursement> getAllReimbursements();
	
	// U
	Reimbursement updateReimbursement(Reimbursement reimbursement); // Pass full reimbursement file
	
	// D
	boolean deleteReimbursement(Reimbursement reimbursement);

}
