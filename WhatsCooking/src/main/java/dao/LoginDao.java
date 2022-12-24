package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import services.LoginService;

public class LoginDao implements LoginService {
	
	public boolean login(String username, String password) {		
		/// Get a connection to the DB
		Connection connection = DBConnection.getConnectionToDatabase();

		try {
			// query the Ingredient
			PreparedStatement ps = connection.prepareStatement("select uname from login where uname=? and password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
}
