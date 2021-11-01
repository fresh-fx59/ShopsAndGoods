<html xmlns:th="http://www.thymeleaf.org">
<head>
    <link href="/webjars/bootstrap/4.5.2/css/bootstrap.min.css"
            rel="stylesheet">
    <script src="/webjars/jquery/1.9.1/jquery.min.js"></script>
    <title>Conrol Panel</title>
</head>
<body style="width: 800px;margin: auto;">
    <div class="container" style="background: #f9e8ff;border-color: rgb(247,104,104);">
        <h1 class="display-4 text-center" style="margin: 0;padding: 0;text-align: center;">Shops And Products</h1>
    </div>
    <div>
        <div class="container">
            <div class="row">
                <div class="col-md-6" style="background: #ffe8e8;border-width: 1px;border-color: rgb(247,104,104);">
                    <form action="/shops/" method="post">
                        <div class="form-group" style="text-align: center;padding: 10px;">
                            <p>Add Shop</p>
                            <input name="name" type="text" class="form-control" placeholder="Shop Name" />

<button value="Submit" class="btn btn-primary" type="submit" style="margin: 20px;padding: auto;">ADD</button></div>


                    </form>
                </div>
                <div class="col-md-6" style="background: #fffbe8;border-width: 1px;border-color: rgb(247,104,104);">
                <form  action="/products/" method="post">
                    <div class="form-group" style="text-align: center;padding: 10px;">
                        <p>Add Product</p>
                        <input name="name" type="text" placeholder="Product Name" style="margin-right: 10px;width: 150px;" />
                        <input name="price" type="number" placeholder="Price" style="width: 100px;" />
                        <button class="btn btn-primary" type="submit" style="margin: 20px;padding: auto;">ADD</button></div>
                        </form>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6" style="background: #ebffe8;border-width: 1px;border-color: rgb(247,104,104);">
                    <form action="/addproducttoshop/" method="post">
                        <div class="form-group" style="text-align: center;">
                            <p>Add Product To Shop</p>

                            <select name="productName" class="form-control" style="height: 30px;margin: 10px;" >
                                <option th:each = "product : ${products}" th:attr = "value=${product.name}" th:text = "${product.name}">Product</option>
                            </select>

                            <select name="shopName" class="form-control" style="height: 30px;margin: 10px;" >
                                <option th:each = "shop : ${shops}" th:attr = "value=${shop.name}" th:text = "${shop.name}">Shop</option>
                            </select>


                            <button
                                class="btn btn-primary text-uppercase" type="submit" style="margin: 20px;padding: auto;">Add Product To Shop</button>
                        </div>
                    </form>
                </div>
                <div class="col-md-6 d-xl-flex justify-content-xl-center align-items-xl-center" style="background: #e8f8ff;border-width: 1px;border-color: rgb(247,104,104);">
                    <form id="show-statistics">
                        <div class="form-group d-xl-flex justify-content-xl-center align-items-xl-center" style="height: 128px;">
                        <button class="btn btn-primary text-uppercase" type="submit">Show Product Stats</button></div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div></div>

    <script>
    	jQuery(document).ready(function($) {

    		$("#show-statistics").submit(function(event) {


    			// Prevent the form from submitting via the browser.
    			event.preventDefault();

    			showStatistics();

    		});

    	});

    	function showStatistics() {

    		var search = {}
    		search["username"] = $("#username").val();
    		search["email"] = $("#email").val();

    		$.ajax({
    			type : "GET",
    			contentType : "application/json",
    			url : "/showstatistics/",
    			timeout : 100000,
    			success : function(data) {
    				console.log("SUCCESS: ", data);
    				display(data);
    			},
    			error : function(e) {
    				console.log("ERROR: ", e);
    				display(e);
    			},
    			done : function(e) {
    				console.log("DONE");
    				enableSearchButton(true);
    			}
    		});

    	}

    	function display(data, status) {
    		alert(data);
    	}
    </script>
    <script src="/webjars/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>