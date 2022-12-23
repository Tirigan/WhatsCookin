package beans;

public class Instruction {
	private int id;
	private int recipeId;
	private int order;
	private String value; 
	
	public Instruction() {
		
	}
	
	public Instruction(int id, int recipeId, int order, String value) {
		super();
		this.id = id;
		this.recipeId = recipeId;
		this.order = order;
		this.value = value;
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

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	
	
	
}
