package servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Recipe;
import dao.RecipeDao;

@WebServlet("/recipes")
public class RecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RecipeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RecipeDao dao = new RecipeDao();
		List<Recipe> recipes = dao.getRecipes();
		request.setAttribute("recipes", recipes);
		request.getRequestDispatcher("/html/recipes.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
