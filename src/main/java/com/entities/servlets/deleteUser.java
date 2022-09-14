package com.entities.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ecommerce.entities.Message;
import com.ecommerce.entities.User;
import com.ecommerce.helper.FactoryProvider;

/**
 * Servlet implementation class deleteUser
 */
public class deleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public deleteUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		int userId = Integer.parseInt(request.getParameter("user_id").trim());
		RequestDispatcher dispatcher = null;
		HttpSession httpSession = request.getSession();
		User user1 = (User) httpSession.getAttribute("currentUser");

		Session session = FactoryProvider.getFactory().openSession();
		Transaction transaction = session.beginTransaction();

		if (user1 == null) {
			Message msg = new Message("Please login to continue.", "error", "alert-danger");
			httpSession.setAttribute("status", msg);
			response.sendRedirect("Login");
			return;

		}

		else if (user1.getUserType().equals("seller") || user1.getUserType().equals("customer")) {
			Message msg = new Message("You haven't permission.", "warning", "alert-danger");
			httpSession.setAttribute("status", msg);
			response.sendRedirect("Login");
			return;

		} else {

			User user = (User) session.get(User.class, userId);

			if (user.getUserType().equals("admin")) {
				Message msg = new Message("You cannot delete UserAdmin.", "error", "alert-danger");
				httpSession.setAttribute("status", msg);
				response.sendRedirect("AdminPanel");
				return;
			}

			else {

				session.delete(user);

				transaction.commit();
				if (user != null) {
					Message msg = new Message("User deleted successfully.", "success", "alert-success");
					httpSession.setAttribute("status", msg);
					dispatcher = request.getRequestDispatcher("AdminPanel");
					dispatcher.forward(request, response);
					return;

				}

				else {

					Message msg = new Message("User cannot be delete.", "error", "alert-danger");
					httpSession.setAttribute("status", msg);
					dispatcher = request.getRequestDispatcher("AdminPanel");
					dispatcher.forward(request, response);
					return;

				}

			}

		}

	}

}
