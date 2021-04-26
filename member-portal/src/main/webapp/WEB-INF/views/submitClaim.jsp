<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
	crossorigin="anonymous">
<title>submit claim</title>
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<style>
	footer{
		bottom: 0;
		position: fixed;
		height: 60px;
		width: 100%
	}
	form{
		margin-bottom: 50px;
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
						href="/login">Logout</a>
					
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-sm">
				<img
					src="https://thumbs.dreamstime.com/b/doctor-sympathetic-work-clothes-pointing-to-poster-51180694.jpg"
					style="height: 450px">
			</div>
				<div class="col-sm">
					<h2>Submit A Claim</h2>
					<form method="post" action="/submitClaim">
						<div class="form-group">
							<label for="exampleInputEmail1"><b>Policy Id</b></label> <select
								class="form-control" style="width: 40%" name="policyId"
								id="policyId">
								<c:forEach items="${policy}" var="item">
									<option value="${item.policyId}">${item.policyId}</option>
								</c:forEach>

							</select> <small id="emailHelp" class="form-text text-muted">Choose
								Policy ID</small>
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1"><b>Hospital</b></label> <select
								class="form-control" style="width: 40%" name="hospitalId"
								id="hospitalId">
								<option value="1">Newlife Hospital, Kolkata</option>
								<option value="2">Red Cross Hospital, Pune</option>
								<option value="3">Cambridge Hospital, Bangalore</option>
								<option value="4">Newlife Hospital, Chennai</option>
								<option value="5">Newlife Hospital, Mumbai</option>
							</select> <small id="emailHelp" class="form-text text-muted">Choose
								Hospital</small>
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1"><b>Amount</b></label> <input
								type="number" name="amount" id="amount" class="form-control"
								placeholder="Amount" style="width: 40%"> <small
								id="emailHelp" class="form-text text-muted">Enter claim
								amount</small>
						</div>
						<div class="form-group">
							<label for="benefits"><b>Benefits</b></label>
							<br> 
							<input
								type="radio" name="benefits" id="benefits"
								  value="Cashless Medical Treatment" style="margin-right: 10px;">Cashless Medical Treatment
							<br>
							<input
								type="radio" name="benefits" id="benefits" 
								  value="Coverage of Pre and Post-Hospitalization Expenses" style="margin-right: 10px;">Coverage of Pre and Post-Hospitalization Expenses
							<br>
								<input
								type="radio" name="benefits" id="benefits" 
								  value="Ambulance Fee" style="margin-right: 10px;">Ambulance Fee
							<br>
								<input
								type="radio" name="benefits" id="benefits" 
								  value="Room Rent Sub-limits in Your Health Insurance Plan" style="margin-right: 10px;">Room Rent Sub-limits in Your Health Insurance Plan
							<br>	
								<input
								type="radio" name="benefits" id="benefits" 
								  value="Co-Payment" style="margin-right: 10px;">Co-Payment
							<br>	
								 <small
								id="emailHelp" class="form-text text-muted" >Choose benefit to be availed
								</small>
						</div>
						<br>
						<button type="submit" class="btn btn-primary">Submit</button>
					</form>
				</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
		crossorigin="anonymous"></script>

</body>
<footer class="navbar    navbar-light bg-info">
	<p class="text-white">Copyright &#xa9; 2021</p>
</footer>
</body>

</html>