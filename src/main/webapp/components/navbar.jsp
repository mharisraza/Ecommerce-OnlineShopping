<%@page import="com.ecommerce.entities.User"%>
<%
User user1 = (User) session.getAttribute("currentUser");
%>

<nav class="navbar navbar-expand-lg navbar-dark custom-bg">
	<div class="container">

		<a class="navbar-brand" href="index">eCommerce</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>


			 <form action="SearchProduct?=key(protected)" method="post" class="form-inline my-2 my-lg-0 mr-auto">
      <input required="required" name="search_bar" style="width: 300px;" class="form-control mr-sm-2" type="search" placeholder="Search any product here." aria-label="Search">
      <button class="btn btn-primary my-2 my-sm-0" type="submit">Search</button>
    </form> 
    
   

			<ul class="navbar-nav ml-auto">
			
			 <button data-toggle="modal" data-target="#cart-modal-viewer" class="btn btn-outline-light" type="submit">
                            <i class="fa fa-cart-plus me-1"></i>
                            Cart
                            <span class="badge bg-dark text-white ms-1 rounded-pill cart-items"></span>
                        </button>

				<%
				if (user1 == null) {
				%>

				<li class="nav-item active"><a class="nav-link"
					href="Login?key=(protected)">Login</a></li>

				<li class="nav-item active"><a class="nav-link"
					href="Register?key=(protected)">Register</a></li>
					<% 
				} else if(user1.getUserSuspend().equals("Yes")) {

					%>
					
					<li class="nav-item active"><a class="nav-link"
					href="Login?key=(protected)">Login</a></li>

				<li class="nav-item active"><a class="nav-link"
					href="Register?key=(protected)">Register</a></li>
					
					
					
					


				<% 
				} else {
				%>

				<li class="nav-item active mb-1"><a class="nav-link"
					href="#!"><%=user1.getUserName().toUpperCase() %></a></li>

				<li class="nav-item active"><a class="nav-link"
					href="LogOut">Logout</a></li>
					
					<%
				}
					
					%>

                 
				


			</ul>
			
			



	</div>
</nav>



    