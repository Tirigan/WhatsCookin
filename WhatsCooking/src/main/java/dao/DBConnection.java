package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	
	///	DB Schema
	//	CREATE DATABASE whatsCooking DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci
	//	CREATE TABLE recipe (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
	//  	title VARCHAR(128),
	//  	description VARCHAR(128),
	//		cooking_style VARCHAR(128),
	//		user_id INT,
	//		FOREIGN KEY (user_id)
	//		REFERENCES login(id)
	//		ON DELETE CASCADE
	//  );
	//	CREATE TABLE ingredient (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
	//  	recipe_id INTEGER,
	//  	name VARCHAR(128),
	//  	amount FLOAT,
	//  	amount_unit VARCHAR(128)
	//  );
	//	CREATE TABLE instruction (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
	//  	recipe_id INTEGER,
	//  	inst_order INTEGER,
	//  	value VARCHAR(128)
	//  );
	//	CREATE TABLE login (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
	//		 uname VARCHAR(128),
	//		 email VARCHAR(128),
	//		 password VARCHAR(128)
	//	);
	//	CREATE TABLE favorite (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
	//      recipe_id INT,
	//		user_id INT,
	//		FOREIGN KEY (user_id)
	//		REFERENCES login(id)
	//		ON DELETE CASCADE,
	//		FOREIGN KEY (recipe_id)
	//		REFERENCES recipe(id)
	//		ON DELETE CASCADE	
	//	);
	

	private static final String dbUser = "root";
	private static final String dbPassword = "password";
	
	private static final String driverClass = "com.mysql.jdbc.Driver";
	private static final String connectionUrl = "jdbc:mysql://localhost:3306/whatscooking";

	
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
