package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import services.RegisterService;

public class RegisterDao implements RegisterService {
	
	public boolean registerUser(String email, String username, String password) {		
		/// Get a connection to the DB
		Connection connection = DBConnection.getConnectionToDatabase();

		try {
			// query the Ingredient
			PreparedStatement ps = connection.prepareStatement("INSERT INTO login (email, uname, password) VALUES (?,?,?)");
			ps.setString(1, email);
			ps.setString(2, username);
			ps.setString(3, password);
			ps.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
}
