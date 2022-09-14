package com.entities.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;

import com.ecommerce.Dao.UserDao;
import com.ecommerce.entities.Message;
import com.ecommerce.entities.User;
import com.ecommerce.helper.FactoryProvider;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
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
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession httpSession = request.getSession();
		RequestDispatcher dispatcher = null;

		try {

			String userName = request.getParameter("user_name");
			String userEmail = request.getParameter("user_email");
			String userPassword = request.getParameter("user_password");
			String userConfirmPassword = request.getParameter("user_confirm_password");
			String userPhone = request.getParameter("user_phone");
			String userAddress = request.getParameter("user_address");
			String userRole = request.getParameter("user_role");

			if (userName.isEmpty()) {
				Message msg = new Message("Username cannot be empty", "error", "alert-danger");
				httpSession.setAttribute("status", msg);
				dispatcher = request.getRequestDispatcher("Register");
				dispatcher.forward(request, response);
				return;
			}

			if (userEmail.isEmpty()) {
				Message msg = new Message("Email cannot be empty", "error", "alert-danger");
				httpSession.setAttribute("status", msg);
				dispatcher = request.getRequestDispatcher("Register");
				dispatcher.forward(request, response);
				return;
			}

			if (userPassword.isEmpty()) {
				Message msg = new Message("Password cannot be empty", "error", "alert-danger");
				httpSession.setAttribute("status", msg);
				dispatcher = request.getRequestDispatcher("Register");
				dispatcher.forward(request, response);
				return;
			}
			if (!userPassword.equals(userConfirmPassword)) {
				Message msg = new Message("Confirm Password should be match to Password.", "error", "alert-danger");
				httpSession.setAttribute("status", msg);
				dispatcher = request.getRequestDispatcher("Register");
				dispatcher.forward(request, response);
				return;
			}
			if (userPhone.isEmpty()) {
				Message msg = new Message("Phone cannot be empty", "error", "alert-danger");
				httpSession.setAttribute("status", msg);
				dispatcher = request.getRequestDispatcher("Register");
				dispatcher.forward(request, response);
				
				return;
			}
				else if(userPhone.length()>15) {
					Message msg = new Message("Phone number cannot be greater than 15 digits.", "error", "alert-danger");
					httpSession.setAttribute("status", msg);
					dispatcher = request.getRequestDispatcher("Register");
					dispatcher.forward(request, response);
					return;
					
				}
			
			if (userAddress.isEmpty()) {
				Message msg = new Message("Address cannot be empty", "error", "alert-danger");
				httpSession.setAttribute("status", msg);
				dispatcher = request.getRequestDispatcher("Register");
				dispatcher.forward(request, response);
				return;
			}

			User user = new User(userName, userEmail, userPassword, userPhone, "default.jpg", userAddress, new Date(),
					"No");

			if (userRole.equals("seller")) {
				user.setUserType(userRole);

			}
			else {
				user.setUserType("customer");
			}
			

			Session hibernateSession = FactoryProvider.getFactory().openSession();
			Transaction transaction = hibernateSession.beginTransaction();

			UserDao userDao = new UserDao(FactoryProvider.getFactory());
			List<User> userCheck = userDao.getUserByEmail(userEmail);
			for (User s : userCheck) {
				if (s != null) {
					Message msg = new Message("This email is already exist, please use another one.", "error",
							"alert-danger");
					httpSession.setAttribute("status", msg);
					dispatcher = request.getRequestDispatcher("Register");
					dispatcher.forward(request, response);
					return;
				}
			}

			List<User> userPhoneCheck = userDao.getUserByPhone(userPhone);
			for (User s : userPhoneCheck) {
				if (s != null) {
					Message msg = new Message("This Phone number is already exist.", "error", "alert-danger");
					httpSession.setAttribute("status", msg);
					dispatcher = request.getRequestDispatcher("Register");
					dispatcher.forward(request, response);
					return;
				}
			}

			int userId = (Integer) hibernateSession.save(user);
			httpSession.setAttribute("userId", userId);
			transaction.commit();
			hibernateSession.close();
			if (user != null) {
				Message msg = new Message("Registered Successfull! You can login now", "success", "alert-success");
				httpSession.setAttribute("status", msg);
				dispatcher = request.getRequestDispatcher("Login");
				dispatcher.forward(request, response);
				return;

			} else {
				Message msg = new Message("An Error has occurred!", "error", "alert-danger");
				httpSession.setAttribute("status", msg);
				dispatcher = request.getRequestDispatcher("Register");
				dispatcher.forward(request, response);
				return;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private UserDao UserDao(SessionFactory factory) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String hashPassword(String plainTextPassword){
		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
	}
	
	
	

}
