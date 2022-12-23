package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Ingredient;
import services.IngredientService;

public class IngredientDao implements IngredientService {
	
	public List<Ingredient> getIngredientsForRecipe(int recipeId) {
		Ingredient ingredient;
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		
		/// Get a connection to the DB
		Connection connection = DBConnection.getConnectionToDatabase();

		
		try {
			// query the Ingredient
			String sql = "select * from ingredient where recipe_id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, Integer.toString(recipeId));
			ResultSet set = statement.executeQuery();
			
			// loop over all the results from the DB
			while(set.next()) {
				// create the Ingredient
				ingredient = new Ingredient(
						set.getInt("id"),
						set.getInt("recipe_id"),
						set.getString("name"),
						set.getFloat("amount"),
						set.getString("amount_unit")
				);
				
				
				// Add the Ingredient
				ingredients.add(ingredient);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ingredients;
	}
	
	public Ingredient getIngredientByName(String name) {
		Ingredient ingredient = null;
		
		/// Get a connection to the DB
		Connection connection = DBConnection.getConnectionToDatabase();
		
		
		try {
			// query the Ingredient
			String sql = "select * from ingredient where name = ? LIMIT 1";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			ResultSet set = statement.executeQuery();
						
			// loop over all the results from the DB
			while(set.next()) {
				// create the Ingredient
				ingredient = new Ingredient();
				ingredient.setId(set.getInt("id"));
				ingredient.setRecipeId(set.getInt("recipe_id"));
				ingredient.setName(set.getString("name"));
				ingredient.setAmount(set.getFloat("amount"));
				ingredient.setAmountUnit(set.getString("amount_unit"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ingredient;
	}
	
}
