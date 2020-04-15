package dev.mahmed.services;

import java.util.List;

import dev.mahmed.entities.Employee;
import dev.mahmed.entities.Reimbursement;

public interface EmployeeService {
	// Get username & password and do a check
	Employee employeeLogin(Employee employee);
	
	// Show amountRequested & status
	List<Reimbursement> viewOwnReimbursements(Employee employee);
	List<Reimbursement> viewOwnPendingReimbursements();
	List<Reimbursement> viewOwnNonpendingReimbursements();
	
	// For showing more details about an individual reimbursement, chosen from list<r>
	Reimbursement viewReimbursement(Reimbursement reimbursement);
	
	Reimbursement cancelReimbursement(Reimbursement reimbursement);
	
	// Pass Reimbursement with:, add employeeId, get amountRequested (input), get category (select), get details (input, up to 200 chars),date=CURRENTDATE, AmountPaid=0, status="pending"
	Reimbursement createReimbursement(Reimbursement reimbursement);
	

}
