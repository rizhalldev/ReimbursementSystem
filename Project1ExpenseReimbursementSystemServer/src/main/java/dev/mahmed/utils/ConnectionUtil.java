package dev.mahmed.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
private static Connection conn = null;
	
	public static Connection createConnection() {
				
		Properties props = new Properties();
				
		try {
			// Sometimes the JRE doesn't load the driver correctly
			// This will force the driver to lead, if it doesn't work
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
		FileInputStream in = new FileInputStream(ConnectionUtil.class.getClassLoader().getResource("/home/ec2-user/.jenkins/workspace/Project1ReimbursementSystem/Project1ExpenseReimbursementSystemServer/src/main/resources/connection.properties").getFile());
//		FileInputStream in = new FileInputStream(ConnectionUtil.class.getClassLoader().getResource("connection.properties").getFile());
		props.load(in);
					
		String details = props.getProperty("condetails");
					
		Connection conn = DriverManager.getConnection(details);
		System.out.println(conn);

		return conn;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
	createConnection();
}
}