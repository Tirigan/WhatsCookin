package servlets;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RegisterDao;


@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/html/register.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");

		String infoMessage = null;
		
		if(email.equals("") || username.equals("") || password.equals("") || confirmPassword.equals("")) {
			infoMessage = "All fields are required!";
			request.setAttribute("infoMessage", infoMessage);
			doGet(request, response);	
			return;
		}
		

		if(!password.equals(confirmPassword)) {
			infoMessage = "Passwords don't match!";
			request.setAttribute("infoMessage", infoMessage);
			doGet(request, response);	
			return;
			
		} 
		
		RegisterDao registerDao = new RegisterDao();
		boolean userRegistered = registerDao.registerUser(email, username, password);
		
		if(userRegistered) {
			response.sendRedirect("login");
			return;
		} else {
			infoMessage = "Something went wrong registering user!";
			request.setAttribute("infoMessage", infoMessage);
			doGet(request, response);	
			return;
		}
		
		
	}

}
