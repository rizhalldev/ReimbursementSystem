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

}
