package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.User;
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
	
	public User getUserByUsername(String uname) {
		User user = new User();
		
		/// Get a connection to the DB
		Connection connection = DBConnection.getConnectionToDatabase();

		
		try {
			// query the Ingredient
			String sql = "select * from login where uname = ? LIMIT 1";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, uname);
			ResultSet set = statement.executeQuery();
			
			// loop over all the results from the DB
			while(set.next()) {
				// set the values for the user
				user.setId(set.getInt("id"));
				user.setUname(set.getString("uname"));
				user.setEmail(set.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
}
