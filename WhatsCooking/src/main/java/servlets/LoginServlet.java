package servlets;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LoginDao;


@WebServlet("/")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/html/login.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String infoMessage = null;
	
		LoginDao loginDao = new LoginDao();
		
		boolean userLoggedIn = loginDao.login(username, password);
		
		if(userLoggedIn) {
			response.sendRedirect("search-recipe");
		} else {
			infoMessage = "Invalid credentials!";
			request.setAttribute("infoMessage", infoMessage);
			request.getRequestDispatcher("/html/login.jsp").forward(request, response);
		}
		
	}

}
