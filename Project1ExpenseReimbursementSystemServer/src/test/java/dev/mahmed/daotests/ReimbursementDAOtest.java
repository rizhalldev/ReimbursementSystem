package dev.mahmed.daotests;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Scanner;

import org.junit.Test;

import dev.mahmed.daos.ReimbursementDAO;
import dev.mahmed.daos.ReimbursementDAOmaria;
import dev.mahmed.entities.Employee;
import dev.mahmed.entities.Manager;
import dev.mahmed.entities.Reimbursement;

public class ReimbursementDAOtest {

	static Scanner keyboard = new Scanner(System.in);
	ReimbursementDAO rdao = new ReimbursementDAOmaria();
	
	@Test
	public void createReimbursement() {
		Reimbursement r = new Reimbursement();
		r.setAmountRequested(400);
		r.setStatus("Pending");
		r.setCategory("Certification");
		r.setAmountPaid(0);
		r.setEmployeeId(6);
		Date d = new Date(System.currentTimeMillis());
		r.setDate(d);
		r.setDetails("Please give me some money for new battle certification.");
		rdao.createReimbursement(r);
	}
	
	@Test
	public void getReimbursementById() {
		System.out.println(rdao.getReimbursementById(3));
	}
	
	@Test
	public void getReimbursementsByManager() {
		Manager manager = new Manager();
		manager.setManagerId(2);
		System.out.println(rdao.getReimbursementByManager(manager));
	}
	
	@Test
	public void getReimbursementByDivision() {
		System.out.println(rdao.getReimbursementByDivision("upper"));
	}
	
	@Test
	public void getReimbursementsByEmployee() {
		Employee emp = new Employee();
		emp.setEmployeeId(6);
		System.out.println(rdao.getReimbursementsByEmployee(emp));
	}
	
	@Test
	public void getReimbursementsByStatus() {
		System.out.println(rdao.getReimbursementByStatus("Partial"));
	}
	
	@Test
	public void getAllReimbursements() {
		System.out.println(rdao.getAllReimbursements());
	}
	
	@Test
	public void updateReimbursement() {
		Reimbursement r = rdao.getReimbursementById(1);
		r.setAmountPaid(20);
		r.setStatus("Partial");
		r.setDetails(r.getDetails() + " -Here's 20 bucks for your stupid lard.");
		System.out.println(rdao.updateReimbursement(r));
	}

}
