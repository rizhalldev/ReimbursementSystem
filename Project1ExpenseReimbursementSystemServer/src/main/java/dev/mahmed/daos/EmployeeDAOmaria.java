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

public class EmployeeDAOmaria implements EmployeeDAO{

	@Override
	public Employee createEmployee(Employee employee) {
		try (Connection conn = ConnectionUtil.createConnection()) {
			String sql = "INSERT INTO project1reimbursements.EMPLOYEE VALUES(?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1,0);
			ps.setString(2, employee.getFirstName());
			ps.setString(3, employee.getLastName());
			ps.setInt(4, employee.getBalance());
			ps.setString(5, employee.getUserName());
			ps.setString(6, employee.getPassword());
			ps.setInt(7, employee.getManagerId());
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int key = rs.getInt("EMPLOYEE_ID");
			employee.setEmployeeId(key);
			return employee;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public Employee getEmployeeById(int id) {
		try (Connection conn = ConnectionUtil.createConnection()) {
			String sql = "SELECT * FROM project1reimbursements.EMPLOYEE WHERE EMPLOYEE_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Employee employee = new Employee();
			employee.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
			employee.setFirstName(rs.getString("FIRST_NAME"));
			employee.setLastName(rs.getString("LAST_NAME"));
			employee.setBalance(rs.getInt("BALANCE"));
			employee.setUserName(rs.getString("USERNAME"));
			employee.setPassword(rs.getString("PASSWORD"));
			employee.setManagerId(rs.getInt("MANAGER_ID"));
			return employee;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public Employee getEmployeeByUsername(String username) {
		try (Connection conn = ConnectionUtil.createConnection()) {
			String sql = "SELECT * FROM project1reimbursements.EMPLOYEE WHERE USERNAME = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Employee employee = new Employee();
			employee.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
			employee.setFirstName(rs.getString("FIRST_NAME"));
			employee.setLastName(rs.getString("LAST_NAME"));
			employee.setBalance(rs.getInt("BALANCE"));
			employee.setUserName(rs.getString("USERNAME"));
			employee.setPassword(rs.getString("PASSWORD"));
			employee.setManagerId(rs.getInt("MANAGER_ID"));
			return employee;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public Employee getEmployeeByFullName(String firstName, String lastName) {
		try (Connection conn = ConnectionUtil.createConnection()) {
			String sql = "SELECT * FROM project1reimbursements.EMPLOYEE WHERE FIRST_NAME = ? AND LAST_NAME = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Employee employee = new Employee();
			employee.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
			employee.setFirstName(rs.getString("FIRST_NAME"));
			employee.setLastName(rs.getString("LAST_NAME"));
			employee.setBalance(rs.getInt("BALANCE"));
			employee.setUserName(rs.getString("USERNAME"));
			employee.setPassword(rs.getString("PASSWORD"));
			employee.setManagerId(rs.getInt("MANAGER_ID"));
			return employee;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public List<Employee> getEmployeesByFirstName(String firstName) {
		try (Connection conn = ConnectionUtil.createConnection()) {
			String sql = "SELECT * FROM project1reimbursements.EMPLOYEE WHERE FIRST_NAME = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, firstName);
			ResultSet rs = ps.executeQuery();
			List<Employee> employees = new ArrayList<Employee>();
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
				employee.setFirstName(rs.getString("FIRST_NAME"));
				employee.setLastName(rs.getString("LAST_NAME"));
				employee.setBalance(rs.getInt("BALANCE"));
				employee.setUserName(rs.getString("USERNAME"));
				employee.setPassword(rs.getString("PASSWORD"));
				employee.setManagerId(rs.getInt("MANAGER_ID"));
				employees.add(employee);
			}
			return employees;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public List<Employee> getEmployeesByDivision(String division) {
		try (Connection conn = ConnectionUtil.createConnection()) {
			List<Employee> employees = new ArrayList<Employee>();
			String sql = "SELECT * FROM project1reimbursements.EMPLOYEE INNER JOIN project1reimbursements.MANAGER ON EMPLOYEE.MANAGER_ID = MANAGER.MANAGER_ID AND MANAGER.DIVISION = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, division);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
				employee.setFirstName(rs.getString("FIRST_NAME"));
				employee.setLastName(rs.getString("LAST_NAME"));
				employee.setBalance(rs.getInt("BALANCE"));
				employee.setUserName(rs.getString("USERNAME"));
				employee.setPassword(rs.getString("PASSWORD"));
				employee.setManagerId(rs.getInt("MANAGER_ID"));
				employees.add(employee);
			}
			return employees;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public List<Employee> getEmployeesByManager(Manager manager) {
		try (Connection conn = ConnectionUtil.createConnection()) {
			String sql = "SELECT * FROM project1reimbursements.EMPLOYEE WHERE MANAGER_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, manager.getManagerId());
			ResultSet rs = ps.executeQuery();
			List<Employee> employees = new ArrayList<Employee>();
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
				employee.setFirstName(rs.getString("FIRST_NAME"));
				employee.setLastName(rs.getString("LAST_NAME"));
				employee.setBalance(rs.getInt("BALANCE"));
				employee.setUserName(rs.getString("USERNAME"));
				employee.setPassword(rs.getString("PASSWORD"));
				employee.setManagerId(rs.getInt("MANAGER_ID"));
				employees.add(employee);
			}
			return employees;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public List<Employee> getAllEmployees() {
		try (Connection conn = ConnectionUtil.createConnection()) {
			String sql = "SELECT * FROM project1reimbursements.EMPLOYEE";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Employee> employees = new ArrayList<Employee>();
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
				employee.setFirstName(rs.getString("FIRST_NAME"));
				employee.setLastName(rs.getString("LAST_NAME"));
				employee.setBalance(rs.getInt("BALANCE"));
				employee.setUserName(rs.getString("USERNAME"));
				employee.setPassword(rs.getString("PASSWORD"));
				employee.setManagerId(rs.getInt("MANAGER_ID"));
				employees.add(employee);
			}
			return employees;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		try (Connection conn = ConnectionUtil.createConnection()){
			String sql = "UPDATE project1reimbursements.EMPLOYEE SET BALANCE = ? WHERE EMPLOYEE_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, employee.getBalance());
			ps.setInt(2, employee.getEmployeeId());
			ps.execute();
			return employee;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public boolean deleteEmployee(Employee employee) {
		// PROBABLY NOT NEEDED
		return false;
	}

}
