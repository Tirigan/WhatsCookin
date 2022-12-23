package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Instruction;
import services.InstructionService;

public class InstructionDao implements InstructionService {
	
	public List<Instruction> getInstructionsForRecipe(int recipeId) {
		Instruction instruction;
		List<Instruction> instructions = new ArrayList<Instruction>();
		
		/// Get a connection to the DB
		Connection connection = DBConnection.getConnectionToDatabase();

		
		try {
			// query the Ingredient
			String sql = "select * from instruction where recipe_id = ? ORDER BY inst_order ASC";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, Integer.toString(recipeId));
			ResultSet set = statement.executeQuery();
			
			// loop over all the results from the DB
			while(set.next()) {
				// create the Ingredient
				instruction = new Instruction(
						set.getInt("id"),
						set.getInt("recipe_id"),
						set.getInt("inst_order"),
						set.getString("value")
				);
				
				
				// Add the Ingredient
				instructions.add(instruction);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return instructions;
	}
	
}
