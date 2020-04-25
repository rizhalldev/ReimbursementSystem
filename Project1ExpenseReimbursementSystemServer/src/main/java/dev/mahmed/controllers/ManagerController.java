package dev.mahmed.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dev.mahmed.entities.Employee;
import dev.mahmed.entities.Manager;
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
		String managerJson = gson.toJson(manager);
		session.setAttribute("manager", managerJson);
	}

public void getLoggedManager(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
	String manager = (String) request.getSession().getAttribute("manager");
	response.getWriter().append(manager);
}

}
