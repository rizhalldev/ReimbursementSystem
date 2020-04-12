package dev.mahmed.entities;

public class Reimbursement {

	private int reimbursementId;
	private int amountRequested;
	private int amountPaid;
	private String status;
	private String category;
	private int employeeId;
	
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

	@Override
	public String toString() {
		return "Reimbursement [reimbursementId=" + reimbursementId + ", amountRequested=" + amountRequested
				+ ", amountPaid=" + amountPaid + ", status=" + status + ", category=" + category + ", employeeId="
				+ employeeId + "]";
	}
	
}
