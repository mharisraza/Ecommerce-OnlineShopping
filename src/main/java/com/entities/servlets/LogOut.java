package com.entities.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecommerce.entities.Message;
import com.ecommerce.entities.User;

/**
 * Servlet implementation class LogOut
 */
public class LogOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogOut() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession();
		RequestDispatcher dispatcher = null;
		
		User user = (User)httpSession.getAttribute("currentUser");
		if(user == null) {
			Message msg = new Message("You're not logged in.", "error", "alert-danger");
			httpSession.setAttribute("status", msg);
			response.sendRedirect("Login");
			return;
			
		}
		else {
			httpSession.removeAttribute("currentUser");
			Message msg = new Message("Logout Successfully, Thanks for using our service!", "success", "alert-success");
			httpSession.setAttribute("status", msg);
			response.sendRedirect("Login");
			return;
			
		}
		
		
		
	}

}
