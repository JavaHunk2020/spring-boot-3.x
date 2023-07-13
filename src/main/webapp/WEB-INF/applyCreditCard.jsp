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
   <hr/><hr/>
   <section class="container">
   	<h4>Apply for Credit Card!!</h4>
   	 <img style="height: 160px;" src="https://d1nhio0ox7pgb.cloudfront.net/_img/g_collection_png/standard/512x512/credit_cards.png">
   	  <img style="height: 160px;" src="https://d1nhio0ox7pgb.cloudfront.net/_img/g_collection_png/standard/512x512/credit_cards.png">
   	   <img style="height: 160px;" src="https://d1nhio0ox7pgb.cloudfront.net/_img/g_collection_png/standard/512x512/credit_cards.png">
   	 <br/><br/>
   	 
   	 
   	 <h1 style="color:tamato;">${message}</h1>
   	 <form action="applyForCreditCard" method="post">
   	 
   	 <input type="hidden" name="sid" value="${param.sid}">
   	 
   	  
   	 <div style="width: 50%;margin-bottom: 15px;" class="form-group">
   		 <label>Name : </label>
   		 <input type="text" style="background-color: #fffff4;" readonly="readonly" name="name" class="form-control" value="${signupDTO.name}">
   	 </div>
   	 
   	  <div style="width: 50%;margin-bottom: 15px;" class="form-group">
   		 <label>Email : </label>
   		 <input type="text" style="background-color: #fffff4;" readonly="readonly" name="email" class="form-control" value="${signupDTO.email}">
   	 </div>
   	 
   	 <div style="width: 50%;margin-bottom: 15px;" class="form-group">
   		 <label>Type : </label>
   		 <label><b>${param.type}</b> </label>
   		  	 <input type="hidden" name="type" value="${param.sid}">
   	 </div>
   	 
   	   <div style="width: 50%;margin-bottom: 15px;" class="form-group">
   		 <label>Card Name : </label>
   		  <label><b>${param.name}</b> </label>
   		  	 <input type="hidden" name="cardName" value="${param.name}">
   		 
   	 </div>
   	 
   	 
   	 <div style="width: 50%;margin-bottom: 15px;" class="form-group">
   	 <label>Name on Card : </label>
   	 <input type="text" name="nameOnCard" class="form-control">
   	 </div>
   	 
   	 <div style="width: 40%;margin-bottom: 15px;" class="form-group">
   	 <label>Limit: </label>
   	<select name="climit" class="form-control">
   		     <option>1000$</option>
   		     <option>2000$</option>
   		     <option>3000$</option>
   		     <option>4000$</option>
   		     <option>5000$</option>
   		 </select>
   	 </div>
   	 
   	 	 <div style="width: 50%;margin-top: 20px;">
   	 <button type="submit" class="btn btn-primary">Apply For Card</button>
   	  <button type="reset" class="btn btn-danger">Clear</button>
   	 </div>
   	 </form>
   	 <hr/>
   	</section>
</body>
</html>