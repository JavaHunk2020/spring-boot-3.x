<!DOCTYPE html>
<html>
<head>
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
   <header style="height: 30px;background-color:yellow; ">
   </header>
   
   <section class="container">
   	<h1>Passport Page</h1>
   	 <img style="height: 160px;" src="https://cdn-icons-png.flaticon.com/512/235/235820.png">
   	  <img style="height: 160px;" src="https://cdn-icons-png.flaticon.com/512/235/235820.png">
   	   <img style="height: 160px;" src="https://cdn-icons-png.flaticon.com/512/235/235820.png">
   	 <br/><br/>
   	 
   	 
   	 <h1 style="color:green;">${message}</h1>
   	 <form action="addPassport" method="post">
   	 
   	 <input type="hidden" name="sid" value="${param.sid}">
   	 
   	  
   	 <div style="width: 50%;margin-bottom: 15px;" class="form-group">
   		 <label>Name : </label>
   		 <input type="text" style="background-color: #fffff4;" readonly="readonly" name="name" class="form-control" value="${param.name}">
   	 </div>
   	 
   	 <div style="width: 50%;margin-bottom: 15px;" class="form-group">
   		 <label>Number : </label>
   		 <input type="text" name="number" class="form-control">
   	 </div>
   	 
   	  <div style="width: 50%;margin-bottom: 15px;" class="form-group">
   		 <label>Photo : </label>
   		 <input type="text" name="photo" class="form-control">
   	 </div>
   	 
   	 
   	 <div style="width: 50%;margin-bottom: 15px;" class="form-group">
   	 <label>Address : </label>
   	 <input type="text" name="address" class="form-control">
   	 </div>
   	 
   	 <div style="width: 40%;margin-bottom: 15px;" class="form-group">
   	 <label>Date of Expiry (dd-MM-yyyy): </label>
   	 <input type="text" name="exp" class="form-control">
   	 </div>
   	 
   	 	 <div style="width: 50%;margin-top: 20px;">
   	 <button type="submit" class="btn btn-primary">Add</button>
   	  <button type="reset" class="btn btn-danger">Clear</button>
   	 </div>
   	 </form>
   	 <hr/>
   	</section>
</body>
</html>