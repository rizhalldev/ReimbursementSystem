package dev.mahmed.servicetests;

import static org.junit.Assert.*;

import org.junit.Test;

import dev.mahmed.entities.Manager;
import dev.mahmed.services.ManagerService;
import dev.mahmed.services.ManagerServiceImpl;

public class ManagerServicetest {

	ManagerService mserv = new ManagerServiceImpl();
	
	@Test
	public void managerLogin() {
		Manager manager = new Manager();
		manager.setUsername("cait");
		manager.setPassword("sith");
		Manager man2 = mserv.managerLogin(manager);
		System.out.println(man2);
	}
	
	@Test
	public void getManagerEmployees() {
		Manager manager = new Manager();
		manager.setUsername("Gyahaha");
		manager.setPassword("heyalright");
		manager = mserv.managerLogin(manager);
		System.out.println(manager);
		System.out.println(mserv.getManagerEmployees(manager));
	}

}
