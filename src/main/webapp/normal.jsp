<%@page import="com.ecommerce.entities.Message"%>
<%
User user = (User) session.getAttribute("currentUser");
if (user == null) {

	Message msg = new Message("Please login to continue.", "error", "alert-danger");
	session.setAttribute("status", msg);
	response.sendRedirect("Login");
	return;
}

else if(user.getUserSuspend().equals("Yes")) {
	Message msg = new Message("Sorry, Admin suspended your account.", "error", "alert-danger");
	session.setAttribute("status", msg);
	session.removeAttribute("currentUser");
	response.sendRedirect("Login");
	return;
	

	}
else if(user.getUserType().equals("seller")) {
	response.sendRedirect("SellerPanel");
	return;
}
else {
	
}
%>




<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="components/cdn-container.jsp"%>
<link rel="stylesheet" type="text/css" href="style.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<meta charset="UTF-8">
<title>Welcome to eCommerce</title>
</head>
<body>
	<%@include file="components/navbar.jsp"%>
	<%@include file="components/cart-modal.jsp" %>
	
	

</body>
</html>
