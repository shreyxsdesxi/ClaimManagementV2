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
<title>Login</title>
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
    <style>
 
div.transbox {
  width: 400px;
  padding: 10px;
  border: 1px solid black;
  margin:5%;
  opacity: 0.6;
     background-image: url("https://c4.wallpaperflare.com/wallpaper/41/737/145/digital-art-render-blue-black-background-wallpaper-preview.jpg");


}
#form{
            padding-left: 65%;

        }
        div.transbox1{
           
            width: 1350px;
           margin-top:15%;
           border: 6px solid black;
           
         
            background-color: white;
            height:200px;
           
           
        }
       
       
    </style>
  </head>


<body  background="https://ejournalz.com/wp-content/uploads/2018/11/Health-Insurance-Plan.jpg" width="1350px" >
    <nav class="px-5 py-3 mb-5 mb-5  navbar navbar-expand-md navbar-light bg-info">
        <div class="container-fluid">
           <a class="navbar-brand h1 text-white" href="home">
             <h2> Health Insurance</h2>
           </a>
           <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
               data-bs-target="#navBarCollapse">
               <span class="navbar-toggler-icon"></span>
           </button>
           
           <div  class="collapse navbar-collapse justify-content-end" id="navBarCollapse">
           <ul  class="navbar-nav ">
           </li></ul>
           </div>
           </div>
       </nav>
   
   
    <div id ="form" >
  <div class="transbox rounded-3">
  <div id="login">
        <h3 class="text-center text-white pt-5">Login form</h3>
        <div class="container">
            <div id="login-row" class="row justify-content-center align-items-center">
                <div id="login-column" class="col-md-6">
                    <div id="login-box" class="col-md-12">
                        <form id="login-form" class="form" action="" method="post">
                            <h3 class="text-center text-primary">Login</h3>
                            <div class="form-group">
                                <label for="username" class="text-white">Username:</label><br>
                                <input type="text" name="username" id="username" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-white">Password:</label><br>
                                <input type="password" name="password" id="password" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="remember-me" class="text-white"><span>${error}</span> </label><br>
                                <input type="submit" name="submit" class="btn btn-primary btn-md" value="submit">
                            </div>
                            <div id="register-link" class="text-right">
                                <a href="#" class="text-white">Register here</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
      </div>
        </div></div>
     <div class="transbox1 text-dark text-center fw-bold">
         Benefits of Health Insurance<br>

         <span class="fw-normal">Health insurance is an agreement under which an insurance company promises to reimburse the medical expenses incurred by the policyholder. The insurance coverage generally includes costs incurred for hospitalisation, medicines, specialist/doctor/surgeon as well as cost incurred during pre-hospitalisation and post-hospitalisation. A policyholder can extend the coverage of a plan with the help of health insurance add-ons like hospital daily cash cover, maternity cover, room rent waiver, etc. by paying an additional premium amount.  Besides providing you with the insurance coverage for medical expenses, a health insurance plan provides peace of mind by providing financial stability in times of emergencies. It ensures that you get timely and required medical treatment. It also helps you avail tax benefits under Section 80D of the Income Tax Act, 1961. To ensure hassle-free services and smooth claim settlement facilities, insurance companies have tie-ups with many hospitals. These hospitals are known as network hospitals. In the absence of any tie-up, i.e. if the treatment is availed at a non-network hospital, then the insurer reimburses the medical expenses to the policyholder.</span>
    </div>
   

      <footer  class="navbar    navbar-light bg-info">
        <p class="text-white">Copyright &#xa9; 2021</p>
    </footer>  
</body>

</html>