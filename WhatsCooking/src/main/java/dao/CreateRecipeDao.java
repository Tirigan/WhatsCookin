package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Ingredient;
import beans.Instruction;
import beans.Recipe;
import services.RecipeService;

public class CreateRecipeDao implements RecipeService {

    public void saveRecipe(Recipe recipe) {
        /// Get a connection to the DB
        Connection connection = DBConnection.getConnectionToDatabase();

        try {
            // Insert the recipe into the recipe table
            String sql = "INSERT INTO recipe (user_id, title, description, cooking_style) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, recipe.getUserId());
            statement.setString(2, recipe.getTitle());
            statement.setString(3, recipe.getDescription());
            statement.setString(4, recipe.getCookingStyle());
            statement.executeUpdate();
            
            // Get the id of the recipe that was just inserted
            ResultSet generatedKeys = statement.getGeneratedKeys();
            int recipeId = -1;
            if (generatedKeys.next()) {
                recipeId = generatedKeys.getInt(1);
            }
            
            // Insert the ingredients of the recipe into the ingredient table
            for (Ingredient ingredient : recipe.getIngredients()) {
                sql = "INSERT INTO ingredient (recipe_id, name, amount, amount_unit) VALUES (?,?,?,?)";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, recipeId);
                statement.setString(2, ingredient.getName());
                statement.setFloat(3, ingredient.getAmount());
                statement.setString(4, ingredient.getAmountUnit());
                statement.executeUpdate();
            }
            
            // Insert the instructions of the recipe into the instruction table
            for (Instruction instruction : recipe.getInstructions()) {
                sql = "INSERT INTO instruction (recipe_id, inst_order, value) VALUES (?,?,?)";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, recipeId);
                statement.setInt(2, instruction.getInt());
                statement.setString(3, instruction.getString());
                statement.executeUpdate();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

