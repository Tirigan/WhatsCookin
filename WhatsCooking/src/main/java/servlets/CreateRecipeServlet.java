package servlets;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Ingredient;
import beans.Instruction;
import beans.Recipe;
import dao.RecipeDao;

@WebServlet("/create-recipe")
public class CreateRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateRecipeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/html/create-recipe.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = (int) request.getSession().getAttribute("user_id");
		String title = request.getParameter("recipe-name");
		String description = request.getParameter("recipe-description");
		String cookingStyle = request.getParameter("recipe-cooking-style");
		
		RecipeDao recipeDao = new RecipeDao();

		Recipe recipe = new Recipe();
		recipe.setTitle(title);
		recipe.setDescription(description);
		recipe.setCookingStyle(cookingStyle);
		recipe.setUserId(userId);
		
		Map<String, String[]> params = request.getParameterMap();
		
		for(int i = 1; i <= params.size(); i++) {
			
			String ingName = request.getParameter("ingredient_name_"+i);
			String ingAmount = request.getParameter("ingredient_amount_"+i);
			String ingAmountUnit = request.getParameter("ingredient_unit_"+i);
			
			if(ingName != null && ingAmount != null && ingAmountUnit != null) {
				Ingredient ingredient = new Ingredient();
				ingredient.setName(ingName);
				ingredient.setAmount(Float.parseFloat(ingAmount));
				ingredient.setAmountUnit(ingAmountUnit);
				
				List<Ingredient> ingredients = recipe.getIngredients();
				ingredients.add(ingredient);
				recipe.setIngredients(ingredients);
			}
			
			String instructionValue = request.getParameter("instruction_"+i);
			if(instructionValue != null) {
				Instruction instruction = new Instruction();
				instruction.setOrder(i);
				instruction.setValue(instructionValue);
				
				List<Instruction> instructions = recipe.getInstructions();
				instructions.add(instruction);
				recipe.setInstructions(instructions);
			}

		}
		
		boolean recipeCreated = recipeDao.createRecipe(recipe);
		
		String infoMessage = null;
		if(recipeCreated) {
			infoMessage = "Recipe successfully created!";
			request.setAttribute("infoMessage", infoMessage);
			request.getRequestDispatcher("/html/search-recipe.jsp").forward(request, response);
		} else {
			infoMessage = "Something went wrong creating your recipe!";
			request.setAttribute("infoMessage", infoMessage);
			doGet(request, response);
		}
	}

}
