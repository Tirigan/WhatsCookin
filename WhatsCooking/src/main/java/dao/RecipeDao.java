package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Recipe;
import services.RecipeService;

public class RecipeDao implements RecipeService {
	
	public List<Recipe> getRecipes() {
		Recipe recipe;
		List<Recipe> recipes = new ArrayList<Recipe>();
		
		/// Get a connection to the DB
		Connection connection = DBConnection.getConnectionToDatabase();
		
		
		try {
			// query the recipe
			String sql = "select * from recipe";
			Statement statement = connection.createStatement();
			ResultSet set = statement.executeQuery(sql);
			
			// loop over all the results from the DB
			while(set.next()) {
				// create the recipe
				recipe = new Recipe();
				recipe.setId(set.getInt("id"));
				recipe.setTitle(set.getString("title"));
				recipe.setDescription(set.getString("description"));
				
				// Add the recipe
				recipes.add(recipe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return recipes;
	}
	
}
