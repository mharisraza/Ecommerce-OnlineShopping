<%@page import="com.ecommerce.entities.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.ecommerce.Dao.ProductDao"%>
<%@page import="com.ecommerce.helper.FactoryProvider"%>
<%@page import="com.ecommerce.entities.Message"%>
<%@page import="com.ecommerce.entities.User"%>

<%
User user3 = (User) session.getAttribute("currentUser");


if(user3==null) {
	Message msg3 = new Message("You need to login to order items.", "error", "alert-danger");
	session.setAttribute("status", msg3);
	response.sendRedirect("login.jsp");
	return;
}


%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<%@include file="components/cdn-container.jsp"%>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="components/navbar.jsp"%>

	<div class="container">
	<form action="orderNow" method="post">
		<div class="row mt-5">
			
				<div class="col-md-6 my-3">

					<div class="card">
						<div class="card-body">
							<h3 class="text-center mb-3">Your Selected Items</h3>
							<div class="cart-body"></div>
						</div>
					</div>

				</div>
				<div class="col-md-6 my-3">
					<div class="card">
						<div class="card-body">
							<h3 class="text-center mb-3">Your Details for the Order</h3>
                            
                            <input type="hidden" name="user_id" value="<%=user3.getUserId()%>">
							<label>Your Name:</label> <input readonly="readonly"
								class="form-control" value="<%=user3.getUserName()%>">
							<label>Your Email Address:</label> <input readonly="readonly"
								class="form-control" value="<%=user3.getUserEmail()%>">
							<label>Your Phone Number:</label> <input readonly="readonly"
								class="form-control" value="+<%=user3.getUserPhone()%>">
							<label>Your Shipping Address:</label>
							<textarea class="form-control"><%=user3.getUserAddress()%></textarea>
							<div class="container text-center mt-3">
								<button onclick="deleteAllProductsFromCart()" style="width: 250px;" class="btn btn-success"
									type="submit">Order Now</button>
							</div>




						</div>


					</div>
				</div>

			
		</div>


</form>
	</div>
	
	<%@include file="components/cart-modal.jsp" %>

</body>
</html>