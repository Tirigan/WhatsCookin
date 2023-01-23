package servlets;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import dao.LoginDao;


@WebServlet("/")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/html/login.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Going to registration
		String goToRegister = request.getParameter("goToRegister");
		if(goToRegister != null && goToRegister.equals("yes")) {
			response.sendRedirect("register");
			return;
		}

		/// Login in
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String infoMessage = null;
	
		LoginDao loginDao = new LoginDao();
		
		boolean userLoggedIn = loginDao.login(username, password);
		
		if(userLoggedIn) {
			User user = loginDao.getUserByUsername(username);
			request.getSession().setAttribute("user_id", user.getId());
			response.sendRedirect("search-recipe");
		} else {
			infoMessage = "Invalid credentials!";
			request.setAttribute("infoMessage", infoMessage);
			request.getRequestDispatcher("/html/login.jsp").forward(request, response);
		}
	}

}
