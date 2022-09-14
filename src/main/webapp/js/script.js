function addToCart(pId, pName, pPrice) {

             let cart = localStorage.getItem("cart");
              if(cart == null) {
              let products = [];
              let product = {productId:pId, productName:pName, productPrice:pPrice, productQuantity:1}
              products.push(product);
              localStorage.setItem("cart", JSON.stringify(products));
              console.log("Product is added for the first time");
              showToast("Item is added to the cart");
             }
              else {
              
               let pcart=JSON.parse(cart);
               
               let oldProduct = pcart.find((item) => item.productId == pId)
                
               if(oldProduct) {
               
               oldProduct.productQuantity = oldProduct.productQuantity+1;
               pcart.map((item) => {
               
               if(item.productId==oldProduct.productId) {
               item.productQuantity=oldProduct.productQuantity;
               }
               })
               
               localStorage.setItem("cart", JSON.stringify(pcart));
               console.log("Product quantity is increased");
               showToast("Item quantity is increased");
               
                  
               }
               else {
                let product = {productId:pId, productName:pName, productPrice:pPrice, productQuantity:1}
                pcart.push(product);
                localStorage.setItem("cart", JSON.stringify(pcart));
                console.log("Another Product is added");
                showToast("Item is added");
              }
              
       }
       
       
       updateCart();            
}

function updateCart() {

          let cartString = localStorage.getItem("cart");
          let cart=JSON.parse(cartString);
          if(cart==null || cart.length==0) {
          console.log("Cart is empty!!");
          $(".cart-items").html("0");
          $(".cart-body").html(`<h1  class="text-center">Sorry, Cart doesn't have any items </h1>`);
          $(".checkout-btn").attr('disabled',true);
          }
          else {
          cart.map((item) => {
         console.log(cart);
          $(".cart-items").html(`${cart.length}`);
          let table=`
          <table class='table'>
          <thead class='thead-light'>
            <tr>
            <th>Item Name</th>
            <th>Item Quantity</th>
            <th>Item Price</th>
            <th>Total Price</th>
            <th>Action</th>

              </tr>
          </thead> `;
         
                let totalPrice = 0;
               cart.map((item)=> {
                 
               table+=`
                   <tr>
                   
                   <td>${item.productName}
                   <input type='hidden' name='product_id' value='${item.productId}'>
                   </td>
                   <td><i class='fa fa-circle-plus text-success'></i> ${item.productQuantity} <i onclick='decQuantity()' class='fa fa-circle-minus text-danger'></i>
                   <input type='hidden' name='product_quantities' value='${item.productQuantity}'>
                   </td>
                   <td>${item.productPrice}</td>
                   <td>${item.productQuantity * item.productPrice}</td>
                   <td><button onclick='deleteItemFromCart(${item.productId})' class='btn btn-danger btn-sm'>Remove</button></td>
                   </tr>`
                   
                   totalPrice+=item.productPrice*item.productQuantity;

  

                   })





             table = table + `<tr><td class='colspan='5' class='text-center font-weight-bold'>Total Price: ${totalPrice}
             <input type='hidden' name='total_price' value='${totalPrice}'>
             </td></tr></table>`
             $(".cart-body").html(table);
             $(".checkout-btn").attr('disabled',false);

                  })
         }
}


function deleteItemFromCart(pId) {

 let cart = JSON.parse(localStorage.getItem("cart"));
 let newCart = cart.filter((item)=>item.productId!=pId)
 localStorage.setItem("cart", JSON.stringify(newCart));
 updateCart();
 showToast("Item is removed from the cart");
}

function deleteAllProductsFromCart() {

       let cart = JSON.parse(localStorage.getItem("cart"));
       let newCart = cart.filter;
       localStorage.setIem("cart", JSON.stringify(newCart));
       updateCart();
       
}

function incQuantity(pId, pQuantity) {

let pcart = JSON.parse(localStorage.getItem("cart"));

let oldProduct = pcart.find((item) => item.productId == pId)
                
               if(oldProduct) {
               
               oldProduct.productQuantity = oldProduct.productQuantity+1;
               pcart.map((item) => {
               
               if(item.productId==oldProduct.productId) {
               item.productQuantity=oldProduct.productQuantity;
               }
               })
               
               localStorage.setItem("cart", JSON.stringify(pcart));
               console.log("Product quantity is increased");
               showToast("Item quantity is increased");
               
                  
               }
}

$(document).ready(function () {

            updateCart();

})

function showToast(content) {

     $("#snackbar").addClass("display");
     $("#snackbar").html(content);

  // After 3 seconds, remove the show class from DIV
  setTimeout(() => {
            $("#snackbar").removeClass("display");
            }, 3000);
}

function checkOut() {

window.location="checkout.jsp";

}




