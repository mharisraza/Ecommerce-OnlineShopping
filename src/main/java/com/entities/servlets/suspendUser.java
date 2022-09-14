package com.entities.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ecommerce.entities.Message;
import com.ecommerce.entities.User;
import com.ecommerce.helper.FactoryProvider;

/**
 * Servlet implementation class suspendUser
 */
public class suspendUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public suspendUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		User user = (User) httpSession.getAttribute("currentUser");
		
		
		
		if(user==null) {
			Message msg = new Message("Please login to continue.", "error", "alert-danger");
			httpSession.setAttribute("status", msg);
			response.sendRedirect("Login");
			return;
			
		}
		
		else if(user.getUserType().equals("seller") || user.getUserType().equals("customer")) {
			Message msg= new Message("You haven't permission.", "warning", "alert-danger");
			httpSession.setAttribute("status", msg);
			response.sendRedirect("Login");
			return;
			
		}
		
		
		else {
			
			int userId = Integer.parseInt(request.getParameter("user_id").trim());
			String userStatus = request.getParameter("user_status");
			String userType = request.getParameter("user_type");
			
			
			RequestDispatcher dispatcher = null;

			Session session = FactoryProvider.getFactory().openSession();
			Transaction transaction = session.beginTransaction();
			
			if(userType.equals("admin")) {
				Message msg = new Message("You cannot suspend Admin.", "error", "alert-danger");
				httpSession.setAttribute("status", msg);
				dispatcher = request.getRequestDispatcher("AdminPanel");
				dispatcher.forward(request, response);
				session.close();
				return;
				
			}
			else {
				
				if (userStatus.equals("No")) {
					

					Query query = session.createSQLQuery("UPDATE users SET user_suspend='Yes' WHERE user_id =:userId");

					query.setParameter("userId", userId);
					int r = query.executeUpdate();

					transaction.commit();
					if (r > 0) {
						Message msg = new Message("User with ID: {" + userId + "} Suspended successfully.", "success",
								"alert-success");
						httpSession.setAttribute("status", msg);
						dispatcher = request.getRequestDispatcher("AdminPanel");
						dispatcher.forward(request, response);
						session.close();
						return;
					}

				} else {
					Query query = session.createSQLQuery("UPDATE users SET user_suspend='No' WHERE user_id =:userId");

					query.setParameter("userId", userId);
					int r = query.executeUpdate();

					transaction.commit();
					if (r > 0) {
						Message msg = new Message("User with ID: {" + userId + "} Unsuspended successfully.",
								"success", "alert-success");
						httpSession.setAttribute("status", msg);
						dispatcher = request.getRequestDispatcher("AdminPanel");
						dispatcher.forward(request, response);
						session.close();
						return;

					}
				}
				
			}
			
		}
		
		
		

		

		

	}

}
