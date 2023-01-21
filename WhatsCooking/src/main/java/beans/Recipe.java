package beans;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
	private int id;	
	private int userId;	
	private String title;
	private String description;
	private String cookingStyle;
	private List<Ingredient> ingredients;
	private List<Instruction> instructions;

	
	public Recipe() {
		ingredients = new ArrayList<Ingredient>();
		instructions = new ArrayList<Instruction>();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCookingStyle() {
		return cookingStyle;
	}
	public void setCookingStyle(String cookingStyle) {
		this.cookingStyle = cookingStyle;
	}
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	public List<Instruction> getInstructions() {
		return instructions;
	}
	public void setInstructions(List<Instruction> instructions) {
		this.instructions = instructions;
	}
	
}
