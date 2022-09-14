<%@page import="com.ecommerce.entities.User"%>
<%
User user = (User) session.getAttribute("currentUser");

 if(user!=null) {
	 Message msg = new Message("You're already logged in.", "warning", "alert-warning");
	 session.setAttribute("status", msg);
	 if(user.getUserType().equals("admin")) {
		 response.sendRedirect("AdminPanel");
		 return;
	 }
	 else if(user.getUserType().equals("seller")) {
		 Message msg1 = new Message("You're already logged in.", "warning", "alert-warning");
		 session.setAttribute("status", msg);
		 response.sendRedirect("SellerPanel");
		 return;
		 
	 }
	 else if(user.getUserType().equals("customer")) {
		 Message msg1 = new Message("You're already logged in.", "warning", "alert-warning");
		 session.setAttribute("status", msg);
		 response.sendRedirect("home");
		 return;
		 
	 }
	 else {
		 
	 }
	 
 }


%>
	


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="components/cdn-container.jsp"%>
<link rel="stylesheet" href="css/style.css">
<title>eCommerce | Login</title>
</head>
<body>
    <%@include file="components/cart-modal.jsp" %>
	<%@include file="components/navbar.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col-md-6 offset-md-3">
				<div class="card mt-5">
					<div class="card-header custom-bg text-white">
						<h2>Login Here</h2>
					</div>
					<div class="card-body">
						<%@include file="components/Message.jsp"%>
						<form action="LoginServ" method="POST">
							<div class="form-group">
								<div class="d-flex flex-row align-items-center mb-4">
									<i class="fas fa-envelope fa-lg me-3 fa-fw"></i> <input
										name="user_email" type="email" class="form-control"
										id="exampleInputEmail1" aria-describedby="emailHelp"
										placeholder="Enter your email">
								</div>

							</div>
							<div class="form-group">
								<div class="d-flex flex-row align-items-center mb-4">
									<i class="fas fa-lock fa-lg me-3 fa-fw"></i> <input
										name="user_password" type="password" class="form-control"
										id="exampleInputPassword1" placeholder="Password">
								</div>
							</div>

							<div class="container text-center">
								<button style="width: 250px;" type="submit"
									class="btn btn-primary">Log In</button>
								<hr>
							</div>
							<div class="container text-center">
								<a href="#!" class="btn btn-danger">Forgot Password?</a> <a
									href="register.jsp" class="btn btn-success">Create Account</a>

							</div>
						</form>
					</div>




				</div>

			</div>


		</div>
	</div>
	
	<%@include file="components/cart-modal.jsp" %>
</body>
</html>