package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	
	///	DB Schema
	//	CREATE DATABASE whatsCooking DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci
	//	CREATE TABLE recipe (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
	//  	title VARCHAR(128),
	//  	description VARCHAR(128)
	//  );
	

	private static final String dbUser = "root";
	private static final String dbPassword = "password";
	
	private static final String driverClass = "com.mysql.jdbc.Driver";
	private static final String connectionUrl = "jdbc:mysql://localhost:3306/whatsCooking";

	
	public static Connection getConnectionToDatabase() {
		Connection connection = null;
		
		try {
			// load the driver class
			Class.forName(driverClass);
			System.out.println("MySQL JDBC Driver registered!");
			
			// Get a hold of the DriverManager
			connection = DriverManager.getConnection(connectionUrl, dbUser, dbPassword);
		} catch (ClassNotFoundException e) {
			System.out.print("Where is yout MySQL JDBC Driver?");
			e.printStackTrace();
		} catch(SQLException e) {
			System.out.print("Connection failed! Check output in the console");
			e.printStackTrace();
		}
		
		
		if(connection != null)
			System.out.println("Connection made to DB!");
		
		
		return connection;
	}
	
}
