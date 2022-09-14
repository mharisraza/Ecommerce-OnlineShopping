package com.entities.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ecommerce.entities.Message;
import com.ecommerce.entities.Product;
import com.ecommerce.entities.User;
import com.ecommerce.helper.FactoryProvider;

/**
 * Servlet implementation class deleteProduct
 */
public class deleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int productId = Integer.parseInt(request.getParameter("product_id").trim());
		
		HttpSession httpSession = request.getSession();
		
		Session session = FactoryProvider.getFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		User user = (User) httpSession.getAttribute("currentUser");
		
		if(user==null) {
			Message msg = new Message("Please login to continue.", "error", "alert-danger");
			httpSession.setAttribute("status", msg);
			response.sendRedirect("Login");
			return;
		}
		else if(user.getUserType().equals("customer")) {
			Message msg = new Message("Sorry, Something went wrong.", "error", "alert-danger");
			httpSession.setAttribute("status", msg);
			response.sendRedirect("index");
			return;
			
		}
		
		else {
			
			Product product = session.get(Product.class, productId);
			session.delete(product);
			transaction.commit();	
			session.close();
			
			if(product!=null) {
				Message msg = new Message("Product deleted successfully.", "success", "alert-success");
				httpSession.setAttribute("status", msg);
				response.sendRedirect("SellerPanel");
				return;
			}
			
			else {
				Message msg = new Message("Sorry, Something went wrong.", "error", "alert-danger");
				httpSession.setAttribute("status", msg);
				response.sendRedirect("SellerPanel");
				return;
			}
			
			
		}
		
		
		
	}

}
