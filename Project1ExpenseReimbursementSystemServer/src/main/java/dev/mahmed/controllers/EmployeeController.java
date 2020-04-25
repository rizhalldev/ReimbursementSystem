package dev.mahmed.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dev.mahmed.entities.Employee;
import dev.mahmed.entities.Manager;
import dev.mahmed.entities.Reimbursement;
import dev.mahmed.services.EmployeeService;
import dev.mahmed.services.EmployeeServiceImpl;

public class EmployeeController {
	
	EmployeeService eserv = new EmployeeServiceImpl();
	
	public void employeeLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();
		Gson gson = new Gson();
		// Initialize json so past logins don't get sent by mistake
		session.setAttribute("employee", "{\"employeeId\":0}");
		
		String body = request.getReader().lines().reduce("", (accumulator,actual) ->accumulator+actual);
		
		Employee employee = gson.fromJson(body, Employee.class);
		employee = eserv.employeeLogin(employee);
		String employeeJson = gson.toJson(employee);
		session.setAttribute("employee", employeeJson);
	}
	
	public void getLoggedEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String employee = (String) request.getSession().getAttribute("employee");
		response.getWriter().append(employee);
	}
	
	public void getEmployeeReimbursements(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Gson gson = new Gson();
		String employeeJson = (String) session.getAttribute("employee");
		Employee employee = gson.fromJson(employeeJson, Employee.class);
		List<Reimbursement> reimbursements = eserv.getEmployeeReimbursements(employee);
		String json = gson.toJson(reimbursements);
		session.setAttribute("reimbursements", json);
		PrintWriter pw = response.getWriter();
		pw.append(json);
	}
	
	public void getEmployeeManager(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Gson gson = new Gson();
		String employeeJson = (String) session.getAttribute("employee");
		Employee employee = gson.fromJson(employeeJson, Employee.class);
		Manager manager = eserv.getEmployeeManager(employee);
		String json = gson.toJson(manager);
		session.setAttribute("manager", json);
		PrintWriter pw = response.getWriter();
		pw.append(json);
	}
	
	public void getSessionReimbursements(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String reimbursementJson = (String) session.getAttribute("reimbursements");
		PrintWriter pw = response.getWriter();
		pw.append(reimbursementJson);
	}
	
	public void cancelReimbursement(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Gson gson = new Gson();
		int anId = Integer.parseInt(request.getParameter("id"));
		Reimbursement reimbursement = eserv.cancelReimbursement(anId);
		PrintWriter pw = response.getWriter();
		String reimbursementJson = gson.toJson(reimbursement);
		pw.append(reimbursementJson);
	}
	
	public void submitRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Gson gson = new Gson();
		String employeeJson = (String) session.getAttribute("employee");
		Employee employee = gson.fromJson(employeeJson, Employee.class);
		
		int amountRequested = Integer.parseInt(request.getParameter("amount"));
		String category = request.getParameter("category");
		String details = request.getParameter("details");
		details = String.format("%1$-200s", details);
		int employeeId = employee.getEmployeeId();
		
		Reimbursement reimbursement = new Reimbursement();
		reimbursement.setAmountRequested(amountRequested);
		reimbursement.setCategory(category);
		reimbursement.setDetails(details);
		reimbursement.setEmployeeId(employeeId);
		Reimbursement r2 = eserv.submitReimbursement(reimbursement);
		
		PrintWriter pw = response.getWriter();
		pw.append(gson.toJson(r2));
	}

}
