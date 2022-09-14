package com.entities.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ecommerce.Dao.CartDao;
import com.ecommerce.entities.Cart;
import com.ecommerce.entities.Product;
import com.ecommerce.entities.User;
import com.ecommerce.helper.FactoryProvider;

/**
 * Servlet implementation class orderNow
 */
public class orderNow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public orderNow() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession httpSession = request.getSession();
		
		int userId = Integer.parseInt(request.getParameter("user_id").trim());
		int totalPrice = Integer.parseInt(request.getParameter("total_price").trim());
		
		String[] str = request.getParameterValues("product_id");
		int size = str.length;
		int[] arr = new int [size];
		for(int i=0; i<size; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}
		
		String[] str1 = request.getParameterValues("product_quantities");
		int size1 = str1.length;
		int[] arr1 = new int [size1];
		for(int i=0; i<size; i++) {
			arr1[i] = Integer.parseInt(str1[i]);
		}
		
		User user = (User) httpSession.getAttribute("currentUser");
		
		Session session = FactoryProvider.getFactory().openSession();
		Transaction transaction = session.beginTransaction();
		Cart cart = new Cart();
		
		
		int generatedOrderId = new Random().nextInt(789906139);
		String orderId = "BKE" + generatedOrderId; 
		
		
		cart.setOrderId(orderId);
		cart.setOrderBy(user.getUserName());
		cart.setOrderDate(new Date());
		cart.setOrderTotalPrice(totalPrice);
		cart.setOrderQuantities(Arrays.toString(arr1));
		cart.setOrderStatus("Processing");
		
		
		// setting expected date after 7 days after ordering the item.
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 7);

		
		cart.setDeliveryDate(cal.getTime()); // getting expected date after 7 days after ordering the item.
		cart.setProductIds(Arrays.toString(arr));
		
		CartDao cartDao = new CartDao(FactoryProvider.getFactory());
		cartDao.saveToCart(cart);
		
		transaction.commit();
		session.close();
		

		
		
		
		
		
		
	}

}
