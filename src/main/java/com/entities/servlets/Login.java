package com.entities.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.ecommerce.Dao.UserDao;
import com.ecommerce.entities.Message;
import com.ecommerce.entities.User;
import com.ecommerce.helper.FactoryProvider;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession httpSession = request.getSession();
		RequestDispatcher dispatcher = null;

		String userEmail = request.getParameter("user_email");
		String userPassword = request.getParameter("user_password");

		if (userEmail.isEmpty()) {
			Message msg = new Message("Email cannot be empty", "error", "alert-danger");
			httpSession.setAttribute("status", msg);
			dispatcher = request.getRequestDispatcher("Login");
			dispatcher.forward(request, response);
			return;
		}

		if (userPassword.isEmpty()) {
			Message msg = new Message("Password cannot be empty", "error", "alert-danger");
			httpSession.setAttribute("status", msg);
			dispatcher = request.getRequestDispatcher("Login");
			dispatcher.forward(request, response);
			return;

		}

		UserDao userDao = new UserDao(FactoryProvider.getFactory());
		User user = userDao.getUserByEmailAndPassword(userEmail, userPassword);

		if (user == null) {
			Message msg = new Message("Invalid Email or Password", "error", "alert-danger");
			httpSession.setAttribute("status", msg);
			dispatcher = request.getRequestDispatcher("Login");
			dispatcher.forward(request, response);
		} else {
			httpSession.setAttribute("currentUser", user);

			if (user.getUserType().equals("admin")) {
				response.sendRedirect("AdminPanel");
			} else if (user.getUserType().equals("customer")) {
				response.sendRedirect("home");
			} else if (user.getUserType().equals("seller")) {
				response.sendRedirect("SellerPanel");
			} else {
				return;
			}

		}

	}

}
