<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>submit claim status</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
<style>
.container {
	width: 35%;
	margin: auto;
	margin-top: 100px;
}
</style>
</head>
<body
	background="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTiiKyLO_lYetuDllMo4eG1_5k8ALvFtu_-dV4D2D8ORHeFmr9_fNRk3mE2tbxD1Up2lCY&usqp=CAU">

	<nav
		class="px-5 py-3 mb-5 mb-5  navbar navbar-expand-md navbar-light bg-info">
		<div class="container-fluid">
			<a class="navbar-brand h1 text-white" href="home">
				<h2>Health Insurance</h2>
			</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navBarCollapse">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse justify-content-end"
				id="navBarCollapse">
				<ul class="navbar-nav ">

					<li class="nav-item"><a class="nav-link text-white"
						href="/logout">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<h2>Claim Submission Status</h2>
		<table class="table table table-striped table-bordered table-hover">
			<tr class="table-info">
				<td><b>Claim Number</b></td>
				<td>${result.claimNumber }</td>
			</tr>
			<tr>
				<td><b>Policy Number</b></td>
				<td>${result.policyNumber }</td>
			</tr>
			<tr class="table-info">
				<td><b>Hospital Details</b></td>
				<td>${result.hospitalDetails }</td>
			</tr>
			<tr>
				<td><b>Claim Status</b></td>
				<td>${result.status }</td>
			</tr>
		</table>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
		crossorigin="anonymous"></script>
	<footer class="navbar  fixed-bottom  navbar-light bg-info">
		<p class="text-white">Copyright &#xa9; 2021</p>
	</footer>
</body>
</html>