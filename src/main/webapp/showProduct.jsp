<%@page import="com.ecommerce.entities.Product"%>
<%@page import="com.ecommerce.Dao.ProductDao"%>
<%@page import="com.ecommerce.helper.FactoryProvider"%>
<%
int productId = Integer.parseInt(request.getParameter("product_id").trim());

ProductDao productDao = new ProductDao(FactoryProvider.getFactory());

Product product = (Product) productDao.getOneProducyById(productId);

%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">
<%@include file="components/cdn-container.jsp"%>
<meta charset="UTF-8">
<title><%=product.getpName() %> | Shopify</title>
</head>
<body>
     <%@include file="components/cart-modal.jsp" %>
	<%@include file="components/navbar.jsp"%>
	<div class="container">
		<div class="col-lg-8 border p-3 main-section bg-white">
			<div class="row hedding m-0 pl-3 pt-0 pb-3">Shopifiy By Muhammad Haris</div>
			<div class="row m-0">
				<div class="col-lg-4 left-side-product-box pb-3">
					<img src="users_upload/<%=product.getpPhoto() %>"
						class="border p-3">
				</div>
				<div class="col-lg-8">
					<div class="right-side-pro-detail border p-3 m-0">
						<div class="row">
							<div class="col-lg-12">
								<p class="m-0 p-0"><%=product.getpName() %></p>
							</div>
							<div class="col-lg-12">
								<p class="m-0 p-0 price-pro">Rs.<%=product.getPrice() %>/- <span><%=product.getpDiscount() %>% Off</span></p>
								<hr class="p-0 m-0">
							</div>
							<div class="col-lg-12 pt-2 mt-3">
								<h5>Product Discription:</h5>
								<span><%=product.getpDesc() %></span>
								<hr class="m-0 pt-2 mt-2">
							</div>
							<div class="col-lg-12">
								<p class="tag-section">
									<strong>Seller : </strong><a href="#!"><%=product.getSeller() %></a>
								</p>
							</div>
							<div class="col-lg-12 mt-3">
								<div class="row">
									<div class="col-lg-6 pb-2">
										<button type="submit" onclick="addToCart(<%=product.getpId()%>, '<%=product.getpName() %>', <%=product.getPrice() %>)" class="btn btn-danger w-100">Add To Cart</button>
									</div>
									<div class="col-lg-6">
										<a href="#" class="btn btn-success w-100">Shop Now</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			
			<%@include file="components/cart-modal.jsp" %>
			
</body>
</html>