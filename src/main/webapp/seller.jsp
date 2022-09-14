<%@page import="com.ecommerce.entities.Product"%>
<%@page import="com.ecommerce.Dao.ProductDao"%>
<%@page import="com.ecommerce.Dao.UserDao"%>
<%
CategoryDao category = new CategoryDao(FactoryProvider.getFactory());
List<Category> list = category.getCategories();
%>


<%@page import="com.ecommerce.entities.Category"%>
<%@page import="java.util.List"%>
<%@page import="com.ecommerce.helper.FactoryProvider"%>
<%@page import="com.ecommerce.Dao.CategoryDao"%>
<%@page import="com.ecommerce.entities.Message"%>
<%@page import="com.ecommerce.entities.User"%>
<%
User user = (User) session.getAttribute("currentUser");
if (user == null) {

	Message msg = new Message("Please login to continue.", "error", "alert-danger");
	session.setAttribute("status", msg);
	RequestDispatcher dispatcher = request.getRequestDispatcher("Login");
	dispatcher.forward(request, response);
	return;
}

else if (user.getUserSuspend().equals("Yes")) {
	Message msg1 = new Message("Sorry, Admin suspended your account.", "error", "alert-danger");
	session.setAttribute("status", msg1);
	session.removeAttribute("currentUser");
	response.sendRedirect("Login");
	return;
}

else if (user.getUserType().equals("admin")) {
	Message msg1 = new Message("You're Admin, you cannot access that page.", "error", "alert-danger");
	session.setAttribute("status", msg1);
	session.removeAttribute("currentUser");
	response.sendRedirect("AdminPanel");
	return;
}

else if (user.getUserType().equals("customer")) {
	Message msg1 = new Message("You're Customer, you cannot access that page.", "error", "alert-danger");
	session.setAttribute("status", msg1);
	session.removeAttribute("currentUser");
	response.sendRedirect("home");
	return;
}
%>

<%
ProductDao pDao = new ProductDao(FactoryProvider.getFactory());
List<Product> plist = pDao.getProductById(user.getUserId());
%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css">
<%@include file="components/cdn-container.jsp"%>
<link rel="stylesheet" type="text/css" href="users.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="style.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:400,700" />

<meta charset="UTF-8">
<title>eCommerce | Seller Panel</title>
</head>
<body>
	<%@include file="components/navbar.jsp"%>
	<br>



	<h1 class="text-center">
		Welcome:
		<%=user.getUserName().toUpperCase()%></h1>

	<div class="container admin">
		<%@include file="components/Message.jsp"%>
		<div class="row mt-5">


			<div class=" col-md-6">

				<div class="card" data-toggle="modal" data-target="#product-modal">
					<div class="card-body text-center">
						<div class="container">
							<img style="max-width: 125px;" class="img-fluid rounded-circle"
								src="img/add.png" alt="users_icon">

						</div>
						<h1 class="text-muted">Add Product</h1>
						<p>Click here to add new product</p>

					</div>

				</div>

			</div>


			<div class=" col-md-6">

				<div class="card" data-toggle="modal"
					data-target="#product-viewer-modal">
					<div class="card-body text-center">
						<div class="container">
							<img style="max-width: 125px;" class="img-fluid rounded-circle"
								src="img/products.png" alt="users_icon">

						</div>
						<h1 class="text-muted">Your Products</h1>
						<p>Click here to view your products.</p>

					</div>

				</div>



			</div>

		</div>

		<!--  End Category modall -->

		<!-- STart Product Modal -->

		<div class="modal fade" id="product-modal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header custom-bg text-white">
						<h5 class="modal-title" id="exampleModalLabel">Add Product</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">

						<%
						Message message1 = (Message) session.getAttribute("status");
						if (message1 != null) {
						%>
						<div
							class="alert <%=message1 != null ? message1.getCssClass() : ""%> alert-dismissible fade show"
							role="<%=message1 != null ? message1.getCssClass() : ""%>">
							<strong><%=message1 != null ? message1.getContent() : ""%></strong>
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<%
						}
						session.removeAttribute("status");
						%>

						<%@include file="add-products.jsp"%>



					</div>

				</div>
			</div>
		</div>

		<!-- Start Product Modal Viewer -->

		<div class="modal fade" id="product-viewer-modal" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-xl" role="document">
				<div class="modal-content">
					<div class="modal-header custom-bg text-white">
						<h5 class="modal-title" id="exampleModalLabel">View Your
							Products</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">



						<div class="card">
							<div class="card-body">
								<table class="table">
									<thead class="thead-light">
										<tr>
										    <th>Product Image</th>
											<th>Product Name</th>
											<th>Product inStocks</th>
											<th>Product Price</th>
											<th>Product Discount</th>
											<th>Action</th>
										</tr>
									</thead>

									<tr>
									<% for(Product p: plist) { %>
									    <td><img style="width: 50px; height: 50px;" alt="" src="users_upload/<%=p.getpPhoto()%>"></td>
										<td><%=p.getpName() %></td>
										<td><%=p.getpQuantity() %></td>
										<td>Rs.<%=p.getpPrice() %>/-</td>
										<td><%=p.getpDiscount() %>% OFF</td>
										<td style="width: 20%;"><a
											href="#!"
											class="table-link text-danger"> <span class="fa-stack">
													<i class="fa fa-square fa-stack-2x"></i> <i
													class="fa fa-edit fa-stack-1x fa-inverse"></i>
											</span>
											
											</a> <a href="deleteProduct?product_id=<%=p.getpId() %>"
														class="table-link danger"> <span class="fa-stack">
																<i class="fa fa-square fa-stack-2x"></i> <i
																class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
														</span>
											</a></td>
									</tr>
									<%} %>
								</table>



							</div>
						</div>

					</div>

				</div>
			</div>
		</div>

		<!-- Ending Product Modal viewer -->
</body>
</html>