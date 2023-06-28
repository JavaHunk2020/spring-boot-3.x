<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Change Password</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body> 
  <header style="height: 30px;background-color: #344ecf;">
  </header>
  
  <div class="container">
     <img src="https://cdn-icons-png.flaticon.com/512/6195/6195699.png" class="img-thumbnail" style="height: 220px;">
      <img src="https://cdn-icons-png.flaticon.com/512/6195/6195699.png" class="img-thumbnail" style="height: 220px;">
      <img src="https://cdn-icons-png.flaticon.com/512/6195/6195699.png" class="img-thumbnail" style="height: 220px;">
      <hr/>
      <span style="color:red;font-size: 20px;font-weight: bold;">${message}</span>
      <br>
       <h4>Change Password Page!</h4>
  	  <hr/>
  	  <form action="changePassword" method="post">
  	      <div style="width: 50%;">
  	            
  	            <label>Email</label>
  	            <input type="email" name="email" class="form-control" value="${param.email}">
  	             <br/>
  	            <label>New Password</label>
  	            <input type="password" name="newPassword" class="form-control">
  	             <label>Confirm Password</label>
  	            <input type="password" name="confirmPassword" class="form-control">
  	            <br/>
  	            <button type="submit" class="btn btn-success">Reset Password</button>
  	            
  	            <button type="reset" class="btn btn-danger">Reset</button>
  	            
  	             <a href="slogin">
  	           		  <button type="button" class="btn btn-warning">Login</button>
  	             </a>
  	             
  	  </div>
  	  </form>
  	  
  	  	
  </div>

</body>
</html>