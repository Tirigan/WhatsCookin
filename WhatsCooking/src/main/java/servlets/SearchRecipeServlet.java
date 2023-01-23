package servlets;


import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Recipe;
import dao.FavoriteDao;
import dao.RecipeDao;

@WebServlet("/search-recipe")
public class SearchRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchRecipeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/html/search-recipe.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Going to create recipe
		
		 String saveRecipe = request.getParameter("saveRecipe");
		    if (saveRecipe != null && saveRecipe.equals("yes")) {
		        // Get recipe_id and user_id from the request
		        int recipeId = Integer.parseInt(request.getParameter("recipe_id"));
		        int userId = Integer.parseInt((String) request.getSession().getAttribute("user_id"));

		        // Save the recipe to the user's favorites
		        FavoriteDao favoriteDao = new FavoriteDao();
		        favoriteDao.saveRecipe(recipeId, userId);
		    }
		
		String goToCreateRecipe = request.getParameter("goToCreateRecipe");
		if(goToCreateRecipe != null && goToCreateRecipe.equals("yes")) {
			response.sendRedirect("create-recipe");
			return;
		}
		  
		String ingredient1 = request.getParameter("ing1");
		String ingredient2 = request.getParameter("ing2");
		String ingredient3 = request.getParameter("ing3");
		String cookingStyle = request.getParameter("cooking_style");

		String infoMessage = null;
		
		RecipeDao recipeDao = new RecipeDao();
		List<Recipe> recipes = recipeDao.getRecipesByIngredientsAndCookingStyle(
				ingredient1, 
				ingredient2, 
				ingredient3,
				cookingStyle
		);
		
		Recipe recipe = null;
		if(recipes.size() < 1) {
			infoMessage = "Sorry, we couldn't find a recipe that has any of those ingredients or matches your cooking style!";
		} else {
			Random random = new Random();
			recipe = recipes.get(random.nextInt(recipes.size()));
		}
		
		request.setAttribute("infoMessage", infoMessage);
		request.setAttribute("recipe", recipe);
		doGet(request, response);	
	}

}
