<%@page import="java.util.List"%>
<%@page import="com.ecommerce.Dao.CategoryDao"%>
<%@page import="com.ecommerce.entities.Category"%>
<%@page import="com.ecommerce.helper.FactoryProvider"%>
<div class="container tm-mt-big tm-mb-big">
	<div class="row">
		<div class="col-xl-9 col-lg-10 col-md-12 col-sm-12 mx-auto">
			<div class="tm-bg-primary-dark tm-block tm-block-h-auto">
				<div class="row">
					<div class="col-12">
						<h2 class="tm-block-title d-inline-block">Add Product</h2>
					</div>
				</div>

				<div class="row tm-edit-product-row">

					<div class="col-xl-6 col-lg-6 col-md-12">
						<form method="POST" action="addOperation" enctype="multipart/form-data" class="tm-edit-product-form">
						
                              <input type="hidden" name="operation" value="addProduct">
							<div class="form-group mb-3">
								<label for="name">Product Name </label> <input id="name"
									name="product_name" type="text" class="form-control validate" required />
							</div>
							<div class="form-group mb-3">
								<label for="description">Description</label>
								<textarea name="product_description" class="form-control validate" rows="3" required></textarea>
							</div>
							
							<%		
							CategoryDao cat = new CategoryDao(FactoryProvider.getFactory());
							List<Category> list2 = cat.getCategories();			
							%>
							<div class="form-group mb-3">
								<label for="category">Category</label> <select name="product_categoryId"
									class="custom-select tm-select-accounts" id="category">
									
									<%
									for(Category c: list2) {
									%>
									
									<option value="<%=c.getCategoryId()%>"><%=c.getCategoryTitle() %></option>
									<%	
									}
									%>
									
								</select>
							</div>
							<div class="row">
								<div class="form-group mb-3 col-xs-12 col-sm-6">
									<label for="expire_date">Price</label> <input name="product_price"
										id="expire_date" type="number"
										class="form-control validate" data-large-mode="true" />
								</div>
								<div class="form-group mb-3 col-xs-12 col-sm-6">
									<label for="stock">Units In Stock </label> <input name="product_stocks" id="stock"
										 type="number" class="form-control validate"
										required />
								</div>
								<div class="form-group mb-3 col-xs-12 col-sm-12">
									<label for="stock">Discount:</label> <input  id="stock"
										name="product_discount" type="number" class="form-control validate"
										required />
										
										</div>
								</div>
							</div>
							
					<div class="col-xl-6 col-lg-6 col-md-12 mx-auto mb-4">
						<div class="tm-product-img-dummy mx-auto"></div>

						<input name="product_photos" style="visibility: hidden;" id="fileupload" type="file" multiple="multiple" />
						<input type="button" class="btn btn-primary btn-block mx-auto"
							value="UPLOAD PRODUCT IMAGE"
							onclick="document.getElementById('fileupload').click();" />
							<hr>
							<div id="dvPreview"></div>
					</div>
					

					<div class="col-12">
						<button type="submit"
							class="btn btn-primary btn-block text-uppercase">Add
							Product Now</button>
					</div>
				</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script src="js/jquery-3.3.1.min.js"></script>
<!-- https://jquery.com/download/ -->
<script src="jquery-ui-datepicker/jquery-ui.min.js"></script>
<!-- https://jqueryui.com/download/ -->
<script src="js/bootstrap.min.js"></script>
<!-- https://getbootstrap.com/ -->
<script>
	$(function() {
		$("#expire_date").datepicker();
	});
</script>


<script src="https://code.jquery.com/jquery-1.12.4.min.js"
	integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
	crossorigin="anonymous"></script>

<script>
	$(function() {
		$("#fileupload")
				.change(
						function() {
							if (typeof (FileReader) != "undefined") {
								var dvPreview = $("#dvPreview");
								dvPreview.html("");
								var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp)$/;
								$($(this)[0].files)
										.each(
												function() {
													var file = $(this);
													if (regex.test(file[0].name
															.toLowerCase())) {
														var reader = new FileReader();
														reader.onload = function(
																e) {
															var img = $("<img />");
															img
																	.attr(
																			"style",
																			"height:100px;width: 100px");
															img
																	.attr(
																			"src",
																			e.target.result);
															dvPreview
																	.append(img);
														}
														reader
																.readAsDataURL(file[0]);
													} else {
														alert(file[0].name
																+ " is not a valid image file.");
														dvPreview.html("");
														return false;
													}
												});
							} else {
								alert("This browser does not support HTML5 FileReader.");
							}
						});
	});
</script>
</body>
</html>
