package dev.mahmed.services;

import java.util.List;

import dev.mahmed.entities.Manager;
import dev.mahmed.entities.Reimbursement;

public interface ManagerLogin {
	
	// get username & password
	Manager managerLogin(Manager manager);
	
	List<Reimbursement> viewAllReimbursements();
	List<Reimbursement> viewReimbursementsByDivision(Manager manager);
	
	// From an above list view, "select" a reimbursement to view, this view gives update options
	Reimbursement viewReimbursement(Reimbursement reimbursemest);
	
	// With a base reimbursement, get amountPaid, edit details, automatically update status based on paid, then update
	Reimbursement updateReimbursement(Reimbursement reimbursement);

}
