package beans;

public class Ingredient {
	private int id;
	private int recipeId;
	private String name;
	private float amount; 
	private String amountUnit;
	
	public Ingredient() {
		
	}
	
	public Ingredient(int id, int recipeId, String name, float amount, String amountUnit) {
		super();
		this.id = id;
		this.recipeId = recipeId;
		this.name = name;
		this.amount = amount;
		this.amountUnit = amountUnit;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getAmountUnit() {
		return amountUnit;
	}
	public void setAmountUnit(String amountUnit) {
		this.amountUnit = amountUnit;
	}
	
	
	
}
