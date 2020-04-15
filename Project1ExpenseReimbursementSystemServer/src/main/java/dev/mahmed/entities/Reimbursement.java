package dev.mahmed.entities;

import java.sql.Date;

public class Reimbursement {

	private int reimbursementId;
	private int amountRequested;
	private int amountPaid;
	private String status;
	private String category;
	public String details;
	private int employeeId;
	private Date date;
	
	public Reimbursement() { }

	public int getReimbursementId() {
		return reimbursementId;
	}

	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}

	public int getAmountRequested() {
		return amountRequested;
	}

	public void setAmountRequested(int amountRequested) {
		this.amountRequested = amountRequested;
	}

	public int getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(int amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbursementId=" + reimbursementId + ", amountRequested=" + amountRequested
				+ ", amountPaid=" + amountPaid + ", status=" + status + ", category=" + category + ", details="
				+ details + ", employeeId=" + employeeId + ", date=" + date + "]";
	}
}
