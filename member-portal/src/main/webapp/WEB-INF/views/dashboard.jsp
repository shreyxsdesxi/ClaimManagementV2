<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
footer {
	height: 80px;
	bottom: 0;
	position: fixed;
	background-color: #989898;
	width: 100%;
}

footer p{
	margin-top: 20px;
	margin-left: 20px;
}
.container{
	margin-top: 100px;
}


 

</style>
</head>
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light bg-primary">
		<a class="navbar-brand" href="#"
			style="color: white; margin-left: 20px">Generic Insurance Website</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<!--  <li class="nav-item active"><a class="nav-link" href="#" style="color: white;">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="#" style="color: white;"></a></li>
				<li class="nav-item"><a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true" style="color: white;">Disabled</a></li>-->
				<li class="nav-item"><a class="nav-link" href="/logout"
					style="color: white;">Logout</a></li>
				
			</ul>
		</div>
	</nav>

	<div class="container">
		<div class="row">
			<div class="col">
				<div class="col mb-3">
					
					
						<div class="card">
							<img class="card-img-top" src="https://khn.org/wp-content/uploads/sites/2/2017/02/health-care-premium.jpg?w=770" alt="Card image cap" height=220px>
							<div class="card-body">
								<h5 class="card-title">View Bills</h5>
								<p class="card-text">Some quick example text to build on the
									card title and make up the bulk of the card's content.</p>
								<a href="/viewPremium" class="btn btn-primary">View Bills</a>
							</div>
						</div>
					
				</div>
			</div>

			<div class="col">
				<div class="col mb-3">
					<div class="card">
							<img class="card-img-top" src="https://www.internationalstudentinsurance.com/blog/wp-content/uploads/2017/08/Insurance-Claims_EN.png" alt="Card image cap" height=220px>
							<div class="card-body">
								<h5 class="card-title">View Claim Status</h5>
								<p class="card-text">Some quick example text to build on the
									card title and make up the bulk of the card's content.</p>
								<a href="/viewAllClaims" class="btn btn-primary">View Claim Status</a>
							</div>
						</div>
				</div>
			</div>

			<div class="col">
				<div class="col mb-3">
					<div class="card">
							<img class="card-img-top" src="https://di5qxxmgu0ea2.cloudfront.net/images/made/images/remote/http_s3-eu-west-1.amazonaws.com/claimsure-sb-uploads/general/Service_Page_Images/1ab7c705bf9395a3ff2bae97da9c91d5/claimsure2_516_381_90.jpg" alt="Card image cap" height=220px>
							<div class="card-body">
								<h5 class="card-title">Submit Claim</h5>
								<p class="card-text">Some quick example text to build on the
									card title and make up the bulk of the card's content.</p>
								<a href="/submitClaim" class="btn btn-primary">Submit Claim</a>
							</div>
						</div>
				</div>
			</div>

		</div>
	</div>

	<footer>
		<div>
			<p>Copyright 2021</p>
		</div>
	</footer>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
		crossorigin="anonymous"></script>
</body>
</html>