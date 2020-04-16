package dev.mahmed.daotests;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

import dev.mahmed.daos.ManagerDAO;
import dev.mahmed.daos.ManagerDAOmaria;
import dev.mahmed.entities.Manager;

public class ManagerDAOtest {

	ManagerDAO mdao = new ManagerDAOmaria();
	static Scanner keyboard = new Scanner(System.in);
	
	@Test
	public void createManager() {
		Manager manager = new Manager();
		System.out.println("Enter first name:");
		manager.setFirstName(keyboard.nextLine());
		System.out.println("Enter last name:");
		manager.setLastName(keyboard.nextLine());
		System.out.println("Enter division:");
		manager.setDivision(keyboard.nextLine());
		System.out.println("Enter username:");
		manager.setUsername(keyboard.nextLine());
		System.out.println("Enter password:");
		manager.setPassword(keyboard.nextLine());
		
		mdao.createManager(manager);
	}
	
	@Test
	public void getManagerById() {
		System.out.println(mdao.getManagerbyId(3));
	}
	
	@Test
	public void getManagerByUsername() {
		System.out.println("Enter username:");
		System.out.println(mdao.getManagerByUsername(keyboard.nextLine()));
	}
	
	@Test
	public void getManagerByFullName() {
		System.out.println("Enter first & last name:");
		System.out.println(mdao.getManagerByFullName(keyboard.nextLine(), keyboard.nextLine()));
	}
	
	@Test
	public void getManagerByDivision() {
		System.out.println(mdao.getManagersByDivision("Weapons"));
	}
	
	@Test
	public void getManagersByFirstName() {
		System.out.println(mdao.getManagersByFirstName("President"));
	}
	
	@Test
	public void getAllManagers() {
		System.out.println(mdao.getAllManagers());
	}

}
