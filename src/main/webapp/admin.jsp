<%@page import="com.ecommerce.entities.Product"%>
<%@page import="com.ecommerce.Dao.ProductDao"%>
<%@page import="com.ecommerce.Dao.UserDao"%>
<%
CategoryDao category = new CategoryDao(FactoryProvider.getFactory());
List<Category> list = category.getCategories();

ProductDao pDao = new ProductDao(FactoryProvider.getFactory());
List<Product> plist = pDao.getProducts();
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

else if (user.getUserType().equals("customer") || user.getUserType().equals("seller")) {
	Message msg = new Message("Unable to access this page", "warning", "alert-warning");
	session.setAttribute("status", msg);
	if (user.getUserType().equals("customer")) {
		response.sendRedirect("home");
		return;
	}

	else if (user.getUserType().equals("seller")) {
		Message msg1 = new Message("Unable to access this page", "warning", "alert-warning");
		session.setAttribute("status", msg1);
		response.sendRedirect("SellerPanel");
		return;
	}

}

UserDao userDao = new UserDao(FactoryProvider.getFactory());
List<User> userList = userDao.getUsers();
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
<title>eCommerce | Admin Panel</title>
</head>
<body>
	<%@include file="components/navbar.jsp"%>
	<br>



	<h1 class="text-center">Welcome To Admin Panel</h1>






	<div class="container admin">
		<%@include file="components/Message.jsp"%>
		<div class="row mt-5">

			<div class=" col-md-4">
				<div class="card" data-toggle="modal" data-target="#users-modal">
					<div class="card-body text-center">

						<div class="container">
							<img style="max-width: 125px;" class="img-fluid"
								src="img/users.png" alt="users_icon">

						</div>
						<h1 class="text-muted">Users</h1>
						<h2><%=userList.size() %></h2>

					</div>

				</div>


			</div>
			<div class=" col-md-4">

				<div class="card" data-toggle="modal" data-target="#category-viewer-modal">
					<div class="card-body text-center">
						<div class="container">
							<img style="max-width: 125px;" class="img-fluid rounded-circle"
								src="img/list.png" alt="users_icon">

						</div>
						<h1 class="text-muted">Categories</h1>
						<h2><%=list.size()%></h2>

					</div>

				</div>



			</div>
			<div class=" col-md-4">

				<div class="card" data-toggle="modal" data-target="#product-viewer-modal">
					<div class="card-body text-center">
						<div class="container">
							<img style="max-width: 125px;" class="img-fluid rounded-circle"
								src="img/products.png" alt="users_icon">

						</div>
						<h1 class="text-muted">Products</h1>
						<h2><%=plist.size() %></h2>

					</div>

				</div>



			</div>

		</div>


	</div>

	<div class="container admin mt-3 text-center">
	
	
		<div class="card" data-toggle="modal" data-target="#category-modal">
			<div class="card-body text-center">
				<div class="container">
					<img style="max-width: 125px;" class="img-fluid rounded-circle"
						src="img/plus.png" alt="users_icon">

				</div>

				<h1 class="text-muted">Add Category</h1>
				<p>Click here to add new category</p>


			</div>

		</div>


	</div>
	
	
	</div>

	<!-- add category modallllll  -->



	<!-- Modal -->
	<div class="modal fade" id="category-modal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header custom-bg text-white">
					<h5 class="modal-title" id="exampleModalLabel">Add Category</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">

					<form action="addOperation" method="POST">
						<input type="hidden" name="operation" value="addCategory">

						<div class="form-group">
							<input type="text" class="form-control" name="catTitle"
								placeholder="Enter category title" required>

						</div>

						<div class="form-group">
							<textarea style="heigh: 250px;" class="form-control"
								name="catDesc" required placeholder="Enter category description"></textarea>

						</div>

						<div class="container text-center">

							<button type="submit" class="btn btn-success">Add
								Category</button>
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>

						</div>


					</form>

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

	<!-- End Product Modal -->

	<!-- add category modallllll  -->



	<!-- Modal -->
	<div class="modal fade" id="category-modal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header custom-bg text-white">
					<h5 class="modal-title" id="exampleModalLabel">Add Category</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">

					<form action="addOperation" method="POST">
						<input type="hidden" name="operation" value="addCategory">

						<div class="form-group">
							<input type="text" class="form-control" name="catTitle"
								placeholder="Enter category title" required>

						</div>

						<div class="form-group">
							<textarea style="heigh: 250px;" class="form-control"
								name="catDesc" required placeholder="Enter category description"></textarea>

						</div>

						<div class="container text-center">

							<button type="submit" class="btn btn-success">Add
								Category</button>
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>

						</div>


					</form>

				</div>

			</div>
		</div>
	</div>

	<!-- Start Users modal -->

	<!-- Modal -->
	<div class="modal fade" id="users-modal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header custom-bg text-white">
					<h5 class="modal-title" id="exampleModalLabel">View Users</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">


					<%
					for (User c : userList) {
					%>


					<div class="row">
						<div class="col-lg-12">
							<div class="main-box no-header clearfix">
								<div class="main-box-body clearfix">
									<div class="table-responsive">
										<table class="table user-list">
											<thead>
												<tr>
													<th><span>User</span></th>
													<th><span>Created</span></th>
													<th class="text-center"><span>Suspend Status</span></th>
													<th><span>Email</span></th>
													<th>&nbsp;</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td><img
														src="https://bootdey.com/img/Content/user_1.jpg" alt="">
														<a href="#" class="user-link"><%=c.getUserName()%></a> <span
														class="user-subhead">Role: <%=c.getUserType()%></span></td>
													<td><%=c.getAddedDate()%></td>
													<td class="text-center"><span
														class="label label-default">Suspended: <%=c.getUserSuspend()%></span></td>
													<td><a href="#"><%=c.getUserEmail()%></a></td>
													<td style="width: 20%;"><a
														href="suspendUser?user_id=<%=c.getUserId()%>&user_status=<%=c.getUserSuspend()%>&user_type=<%=c.getUserType()%>"
														class="table-link text-danger"> <span class="fa-stack">
																<i class="fa fa-square fa-stack-2x"></i> <i
																class="fa fa-circle-minus fa-stack-1x fa-inverse"></i>
														</span>

													</a> <a href="deleteUser?user_id=<%=c.getUserId()%>"
														class="table-link danger"> <span class="fa-stack">
																<i class="fa fa-square fa-stack-2x"></i> <i
																class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
														</span>
													</a></td>
												</tr>
												<tr>
													<td></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>

					<%
					}
					%>


				</div>

			</div>
		</div>
	</div>



	<!-- Start CAtegory Modal Viewer -->

	<div class="modal fade" id="category-viewer-modal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header custom-bg text-white">
					<h5 class="modal-title" id="exampleModalLabel">View or Edit Categories</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">

					<%
					for (Category c : list) {
					%>

					<div class="card">
						<h5 class="card-header"><%=c.getCategoryTitle()%></h5>
						<div class="card-body">
							<p class="card-text"><%=c.getCategoryDesc()%></p>
						</div>
					</div>





					<%
					}
					%>

				</div>

			</div>
		</div>
	</div>

	<!-- Ending Category Modal viewer -->
	
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
											<th>Product Name</th>
											<th>Product inStocks</th>
											<th>Product Price</th>
											<th>Product Discount</th>
											<th>Added By</th>
											<th>Action</th>
										</tr>
									</thead>

									<tr>
									<% for(Product p: plist) { %>
										<td><%=p.getpName() %></td>
										<td><%=p.getpQuantity() %></td>
										<td>Rs.<%=p.getpPrice() %>/-</td>
										<td><%=p.getpDiscount() %>% OFF</td>
										<td><%=p.getSeller() %></td>
										<td style="width: 20%;"><a
											href="#!"
											class="table-link text-danger"> <span class="fa-stack">
													<i class="fa fa-square fa-stack-2x"></i> <i
													class="fa fa-edit fa-stack-1x fa-inverse"></i>
											</span>
											
											</a> <a href="#!"
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