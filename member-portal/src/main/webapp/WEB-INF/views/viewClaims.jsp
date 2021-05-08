<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Claims</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
<style>
.container {
	margin-top: 20px;
	
}
footer {
	height: 60px;
	bottom: 0;
	background-color: #989898;
	width: 100%;
	
}

footer p{
	margin-top: 20px;
	margin-left: 20px;
}

#background{
	background-image: url("https://www.outsourcedataworks.com/assets/img/blog/banner/tips-to-speed-up-medical-claims-payments-inner.jpg");
	height: 250px;
	background-size: cover;
	background-position: center;
}
#background input {
margin-top: 200px;
margin-left: 220px;
padding: 5px;
border-radius: 2px;
border: 0; 
}

</style>
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
				<li class="nav-item"><a class="nav-link" href="/viewAllClaims"
					style="color: white;">View Claims</a></li>
			</ul>
		</div>
	</nav>
	
	<div id="background">
				
	</div>
	<div class="container">
		
		<h2>Claims</h2>
		
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Sr. No</th>
					<th>Claim ID</th>
					<th>Policy ID</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${claims}" var="item" varStatus="loop">
					<tr>
						<td>${loop.index + 1 }</td>
						<td>${item.claimNumber }</td>
						<td>${item.policyNumber }</td>
						<td><a class="btn btn-warning" href="/getClaimStatus/${item.policyNumber}/${item.claimNumber}">View Status</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	
	<footer>
		<div>
			<p>Copyright 2021</p>
		</div>
	</footer>
</body>
</html>