package dev.mahmed.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.mahmed.controllers.EmployeeController;
import dev.mahmed.controllers.ManagerController;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DispatcherServlet() {
		super();
	}

	EmployeeController econtrol = new EmployeeController();
	ManagerController mcontrol = new ManagerController();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);
		switch (uri) {
		
		case "/Project1ExpenseReimbursementSystemServer/api/managerlogin":
			mcontrol.managerLogin(request, response);
			break;
		case "/Project1ExpenseReimbursementSystemServer/api/getloggedmanager":
			mcontrol.getLoggedManager(request, response);
			break;
			
		case "/Project1ExpenseReimbursementSystemServer/api/employeelogin":
			econtrol.employeeLogin(request, response);
			break;
		case "/Project1ExpenseReimbursementSystemServer/api/getloggedemployee":
			econtrol.getLoggedEmployee(request, response);
			break;
		case "/Project1ExpenseReimbursementSystemServer/api/getemployeereimbursements":
			econtrol.getEmployeeReimbursements(request, response);
			break;
		case "/Project1ExpenseReimbursementSystemServer/api/getemployeemanager":
			econtrol.getEmployeeManager(request, response);
			break;
		case "/Project1ExpenseReimbursementSystemServer/api/getsessionreimbursements":
			econtrol.getSessionReimbursements(request, response);
			break;
		case "/Project1ExpenseReimbursementSystemServer/api/cancelreimbursement":
			econtrol.cancelReimbursement(request, response);
			break;
		case "/Project1ExpenseReimbursementSystemServer/api/submitrequest":
			econtrol.submitRequest(request, response);
			break;
		default:
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
