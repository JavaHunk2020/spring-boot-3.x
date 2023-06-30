<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
   <script type="text/javascript">
         var  pname='nagen';
          let tt= {
        		  email:"nagen@gmail.com",
        		  show:function() {
        			  console.log("name = "+this.name);  
        			  console.log("email = "+this.email);
        		  }
          }
          tt.show();
   
   </script>
</head>
<body> 
  <header style="height: 30px;background-color: #344ecf;">
  </header>
  
  <div class="container">
       <hr/>
      <img src="https://www.bankofbaroda.in/-/media/project/bob/countrywebsites/india/blogs/images/new-credit-card-rules-effective-from-july-1-2022.jpg" class="img-thumbnail" style="height: 120px;">
      <img src="https://www.bankofbaroda.in/-/media/project/bob/countrywebsites/india/blogs/images/new-credit-card-rules-effective-from-july-1-2022.jpg" class="img-thumbnail" style="height: 120px;">
      <img src="https://www.bankofbaroda.in/-/media/project/bob/countrywebsites/india/blogs/images/new-credit-card-rules-effective-from-july-1-2022.jpg" class="img-thumbnail" style="height: 120px;">
      <hr/>
      <span style="color:red;font-size: 20px;font-weight: bold;">${appResponse.message}</span>
      <br>
  	  <hr/>
  	      <div style="width: 100%;">
  	         <img src="https://media.hswstatic.com/eyJidWNrZXQiOiJjb250ZW50Lmhzd3N0YXRpYy5jb20iLCJrZXkiOiJnaWZcL2RlYnQtbWFuYWdlbWVudC1jcmVkaXQtY2FyZC5qcGciLCJlZGl0cyI6eyJyZXNpemUiOnsid2lkdGgiOjgyOH19fQ==">
  	  </div>
  	  
  	  	
  </div>

</body>
</html>