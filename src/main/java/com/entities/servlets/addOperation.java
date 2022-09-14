package com.entities.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.tomcat.util.buf.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.MultipartStream;

import com.ecommerce.Dao.CategoryDao;
import com.ecommerce.Dao.ProductDao;
import com.ecommerce.entities.Category;
import com.ecommerce.entities.Message;
import com.ecommerce.entities.Product;
import com.ecommerce.entities.User;
import com.ecommerce.helper.FactoryProvider;

import net.bytebuddy.utility.RandomString;

/**
 * Servlet implementation class addOperation
 */
@MultipartConfig
public class addOperation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addOperation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// fetching category data
		HttpSession httpSession = request.getSession();

		String operation = request.getParameter("operation");

		if (operation.trim().equals("addCategory")) {

			String catTitle = request.getParameter("catTitle");
			String catDesc = request.getParameter("catDesc");
			User user = (User) httpSession.getAttribute("currentUser");
			int userId = user.getUserId();

			if (catTitle.isEmpty()) {
				Message msg = new Message("Category Title cannot be blank.", "error", "alert-danger");
				httpSession.setAttribute("status", msg);
				response.sendRedirect("SellerPanel");
				return;
			}
			if (catDesc.isEmpty()) {
				Message msg = new Message("Category Description cannot be blank.", "error", "alert-danger");
				httpSession.setAttribute("status", msg);
				response.sendRedirect("SellerPanel");
				return;
			}

			Category cat = new Category();
			cat.setCategoryTitle(catTitle);
			cat.setCategoryDesc(catDesc);
			cat.setUser_id(userId);

			CategoryDao categoryDao = new CategoryDao(FactoryProvider.getFactory());
			int catId = categoryDao.saveCategory(cat);

			if (catId > 0) {
				Message msg = new Message("Category added successfully", "success", "alert-success");
				httpSession.setAttribute("status", msg);
				response.sendRedirect("AdminPanel");
				return;

			}

		} else if (operation.trim().equals("addProduct")) {

			// creating objects
			
			

			String productName = request.getParameter("product_name");
			String productDesc = request.getParameter("product_description");
			int productPrice = Integer.parseInt(request.getParameter("product_price"));
			int productStocks = Integer.parseInt(request.getParameter("product_stocks"));
			int productDiscount = Integer.parseInt(request.getParameter("product_discount"));
			int productCategoryId = Integer.parseInt(request.getParameter("product_categoryId"));
			Part productPhoto = request.getPart("product_photos");
			
			
			// **** Start Checking and returning user if user input anything blank/empty **********
			
			if(productName.isEmpty()) {
				Message msg = new Message ("Product name cannot be blank.", "error", "alert-danger");
				httpSession.setAttribute("status", msg);
				response.sendRedirect("SellerPanel");
				return;
			}
			
			if(productDesc.isEmpty()) {
				Message msg = new Message ("Product description cannot be blank.", "error", "alert-danger");
				httpSession.setAttribute("status", msg);
				response.sendRedirect("SellerPanel");
				return;
			}
			
			if(productStocks==0) {
				Message msg = new Message ("Product stocks cannot be blank.", "error", "alert-danger");
				httpSession.setAttribute("status", msg);
				response.sendRedirect("SellerPanel");
				return;
			}
			
			if(productPrice==0) {
				Message msg = new Message ("Product price cannot be blank.", "error", "alert-danger");
				httpSession.setAttribute("status", msg);
				response.sendRedirect("SellerPanel");
				return;
			}
			
			// *** END CHECKING.
			
			// **** Start Validating details if user input is okay > PROCEED, ELSE RETURN.
			
			if(!productPhoto.getSubmittedFileName().endsWith(".png")) {
				Message msg = new Message ("Sorry, only PNG Image are Supported.", "error", "alert-danger");
				httpSession.setAttribute("status", msg);
				response.sendRedirect("SellerPanel");
				return;
				
			}
			
			if(productPrice<1) {
				Message msg = new Message ("Produce price cannot be negative.", "error", "alert-danger");
				httpSession.setAttribute("status", msg);
				response.sendRedirect("SellerPanel");
				return;
				
			}
			if(productStocks<1) {
				Message msg = new Message ("Product Stocks cannot be negative.", "error", "alert-danger");
				httpSession.setAttribute("status", msg);
				response.sendRedirect("SellerPanel");
				return;
			}
			if(productDiscount==100) {
				Message msg = new Message ("Product discount cannot be 100% off. ", "error", "alert-danger");
				httpSession.setAttribute("status", msg);
				response.sendRedirect("SellerPanel");
				return;
			}
				else if(productDiscount>100) {
					Message msg = new Message ("Product discount cannot be greater than 99%. ", "error", "alert-danger");
					httpSession.setAttribute("status", msg);
					response.sendRedirect("SellerPanel");	
					return;
				}
			
			// **** End Validating details module.
			
			
			
			// if both above value are okay then Proceeding adding products.
		
			// getting userId for validation.
			
			User user = (User) httpSession.getAttribute("currentUser");
			int userId = user.getUserId();

			// generating randomString incase if files name match in folder or database.
			String generatedString = usingRandomUUID();
			int len = 30;
			
			


			String file = generatedString + "_" + productPhoto.getSubmittedFileName();

			String path = request.getRealPath("users_upload") + File.separator + file; // getting path of the file.
			
			
			
			// adding the photos to the folder
			
			FileOutputStream fos = new FileOutputStream(path);
			InputStream fis = productPhoto.getInputStream();
			byte[] data = new byte[fis.available()];

			fis.read(data);

			fos.write(data);
			
			//now adding products to the database.

			Product product = new Product();
			product.setpName(productName);
			product.setpDesc(productDesc);
			product.setpPrice(productPrice);
			product.setpQuantity(productStocks);
			product.setpDesc(productDesc);
			product.setpDiscount(productDiscount);
			product.setpPhoto(file);
			product.setUserId(userId);
			product.setSeller(user.getUserName());

			CategoryDao catDao = new CategoryDao(FactoryProvider.getFactory());
			Category cat = catDao.getCategoryById(productCategoryId);
			product.setCategory(cat);

			ProductDao pDao = new ProductDao(FactoryProvider.getFactory());
			boolean p = pDao.saveProduct(product);

			fos.close();

			fis.close();
			
			
			// save 
			if (p == true) { 
				
				Message msg = new Message("Product added successfully.", "success", "alert-success");
				httpSession.setAttribute("status", msg);
				response.sendRedirect("SellerPanel");
				return;
             
				// incase everything is wrong but server side occuring error while adding product then showing this:=
			} else { 
				 
				Message msg = new Message("An Error has occurred while adding product.", "error", "alert-danger");
				httpSession.setAttribute("status", msg);
				response.sendRedirect("SellerPanel");
				return;

			}

		}
	}

	// generating random string
	static String usingRandomUUID() {

		UUID randomUUID = UUID.randomUUID();

		return randomUUID.toString().replaceAll("-", "");

	}

}
