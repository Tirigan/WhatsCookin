package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Ingredient;
import beans.Recipe;
import services.RecipeService;

public class RecipeDao implements RecipeService {
		
	public Recipe getRecipeById(int recipeId) {
		Recipe recipe = null;
		
		/// Get a connection to the DB
		Connection connection = DBConnection.getConnectionToDatabase();
		
		
		try {
			// query the recipe
			String sql = "select * from recipe where id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, Integer.toString(recipeId));
			ResultSet set = statement.executeQuery();
			
			IngredientDao ingredientDao = new IngredientDao();
			
			// loop over all the results from the DB
			while(set.next()) {
				// create the recipe
				recipe = new Recipe();
				recipe.setId(set.getInt("id"));
				recipe.setTitle(set.getString("title"));
				recipe.setDescription(set.getString("description"));
				recipe.setCookingStyle(set.getString("cooking_style"));
				
				/// get the ingredients
				recipe.setIngredients(ingredientDao.getIngredientsForRecipe(recipe.getId()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return recipe;
	}
	
	
	public List<Recipe> getRecipesByIngredientsAndCookingStyle(String ing1, String ing2, String ing3, String cookingStyle) {
		if(ing1 == null && ing2 == null && ing3 == null && cookingStyle == null) return null;

		Recipe recipe = null;
		
		List<Recipe> recipes = new ArrayList<Recipe>();
		
		/// Get a connection to the DB
		Connection connection = DBConnection.getConnectionToDatabase();
		
		
		try {
			IngredientDao ingredientDao = new IngredientDao();
			Ingredient ingredient1 = ingredientDao.getIngredientByName(ing1);
			Ingredient ingredient2 = ingredientDao.getIngredientByName(ing2);
			Ingredient ingredient3 = ingredientDao.getIngredientByName(ing3);
			

			// query the recipe
			// This will filter a recipe where ANY of the ingredients shows up.
			String sql = "select * from recipe where id = ? OR id = ? OR id = ? OR cooking_style = ?";
				
			PreparedStatement statement = connection.prepareStatement(sql);
			statement = connection.prepareStatement(sql);

			String recipeId = "";
			
			if(ingredient1 != null) 
				recipeId = Integer.toString(ingredient1.getRecipeId());
			statement.setString(1, recipeId);
			
			if(ingredient2 != null) 
				recipeId = Integer.toString(ingredient2.getRecipeId());
			statement.setString(2, recipeId);
			
			if(ingredient3 != null) 
				recipeId = Integer.toString(ingredient3.getRecipeId());
			statement.setString(3, recipeId);
			
			if(cookingStyle == null) cookingStyle = "";
			statement.setString(4, cookingStyle);
			
			
			ResultSet set = statement.executeQuery();
			
			// loop over all the results from the DB
			while(set.next()) {
				// create the recipe
				recipe = new Recipe();
				recipe.setId(set.getInt("id"));
				recipe.setTitle(set.getString("title"));
				recipe.setDescription(set.getString("description"));
				recipe.setCookingStyle(set.getString("cooking_style"));
				
				/// get the ingredients
				recipe.setIngredients(ingredientDao.getIngredientsForRecipe(recipe.getId()));
			
				/// get the instructions
				InstructionDao instructionDao = new InstructionDao();
				recipe.setInstructions(instructionDao.getInstructionsForRecipe(recipe.getId()));

				recipes.add(recipe);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		
		return recipes;
	}
	

	
	
}
