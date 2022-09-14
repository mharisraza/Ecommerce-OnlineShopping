<%@page import="com.ecommerce.entities.Message"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.ecommerce.helper.Helper"%>
<%@page import="com.ecommerce.entities.Category"%>
<%@page import="com.ecommerce.Dao.CategoryDao"%>
<%@page import="org.hibernate.Query"%>
<%@page import="org.hibernate.Transaction"%>
<%@page import="org.hibernate.Session"%>
<%@page import="com.ecommerce.entities.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.ecommerce.helper.FactoryProvider"%>
<%@page import="com.ecommerce.Dao.ProductDao"%>
<%
String searchKey = request.getParameter("search_bar");

if(searchKey.equals("") || searchKey==null | searchKey.isEmpty()) {
	Message msg = new Message("Sorry, please search anything", "error", "alert-danger");
	session.setAttribute("msg", msg);
	response.sendRedirect("index");
	return;
}

ProductDao productDao = new ProductDao(FactoryProvider.getFactory());
List<Product> searchBarList = productDao.getProductsForSearchBar(searchKey);
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<%@include file="components/cdn-container.jsp"%>
<link rel="stylesheet" type="text/css" href="css/style.css">
<meta charset="UTF-8">
<title>
	<%
	out.println(searchKey);
	%> | Shopify
</title>
<body>
	<%@include file="components/cart-modal.jsp"%>
	<%@include file="components/navbar.jsp"%>

	<div class="container-fluid">
	<%@include file="components/Message.jsp" %>
	<%
	if(searchBarList.size()==0) {
		out.println("<h1 class='text-center mt-4'>Sorry we cannot found the item for your search.</h1>");
	}
	else {
	 %>
	<h1 class="text-center mt-3">Results for your search: <%out.println(searchKey); %></h1>
	<%} %>
		<div class="row mt-4 mx-2">
			<div class="col-md-12">
				<div class="card-columns">
					<%
					for (Product p : searchBarList) {
					%>
					<div class="col mb-2">

						<div style="width: 25rem;" class="card h-100">
							<div class="container text-center">
								<%
								if (p.getpDiscount() > 0) {
								%><div class="badge bg-dark text-white position-absolute"
									style="top: 0.5rem; left: 0.5rem">Sale</div>
								<%
								}
								%>
								<img style="max-height: 200px; max-width: 100%; width: auto;"
									class="card-img-top m-2" src="users_upload/<%=p.getpPhoto()%>">
							</div>
							<div class="card-body">
								<h5 class="card-title text-center"><%=p.getpName()%></h5>
								<p class="card-text text-center"><%=Helper.shortDesc(p.getpDesc())%><a
										class="text-primary"
										href="showProduct.jsp?product_id=<%=p.getpId()%>">Read
										More</a>
								</p>
								<h6 class="card-text text-center">
									Rs.<%=p.getPrice()%>/-
								</h6>
								<%
								if (p.getpDiscount() > 0) {
								%><p class="card-text text-center op-price">
									Rs.<%=p.getpPrice()%>/- <span style="text-decoration: none;"><%=p.getpDiscount()%>%
										Off</span>
								</p>
								<%
								}
								%>
							</div>

							<div class="card-footer text-center">
								<button style="width: 250px;" type="submit"
									onclick="addToCart(<%=p.getpId()%>, '<%=p.getpName()%>', <%=p.getPrice()%>)"
									class="btn btn-danger mt-auto">Add to Cart</button>

							</div>


						</div>


						<%
						}
						%>


					</div>





				</div>



			</div>


		</div>

	</div>




</body>
</html>