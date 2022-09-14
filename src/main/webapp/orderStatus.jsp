<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css">
<%@include file="components/cdn-container.jsp"%>
<meta charset="UTF-8">
<title>Track your order Status</title>
</head>
<body>
	<%@include file="components/navbar.jsp"%>
	<div class="container text-center mt-3 font-weight-bold">
	<h1>Track your order Status here.</h1>
	</div>
	<div class="container text-center mt-5">
		<div class="card">
			<div class="card-body">
				<table class="table">
					<thead class="thead-light">
						<tr>
						<th>ORDER ID</th>
						<th>ORDER STATUS</th>
						<th>ORDER QUANTITIES</th>
						<th>Expected Delivery Time</th>
						<th>Action</th>
						</tr>
					</thead>
					
					<tr>
					<td>B561237090</td>
					<td>Processing</td>
					<td>10</td>
					<td>On Monday at 3:00 PM.</td>
					<td><button class='btn btn-danger btn-sm'>Cancel</button></td>
					</tr>
				</table>


			</div>


		</div>

	</div>
<%@include file="components/cart-modal.jsp" %>
</body>
</html>