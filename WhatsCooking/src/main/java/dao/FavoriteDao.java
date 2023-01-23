package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FavoriteDao {
	 public boolean saveRecipe(int recipeId, int userId) {
	        Connection connection = DBConnection.getConnectionToDatabase();

	        try {       
	            PreparedStatement ps = connection.prepareStatement("INSERT INTO favorite (user_id, recipe_id) VALUES (?,?)");
	            ps.setInt(1, userId);
	            ps.setInt(2, recipeId);
	            ps.executeUpdate();

	            return true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	}

