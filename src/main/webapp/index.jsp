<%@page import="com.ecommerce.helper.Helper"%>
<%@page import="com.ecommerce.entities.Product"%>
<%@page import="com.ecommerce.Dao.ProductDao"%>
<%@page import="com.ecommerce.entities.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.ecommerce.Dao.CategoryDao"%>
<%@page import="com.ecommerce.helper.FactoryProvider"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@6.1.2/css/fontawesome.min.css" integrity="sha384-X8QTME3FCg1DLb58++lPvsjbQoCT9bp3MsUU3grbIny/3ZwUJkRNO8NPW6zqzuW9" crossorigin="anonymous">
<%@include file="components/cdn-container.jsp"%>
<title>OnlineShopping | Home</title>
</head>
<body>
    <%@include file="components/cart-modal.jsp" %>
	<%@include file="components/navbar.jsp"%>
    
    <%@include file="components/Message.jsp" %>
	<div class="container-fluid">
		<div class="row mt-4 mx-2">

			<%
			String catId = request.getParameter("category");

			CategoryDao catDao = new CategoryDao(FactoryProvider.getFactory());
			List<Category> catList = catDao.getCategories();

			ProductDao productDao = new ProductDao(FactoryProvider.getFactory());
			List<Product> productList = null;

			if (catId == null) {
				productList = productDao.getProducts();

			}

			else if (catId.trim().equals("all")) {
				productList = productDao.getProducts();
			} else {
				int cId = Integer.parseInt(catId.trim());
				productList = productDao.getCategoryFilter(cId);
			}
			%>
           
			<div class="col-md-2">

				<div class="list-group mt-4">

					<a href="index?category=all"
						class="list-group-item list-group-item-action active">All
						Categories</a>
					<%
					for (Category c : catList) {
					%>

					<a href="index?category=<%=c.getCategoryId()%>"
						class="list-group-item list-group-item-action"><%=c.getCategoryTitle()%></a>

					<%
					}
					%>


				</div>


			</div>

			<div class="col-md-10">

				<div class="row mt-4">
					<div class="col-md-12">

						<div class="card-columns">

							<%
							for (Product p : productList) {
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
											class="card-img-top m-2"
											src="users_upload/<%=p.getpPhoto()%>">
									</div>
									<div class="card-body">
										<h5 class="card-title text-center"><%=p.getpName()%></h5>
										<p class="card-text text-center"><%=Helper.shortDesc(p.getpDesc())%><a class="text-primary" href="ViewProduct?product_id=<%=p.getpId()%>">Read More</a></p>
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
										<button style="width: 250px;" type="submit" onclick="addToCart(<%=p.getpId() %>, '<%=p.getpName() %>', <%=p.getPrice() %>)"
											class="btn btn-danger mt-auto">Add to Cart</button>

									</div>


								</div>


								<%
								}
								%>


							</div>


						</div>

					</div>

					<%
					if (productList.size() == 0) {
						out.println("<h1>Sorry, No items found in this category.</h1>");
					}
					%>



				</div>



			</div>

		</div>

	</div>

	<script>

	
		$(document).ready(function() {
			$('a').click(function() {
				$('a.list-group-item.active').removeClass("active");
				$(this).addClass("active");
			});
		});

		 
		
	</script>
</body>
</html>