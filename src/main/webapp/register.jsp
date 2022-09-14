<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="components/cdn-container.jsp"%>
<link rel="stylesheet" href="css/style.css">
<title>eCommerce | Register</title>
</head>
<body>

    <%@include file="components/cart-modal.jsp" %>
	<%@include file="components/navbar.jsp"%>
	

	<div class="container-fluid">
	<div class="row mt-5">
		<div class="col-md-4 offset-md-4">
		
			<div class="container text-center mb-2">
			<img style="max-width: 100px;" src="img/add-user.png">
			<h3>Register Now</h3>
			</div>
			
			<div class="card">
			
				<div class="card text-black" style="border-radius: 25px;">
					<div class="card-body px-5">

						<hr>
						<%@include file="components/Message.jsp" %>
						<form action="RegisterNow" method="POST">

							<div class="form-group">
								<div class="d-flex flex-row align-items-center mb-4">
									<i class="fas fa-user fa-lg me-3 fa-fw"></i> <input name="user_name" type="text"
										class="form-control" id="username"
										 placeholder="Enter your name">
								</div>
							</div>
							<div class="form-group">
								<div class="d-flex flex-row align-items-center mb-4">
									<i class="fas fa-envelope fa-lg me-3 fa-fw"></i> <input
										name="user_email" type="email" class="form-control" id="useremail"
										aria-describedby="emailHelp"
										placeholder="Enter your email address">
								</div>
							</div>
							<div class="form-group">
								<div class="d-flex flex-row align-items-center mb-4">
									<i class="fas fa-lock fa-lg me-3 fa-fw"></i> <input
										name="user_password" type="password" class="form-control" id="userpassword"
										 placeholder="Enter your password">
								</div>
							</div>
							<div class="form-group">
								<div class="d-flex flex-row align-items-center mb-4">
									<i class="fas fa-key fa-lg me-3 fa-fw"></i><input name="user_confirm_password" type="password"
										class="form-control" id="userphone"
										 placeholder="Confirm Password">
								</div>
							</div>
							<div class="form-group">
								<div class="form-group">
									<div class="d-flex flex-row align-items-center mb-4">
										<i class="fas fa-phone fa-lg me-3 fa-fw"></i><input
											name="user_phone" type="number" class="form-control" id="userphone"
											 placeholder="Your Phone Number">
									</div>
								</div>
								<div class="form-group">
								
								
								<div class="form-group">
								<div class="d-flex flex-row align-items-center mb-4">
										<i class="fas fa-hand-pointer fa-lg me-3 fa-fw"></i>
								<select name="user_role"
									class="custom-select tm-select-accounts" id="category">
									<option selected>Select your role:</option>
									<option value="customer">Customer</option>
									<option value="seller">Seller</option>
								</select>
								</div>


									<i class="fas fa-location-dot fa-lg me-3 fa-fw"></i> <label>Address:</label>
									<textarea name="user_address" style="height: 150px;" class="form-control"
										placeholder="Enter your address"> </textarea>
								</div>
							</div>

							<hr>

							<div class="container text-center">

								<button type="submit" class="btn btn-success">Register
									Now</button>
								<button type="reset" class="btn btn-warning">Reset
									Fields</button>

							</div>
						</form>









					</div>



				</div>

			</div>
		</div>
	</div>
	
	
	
	
	
	
	
	
	
	</div>
<%@include file="components/cart-modal.jsp" %>

</body>
</html>