<!DOCTYPE html>
<%@page import="com.kuebiko.dto.PassportDTO"%>
<%@page import="com.kuebiko.dto.SignupDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
   	<h1>Signup Page</h1>
   	 <img style="height: 60px;" src="https://s40424.pcdn.co/in/wp-content/uploads/2022/03/What-is-project-management-Meaning-and-Definition-of-Project-Management-1.jpg">
   	  <img style="height: 60px;" src="https://s40424.pcdn.co/in/wp-content/uploads/2022/03/What-is-project-management-Meaning-and-Definition-of-Project-Management-1.jpg">
   	   <img style="height: 60px;" src="https://s40424.pcdn.co/in/wp-content/uploads/2022/03/What-is-project-management-Meaning-and-Definition-of-Project-Management-1.jpg">
   	   <span style="float: right;font-weight: bold;">
   	           Hello!! ${sessionScope.userLoggedIn.email}
   	               <a href="logout">
   	                <button type="button" class="btn btn-danger">Logout</button>
   	                </a>
   	   </span>
   	 <hr/>
   	 
   	 <%
   	    	 List<SignupDTO> signupDTOs = (List<SignupDTO>) request.getAttribute("bananas");
   	    	 if (signupDTOs == null) {
   	    	 	signupDTOs = new ArrayList<>();
   	    	 }

   	    	 PassportDTO passportDTO = (PassportDTO) request.getAttribute("passportDetails");

   	    	 if (passportDTO != null) {
   	    	 %>
   	   <h3>Selected Passport Details  :-</h3>
   	  	 <table class="table table-bordered">
    <thead>
      <tr style="background-color: #35858b;color:white;">
         <th>Name</th>
        <th>Number</th>
        <th>Address</th>
        <th>Date of expiry</th>
        <th>Photo</th>
         <th>Delete</th>
      </tr>
    </thead>
    <tbody>
      <tr>
    	<td>${passportDetails.name}</td>
    	<td>${passportDetails.number}</td>
    			<td>${passportDetails.address}</td>
    				<td>
    				<b>${passportDetails.doe}</b>
    				</td>
    					  <td><img src="${passportDetails.photo}" style="height: 40px;"></td>
    					     <td>
    					     <button type="button" class="btn btn-danger">Delete</button>
    					     <button type="button" class="btn btn-primary">EDIT</button>
    					     </td>
    			</tr>		
    </tbody>
    </table>
    <%} %>
    
		

		<hr/>
   	  <h3 style="background-color: #ffffe2;">Signup Data with Passport!</h3>
   	  <hr/>
   	   <b>Total Records</b> :  <span style="font-weight: bold;font-size: 16px;"> <%=signupDTOs.size()%></span>
   	 <table class="table table-bordered">
    <thead>
      <tr style="background-color: maroon;color:white;">
         <th>Sno</th>
        <th>Name</th>
        <th>Email</th>
        <th>Gender</th>
        <th>Photo</th>
          <th>Action</th>
      </tr>
    </thead>
    <tbody>
	
<%
for(SignupDTO signupDTO :signupDTOs ){
	%>
      <tr>
      <td><%=signupDTO.getSid() %></td>
        <td><%=signupDTO.getName() %>
        </td>
       <td><%=signupDTO.getEmail() %></td>
      <td><%=signupDTO.getGender() %></td>
      <td><img src="<%=signupDTO.getPhoto() %>" style="height: 80px;"></td>
        <td>
          <a href="deleteData?sid=<%=signupDTO.getSid()%>">
              <button type="button" class="btn btn-danger">DELETE</button>
          </a>
          &nbsp;
         <% 
          if(signupDTO.getPassportFlag().equalsIgnoreCase("no")){ %>
          <a href="addPassport?sid=<%=signupDTO.getSid()%>&name=<%=signupDTO.getName() %>">
              <button type="button" class="btn btn-primary">Passport</button>
          </a>
          <%}else { %>
           &nbsp;
          <a href="passportDetails?sid=<%=signupDTO.getSid()%>">
              <button type="button" class="btn btn-success">More!</button>
          </a>
        <%  } %>
          </td>
      </tr>
   
   <%
   }
%>   
      
      <tr style="background-color: #e9ffe9;">
      <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
         <td></td>
      </tr>
    </tbody>
  </table>
   </section>
   
   <br/><br/>
 
</body>
</html>