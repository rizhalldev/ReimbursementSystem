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
import dev.mahmed.entities.Reimbursement;
import dev.mahmed.utils.ConnectionUtil;

public class ReimbursementDAOmaria implements ReimbursementDAO{

	@Override
	public Reimbursement createReimbursement(Reimbursement reimbursement) {
		try (Connection conn = ConnectionUtil.createConnection()) {
			String sql = "INSERT INTO project1reimbursements.REIMBURSEMENT VALUES (0,?,'Pending',?,0,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, reimbursement.getAmountRequested());
			ps.setString(2, reimbursement.getCategory());
			ps.setInt(3, reimbursement.getEmployeeId());
			ps.setDate(4, reimbursement.getDate());
			ps.setString(5, reimbursement.getDetails());
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int key = rs.getInt("REIMBURSEMENT_ID");
			reimbursement.setReimbursementId(key);
			return reimbursement;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public Reimbursement getReimbursementById(int id) {
		try (Connection conn = ConnectionUtil.createConnection()) {
			String sql = "SELECT * FROM project1reimbursements.REIMBURSEMENT WHERE REIMBURSEMENT_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Reimbursement reimbursement = new Reimbursement();
			reimbursement.setAmountRequested(rs.getInt("AMOUNT_REQUESTED"));
			reimbursement.setStatus(rs.getString("STATUS"));
			reimbursement.setCategory(rs.getString("CATEGORY"));
			reimbursement.setAmountPaid(rs.getInt("AMOUNT_PAID"));
			reimbursement.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
			reimbursement.setDate(rs.getDate("SET_DATE"));
			reimbursement.setDetails(rs.getString("DETAILS"));
			return reimbursement;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public List<Reimbursement> getReimbursementByManager(Manager manager) {
		try (Connection conn = ConnectionUtil.createConnection()) {
			List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
			String sql = "SELECT * FROM project1reimbursements.REIMBURSEMENT INNER JOIN project1reimbursements.EMPLOYEE ON REIMBURSEMENT.EMPLOYEE_ID = EMPLOYEE.EMPLOYEE_ID INNER JOIN project1reimbursements.MANAGER ON EMPLOYEE.MANAGER_ID = MANAGER.MANAGER_ID AND MANAGER.MANAGER_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, manager.getManagerId());
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Reimbursement r = new Reimbursement();
				r.setReimbursementId(rs.getInt("REIMBURSEMENT_ID"));
				r.setAmountRequested(rs.getInt("AMOUNT_REQUESTED"));
				r.setStatus(rs.getString("STATUS"));
				r.setCategory(rs.getString("CATEGORY"));
				r.setAmountPaid(rs.getInt("AMOUNT_PAID"));
				r.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
				r.setDate(rs.getDate("SET_DATE"));
				r.setDetails(rs.getString("DETAILS"));
				reimbursements.add(r);
			}
			
			return reimbursements;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public List<Reimbursement> getReimbursementByDivision(String division) {
		try (Connection conn = ConnectionUtil.createConnection()) {
			List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
			String sql = "SELECT * FROM project1reimbursements.REIMBURSEMENT INNER JOIN project1reimbursements.EMPLOYEE ON REIMBURSEMENT.EMPLOYEE_ID = EMPLOYEE.EMPLOYEE_ID INNER JOIN project1reimbursements.MANAGER ON EMPLOYEE.MANAGER_ID = MANAGER.MANAGER_ID AND MANAGER.DIVISION = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, division);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Reimbursement r = new Reimbursement();
				r.setReimbursementId(rs.getInt("REIMBURSEMENT_ID"));
				r.setAmountRequested(rs.getInt("AMOUNT_REQUESTED"));
				r.setStatus(rs.getString("STATUS"));
				r.setCategory(rs.getString("CATEGORY"));
				r.setAmountPaid(rs.getInt("AMOUNT_PAID"));
				r.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
				r.setDate(rs.getDate("SET_DATE"));
				r.setDetails(rs.getString("DETAILS"));
				reimbursements.add(r);
			}
			
			return reimbursements;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public List<Reimbursement> getReimbursementsByEmployee(Employee employee) {
		try (Connection conn = ConnectionUtil.createConnection()) {
			List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
			String sql = "SELECT * FROM project1reimbursements.REIMBURSEMENT WHERE EMPLOYEE_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, employee.getEmployeeId());
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Reimbursement r = new Reimbursement();
				r.setReimbursementId(rs.getInt("REIMBURSEMENT_ID"));
				r.setAmountRequested(rs.getInt("AMOUNT_REQUESTED"));
				r.setStatus(rs.getString("STATUS"));
				r.setCategory(rs.getString("CATEGORY"));
				r.setAmountPaid(rs.getInt("AMOUNT_PAID"));
				r.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
				r.setDate(rs.getDate("SET_DATE"));
				r.setDetails(rs.getString("DETAILS"));
				reimbursements.add(r);
			}
			
			return reimbursements;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public List<Reimbursement> getReimbursementByStatus(String status) {
		try (Connection conn = ConnectionUtil.createConnection()) {
			List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
			String sql = "SELECT * FROM project1reimbursements.REIMBURSEMENT WHERE STATUS = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Reimbursement r = new Reimbursement();
				r.setReimbursementId(rs.getInt("REIMBURSEMENT_ID"));
				r.setAmountRequested(rs.getInt("AMOUNT_REQUESTED"));
				r.setStatus(rs.getString("STATUS"));
				r.setCategory(rs.getString("CATEGORY"));
				r.setAmountPaid(rs.getInt("AMOUNT_PAID"));
				r.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
				r.setDate(rs.getDate("SET_DATE"));
				r.setDetails(rs.getString("DETAILS"));
				reimbursements.add(r);
			}
			
			return reimbursements;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public List<Reimbursement> getAllReimbursements() {
		try (Connection conn = ConnectionUtil.createConnection()) {
			List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
			String sql = "SELECT * FROM project1reimbursements.REIMBURSEMENT";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Reimbursement r = new Reimbursement();
				r.setReimbursementId(rs.getInt("REIMBURSEMENT_ID"));
				r.setAmountRequested(rs.getInt("AMOUNT_REQUESTED"));
				r.setStatus(rs.getString("STATUS"));
				r.setCategory(rs.getString("CATEGORY"));
				r.setAmountPaid(rs.getInt("AMOUNT_PAID"));
				r.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
				r.setDate(rs.getDate("SET_DATE"));
				r.setDetails(rs.getString("DETAILS"));
				reimbursements.add(r);
			}
			
			return reimbursements;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public Reimbursement updateReimbursement(Reimbursement reimbursement) {
		try (Connection conn = ConnectionUtil.createConnection()) {
			String sql = "UPDATE project1reimbursements.REIMBURSEMENT SET STATUS = ?, AMOUNT_PAID = ?, DETAILS = ? WHERE REIMBURSEMENT_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, reimbursement.getStatus());
			ps.setInt(2, reimbursement.getAmountPaid());
			ps.setString(3, reimbursement.getDetails());
			ps.setInt(4, reimbursement.getReimbursementId());
			ps.execute();
			return reimbursement;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public boolean deleteReimbursement(Reimbursement reimbursement) {
		// TODO Auto-generated method stub
		return false;
	}

}
