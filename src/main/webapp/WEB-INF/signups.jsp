<!DOCTYPE html>
<%@page import="com.kuebiko.dto.LoginHistoryDTO"%>
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
   	          <a href="loginHistory">
   	           <img style="height: 60px;" src="data:image/jpeg;base64,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">
   	           </a>
   	           Hello!! ${sessionScope.userLoggedIn.email}
   	               <a href="logout">
   	                <button type="button" class="btn btn-danger">Logout</button>
   	                </a>
   	   </span>
   	   
   	   <%
       	List<LoginHistoryDTO>  historyDTOs = (List<LoginHistoryDTO>) request.getAttribute("loginHistoryList");
   	    	 if (historyDTOs == null) {
   	    		historyDTOs = new ArrayList<>();
   	    	 }
   	    	if(historyDTOs.size()>0){ 
   	    %>	 
   	   
   	   <h3>Login History  :-</h3>
   	  	 <table class="table table-bordered">
    <thead>
      <tr style="background-color: #5d62f4;color:white;">
         <th>SNO</th>
        <th>Login Time</th>
        <th>Logout Time</th>
        <th>Duration</th>
         <th>Action</th>
      </tr>
    </thead>
    <tbody>
    
    <%
    int count=1;
for(LoginHistoryDTO loginHistoryDTO :historyDTOs ){
	%>
      <tr>
    	<td><%=count++ %></td>
    	<td><%=loginHistoryDTO.getLtime()%></td>
    			<td><%=loginHistoryDTO.getLogouttime()%></td>
    				<td>
    				<b>TODO</b>
    				</td>
    				<td>
    				<b>  <button type="button" class="btn btn-danger">Delete</button></b>
    				</td>
    			</tr>
    <%} %>			
    					
    </tbody>
    </table>
    <%
   	    	}
    %>
   	   
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