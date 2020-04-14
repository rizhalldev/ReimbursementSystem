package dev.mahmed.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dev.mahmed.entities.Manager;
import dev.mahmed.utils.ConnectionUtil;

public class ManagerDAOmaria implements ManagerDAO{

	public Manager createManager(Manager manager) {

		try (Connection conn = ConnectionUtil.createConnection()) {
			String sql = "INSERT INTO MANAGER VALUES(?,?,?,?,?,?)";
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

	public Manager getManagerbyId(Manager manager) {
		// TODO Auto-generated method stub
		return null;
	}

	public Manager getManagerByUsername(Manager manager) {
		// TODO Auto-generated method stub
		return null;
	}

	public Manager getManagerByFullName(Manager manager) {
		// TODO Auto-generated method stub
		return null;
	}

	public <List> Manager getManagersByDivision(Manager manager) {
		// TODO Auto-generated method stub
		return null;
	}

	public <List> Manager getManagersByFirstName(Manager manager) {
		// TODO Auto-generated method stub
		return null;
	}

	public <List> Manager getAllManagers() {
		// TODO Auto-generated method stub
		return null;
	}

	public Manager updateManager(Manager manager) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteManager(Manager manager) {
		// TODO Auto-generated method stub
		return false;
	}

}
