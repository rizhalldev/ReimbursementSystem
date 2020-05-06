package dev.mahmed.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dev.mahmed.entities.Employee;
import dev.mahmed.entities.Manager;
import dev.mahmed.entities.Reimbursement;
import dev.mahmed.services.ManagerService;
import dev.mahmed.services.ManagerServiceImpl;

public class ManagerController {
	
	ManagerService mserv = new ManagerServiceImpl();
	
public void managerLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();
		Gson gson = new Gson();
		// Initialize json so past logins don't get sent by mistake
		session.setAttribute("manager", "{\"managerId\":0}");
		
		String body = request.getReader().lines().reduce("", (accumulator,actual) ->accumulator+actual);
		
		Manager manager = gson.fromJson(body, Manager.class);
		manager = mserv.managerLogin(manager);
		manager.setPassword("");
		String managerJson = gson.toJson(manager);
		session.setAttribute("manager", managerJson);
	}

public void getLoggedManager(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
	String manager = (String) request.getSession().getAttribute("manager");
	response.getWriter().append(manager);
}

public void getManagerEmployees(HttpServletRequest request, HttpServletResponse response) throws IOException {
	HttpSession session = request.getSession();
	Gson gson = new Gson();
	String managerJson = (String) session.getAttribute("manager");
	Manager manager = gson.fromJson(managerJson, Manager.class);
	List<Employee> employees = mserv.getManagerEmployees(manager);
	String employeesJson = gson.toJson(employees);
	session.setAttribute("employees", employeesJson);
	PrintWriter pw = response.getWriter();
	pw.append(employeesJson);
}

public void getManagerReimbursements(HttpServletRequest request, HttpServletResponse response) throws IOException {
	HttpSession session = request.getSession();
	Gson gson = new Gson();
	String managerJson = (String) session.getAttribute("manager");
	Manager manager = gson.fromJson(managerJson, Manager.class);
	List<Reimbursement> reimbursements = mserv.getManagerReimbursements(manager);
	PrintWriter pw = response.getWriter();
	String json = gson.toJson(reimbursements);
	session.setAttribute("managerReimbursements", json);
	pw.append(json);
}

public void getDivisionReimbursements(HttpServletRequest request, HttpServletResponse response) throws IOException {
	HttpSession session = request.getSession();
	Gson gson = new Gson();
	String managerJson = (String) session.getAttribute("manager");
	Manager manager = gson.fromJson(managerJson, Manager.class);
	List<Reimbursement> reimbursements = mserv.getDivisionReimbursements(manager);
	PrintWriter pw = response.getWriter();
	String json = gson.toJson(reimbursements);
	session.setAttribute("divisionReimbursements", json);
	pw.append(json);
}

public void getReimbursementFromManager(HttpServletRequest request, HttpServletResponse response) throws IOException {
	HttpSession session = request.getSession();
	Gson gson = new Gson();
	PrintWriter pw = response.getWriter();
	int index = Integer.parseInt(request.getParameter("index"));
	String managerJson = (String) session.getAttribute("manager");
	Manager manager = gson.fromJson(managerJson, Manager.class);
	List<Reimbursement> reimbursements = mserv.getManagerReimbursements(manager);
	Reimbursement reimbursement = reimbursements.get(index);
	String json = gson.toJson(reimbursement);
	session.setAttribute("currentReimbursement", json);
	pw.append(json);
}

public void getReimbursementFromDivision(HttpServletRequest request, HttpServletResponse response) throws IOException {
	HttpSession session = request.getSession();
	Gson gson = new Gson();
	PrintWriter pw = response.getWriter();
	int index = Integer.parseInt(request.getParameter("index"));
	String managerJson = (String) session.getAttribute("manager");
	Manager manager = gson.fromJson(managerJson, Manager.class);
	List<Reimbursement> reimbursements = mserv.getDivisionReimbursements(manager);
	Reimbursement reimbursement = reimbursements.get(index);
	String json = gson.toJson(reimbursement);
	session.setAttribute("currentReimbursement", json);
	pw.append(json);
}

public void getEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
	HttpSession session = request.getSession();
	Gson gson = new Gson();
	PrintWriter pw = response.getWriter();
	int id = Integer.parseInt(request.getParameter("id"));
	Employee employee = mserv.getEmployee(id);
	String json = gson.toJson(employee);
	session.setAttribute("currentEmployee", json);
	pw.append(json);
}

public void settleReimbursement(HttpServletRequest request, HttpServletResponse response) throws IOException {
	HttpSession session = request.getSession();
	Gson gson = new Gson();
	String reimbJson = (String) session.getAttribute("currentReimbursement");
	Reimbursement reimbursement = gson.fromJson(reimbJson, Reimbursement.class);
	String empJson = (String) session.getAttribute("currentEmployee");
	Employee employee = gson.fromJson(empJson, Employee.class);
	int amount = Integer.parseInt(request.getParameter("amount"));
	String details = request.getParameter("details");
	reimbursement.setAmountPaid(amount);
	Reimbursement newReimb = mserv.settleReimbursement(reimbursement, employee, details);
	String reimbursementJson = gson.toJson(newReimb);
	response.getWriter().append(reimbursementJson);
}

}
