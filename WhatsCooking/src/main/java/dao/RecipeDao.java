package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Ingredient;
import beans.Instruction;
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
				recipe.setUserId(set.getInt("user_id"));
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
				recipe.setUserId(set.getInt("user_id"));
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
	
	public List<Recipe> getUserRecipes(int userId) {
		Recipe recipe = null;
		
		List<Recipe> recipes = new ArrayList<Recipe>();
		
		/// Get a connection to the DB
		Connection connection = DBConnection.getConnectionToDatabase();
		
		
		try {
		
			// query the recipes for the user id
			String sql = "select * from recipe where user_id = ?";
				
			PreparedStatement statement = connection.prepareStatement(sql);
			statement = connection.prepareStatement(sql);			
			statement.setString(1, Integer.toString(userId));

			
			ResultSet set = statement.executeQuery();
			
			// loop over all the results from the DB
			while(set.next()) {
				// create the recipe
				recipe = new Recipe();
				recipe.setId(set.getInt("id"));
				recipe.setUserId(set.getInt("user_id"));
				recipe.setTitle(set.getString("title"));
				recipe.setDescription(set.getString("description"));
				recipe.setCookingStyle(set.getString("cooking_style"));
				
				/// get the ingredients
				IngredientDao ingredientDao = new IngredientDao();
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
	
	public boolean createRecipe(Recipe recipe) {		
		/// Get a connection to the DB
		Connection connection = DBConnection.getConnectionToDatabase();

		try {
			// query the Ingredient
			PreparedStatement ps = connection.prepareStatement("INSERT INTO recipe (title, description, cooking_style, user_id) VALUES (?,?,?,?)");
			ps.setString(1, recipe.getTitle());
			ps.setString(2, recipe.getDescription());
			ps.setString(3, recipe.getCookingStyle());
			ps.setInt(4, recipe.getUserId());
			
			int recipeId = ps.executeUpdate();
			
			for(int i=0;i<recipe.getInstructions().size();i++) {
				Instruction instruction = recipe.getInstructions().get(i);
				ps = connection.prepareStatement("INSERT INTO instruction (recipe_id, inst_order, value) VALUES (?,?,?)");
				ps.setInt(1, recipeId);
				ps.setInt(2, instruction.getOrder());
				ps.setString(3, instruction.getValue());
				ps.executeUpdate();
			}
			for (Ingredient ingredient : recipe.getIngredients()){
				ps = connection.prepareStatement("INSERT INTO ingredient (recipe_id, name, amount, amount_unit) VALUES (?,?,?,?)");
				ps.setInt(1, recipeId);
				ps.setString(2, ingredient.getName());
				ps.setFloat(3, ingredient.getAmount());
				ps.setString(4, ingredient.getAmountUnit());
				ps.executeUpdate();
			}
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public List<Recipe> getSavedRecipes(int userId) {
		Recipe recipe = null;
		
		List<Recipe> recipes = new ArrayList<Recipe>();
		
		/// Get a connection to the DB
		Connection connection = DBConnection.getConnectionToDatabase();
		
		
		try {
		
			// query the rows where the user has a recipe
			String sql = "select * from favorite where user_id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement = connection.prepareStatement(sql);			
			statement.setInt(1, userId);
			
			ResultSet favResults = statement.executeQuery();
			
			// loop over all the results from the DB
			while(favResults.next()) {
				
				// query the recipes for the user id
				sql = "select * from recipe where recipe_id = ?";
					
				statement = connection.prepareStatement(sql);
				statement = connection.prepareStatement(sql);			
				statement.setInt(1, favResults.getInt("recipe_id"));

				
				ResultSet set = statement.executeQuery();
				
				// loop over all the results from the DB
				while(set.next()) {
					// create the recipe
					recipe = new Recipe();
					recipe.setId(set.getInt("id"));
					recipe.setUserId(set.getInt("user_id"));
					recipe.setTitle(set.getString("title"));
					recipe.setDescription(set.getString("description"));
					recipe.setCookingStyle(set.getString("cooking_style"));
					
					/// get the ingredients
					IngredientDao ingredientDao = new IngredientDao();
					recipe.setIngredients(ingredientDao.getIngredientsForRecipe(recipe.getId()));
				
					/// get the instructions
					InstructionDao instructionDao = new InstructionDao();
					recipe.setInstructions(instructionDao.getInstructionsForRecipe(recipe.getId()));

					recipes.add(recipe);
				}
				
			}
			

			
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		
		return recipes;
	}
	
	
	
	public boolean saveRecipe(int recipeId, int userId) {
		/// Get a connection to the DB
		Connection connection = DBConnection.getConnectionToDatabase();

		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO favorite (recipe_id, user_id) VALUES (?,?)");
			ps.setInt(1, recipeId);
			ps.setInt(2, userId);
			ps.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	
	
}
