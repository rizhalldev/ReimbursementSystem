package dev.mahmed.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dev.mahmed.entities.Employee;
import dev.mahmed.entities.Manager;
import dev.mahmed.utils.ConnectionUtil;

public class ManagerDAOmaria implements ManagerDAO{

	public Manager createManager(Manager manager) {

		try (Connection conn = ConnectionUtil.createConnection()) {
			String sql = "INSERT INTO project1reimbursements.MANAGER VALUES(?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1,0);
			ps.setString(2, manager.getFirstName());
			ps.setString(3, manager.getLastName());
			ps.setString(4, manager.getDivision());
			ps.setString(5, manager.getUsername());
			ps.setString(6, manager.getPassword());
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			
			int key = rs.getInt("MANAGER_ID");
			manager.setManagerId(key);
			return manager;
			
			
		} catch (SQLException e) {
			return null;
		}
	}

	public Manager getManagerbyId(int id) {
		try (Connection conn = ConnectionUtil.createConnection()) {
			String sql = "SELECT * FROM project1reimbursements.MANAGER WHERE MANAGER_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			Manager man = new Manager();
			man.setManagerId(rs.getInt("MANAGER_ID"));
			man.setFirstName(rs.getString("FIRST_NAME"));
			man.setLastName(rs.getString("LAST_NAME"));
			man.setDivision(rs.getString("DIVISION"));
			man.setUsername(rs.getString("USERNAME"));
			man.setPassword(rs.getString("PASSWORD"));
			return man;
		} catch (SQLException e) {
			return null;
		}
	}

	public Manager getManagerByUsername(String username) {
		try (Connection conn = ConnectionUtil.createConnection()) {
			String sql = "SELECT * FROM project1reimbursements.MANAGER WHERE USERNAME = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			Manager man = new Manager();
			man.setManagerId(rs.getInt("MANAGER_ID"));
			man.setFirstName(rs.getString("FIRST_NAME"));
			man.setLastName(rs.getString("LAST_NAME"));
			man.setDivision(rs.getString("DIVISION"));
			man.setUsername(rs.getString("USERNAME"));
			man.setPassword(rs.getString("PASSWORD"));
			return man;
		} catch (SQLException e) {
			return null;
		}
	}

	public Manager getManagerByFullName(String firstName, String lastName) {
		try (Connection conn = ConnectionUtil.createConnection()) {
			String sql = "SELECT * FROM project1reimbursements.MANAGER WHERE FIRST_NAME = ? AND LAST_NAME = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			Manager man = new Manager();
			man.setManagerId(rs.getInt("MANAGER_ID"));
			man.setFirstName(rs.getString("FIRST_NAME"));
			man.setLastName(rs.getString("LAST_NAME"));
			man.setDivision(rs.getString("DIVISION"));
			man.setUsername(rs.getString("USERNAME"));
			man.setPassword(rs.getString("PASSWORD"));
			return man;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public Manager getManagerByEmployee(Employee employee) {
		try (Connection conn = ConnectionUtil.createConnection()) {
			String sql = "SELECT MANAGER.DIVISION,MANAGER.FIRST_NAME, MANAGER.LAST_NAME, MANAGER.MANAGER_ID, MANAGER.PASSWORD, MANAGER.USERNAME FROM project1reimbursements.MANAGER INNER JOIN project1reimbursements.EMPLOYEE ON MANAGER.MANAGER_ID = EMPLOYEE.MANAGER_ID AND EMPLOYEE_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, employee.getEmployeeId());
			ResultSet rs = ps.executeQuery();
			rs.next();
			Manager man = new Manager();
			man.setManagerId(rs.getInt("MANAGER_ID"));
			man.setFirstName(rs.getString("FIRST_NAME"));
			man.setLastName(rs.getString("LAST_NAME"));
			man.setDivision(rs.getString("DIVISION"));
			man.setUsername(rs.getString("USERNAME"));
			man.setPassword(rs.getString("PASSWORD"));
			return man;
		} catch (SQLException e) {
			return null;
		}
	}

	public List<Manager> getManagersByDivision(String division) {
		try (Connection conn = ConnectionUtil.createConnection()) {
			String sql = "SELECT * FROM project1reimbursements.MANAGER WHERE DIVISION = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, division);
			ResultSet rs = ps.executeQuery();
			
			List<Manager> managers = new ArrayList<Manager>();
			
			while (rs.next()) {
				Manager man = new Manager();
				man.setManagerId(rs.getInt("MANAGER_ID"));
				man.setFirstName(rs.getString("FIRST_NAME"));
				man.setLastName(rs.getString("LAST_NAME"));
				man.setDivision(rs.getString("DIVISION"));
				man.setUsername(rs.getString("USERNAME"));
				man.setPassword(rs.getString("PASSWORD"));
				managers.add(man);
			}
			return managers;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public List<Manager> getManagersByFirstName(String firstName) {
		try (Connection conn = ConnectionUtil.createConnection()) {
			String sql = "SELECT * FROM project1reimbursements.MANAGER WHERE FIRST_NAME = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, firstName);
			ResultSet rs = ps.executeQuery();
			
			List<Manager> managers = new ArrayList<Manager>();
			
			while (rs.next()) {
				Manager man = new Manager();
				man.setManagerId(rs.getInt("MANAGER_ID"));
				man.setFirstName(rs.getString("FIRST_NAME"));
				man.setLastName(rs.getString("LAST_NAME"));
				man.setDivision(rs.getString("DIVISION"));
				man.setUsername(rs.getString("USERNAME"));
				man.setPassword(rs.getString("PASSWORD"));
				managers.add(man);
			}
			return managers;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public List<Manager> getAllManagers() {
		try (Connection conn = ConnectionUtil.createConnection()) {
			String sql = "SELECT * FROM project1reimbursements.MANAGER";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			List<Manager> managers = new ArrayList<Manager>();
			
			while (rs.next()) {
				Manager man = new Manager();
				man.setManagerId(rs.getInt("MANAGER_ID"));
				man.setFirstName(rs.getString("FIRST_NAME"));
				man.setLastName(rs.getString("LAST_NAME"));
				man.setDivision(rs.getString("DIVISION"));
				man.setUsername(rs.getString("USERNAME"));
				man.setPassword(rs.getString("PASSWORD"));
				managers.add(man);
			}
			return managers;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public Manager updateManager(Manager manager) {
		try (Connection conn = ConnectionUtil.createConnection()) {
			String sql = "UPDATE project1reimbursements.MANAGER SET FIRST_NAME = ?, LAST_NAME = ?, DIVISION = ?, USERNAME = ?, PASSWORD = ? WHERE MANAGER_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, manager.getFirstName());
			ps.setString(2, manager.getLastName());
			ps.setString(3, manager.getDivision());
			ps.setString(4, manager.getUsername());
			ps.setString(5,  manager.getPassword());
			ps.setInt(6, manager.getManagerId());
			ps.execute();
			return manager;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public boolean deleteManager(Manager manager) {
		try (Connection conn = ConnectionUtil.createConnection()) {
			
			String sql = "DELETE FROM project1reimbursements.MANAGER WHERE MANAGER_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, manager.getManagerId());
			boolean attempt = ps.execute();
			
			return !attempt;
			
		} catch (SQLException e) {
			return false;
		}
	}


}
