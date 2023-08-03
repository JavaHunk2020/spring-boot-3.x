<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
  
  <script type="text/javascript">
      function uploadFile(formName){
    	  //Submiting form using javascipt
    	  document.forms[formName].submit();
      }
  
  </script>
  
</head>
<body>
   <header style="height: 30px;background-color:yellow; ">
   </header>
   
   <section class="container">
   	<h1>Home! Page</h1>
   	 <img style="height: 60px;" src="https://s40424.pcdn.co/in/wp-content/uploads/2022/03/What-is-project-management-Meaning-and-Definition-of-Project-Management-1.jpg">
   	   <span style="float: right;font-weight: bold;">
   	   
   	   
   	      <a href="${pageContext.request.contextPath}/action/gallery"> 
   	         <button type="button" class="btn btn-warning">Gallery</button>
   	    </a>
   	     &nbsp;&nbsp;&nbsp;&nbsp;
   	       <a href="${pageContext.request.contextPath}/action/editProfile"> 
   	         <button type="button" class="btn btn-warning">Profile</button>
   	    </a>
   	   
   	    <a href="${pageContext.request.contextPath}/action/addNewCard"> 
   	      <button type="button" class="btn btn-success">New Credit Card</button>
   	    </a>
   	   
   	    <a href="showCards"> 
   	   <button type="button" class="btn btn-primary">Manage Credit Card</button>
   	    </a>
   	          <a href="loginHistory">
   	           <img style="height: 60px;" src="data:image/jpeg;base64,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">
   	           </a>
   	          
   	           	
   	           	
   	           Hello!! ${sessionScope.userLoggedIn.email}
   	               <a href="logout">
   	                <button type="button" class="btn btn-danger">Logout</button>
   	                </a>
   	                
   	                 
   	                 <img style="height: 60px;" src="${pageContext.request.contextPath}/action/uimage"/>
   	                 
   	                
   	   </span>
   	   <hr/>
   	   <br/><br/>
   	  	 <table class="table table-bordered">
    <thead>
    
    <c:forEach items="${mapped}" var="item">
      <tr>
      
      <c:forEach items="${item.value}" var="citem">
          <td>
          <img style="height: 180px;margin-bottom: 3px;" src="gimage?iid=${citem.id}" alt="No image is present">
                Filename : <b>${citem.name }  </b>  &nbsp; &nbsp;  Filesize : <b>${citem.size }  </b>
                <form name="galleryForm${citem.id}"  action="gallery" method="post" enctype="multipart/form-data">
                       <input type="hidden"  name="gid" value="${citem.id}">    
                      <input onchange="uploadFile('galleryForm${citem.id}');" type="file" name="photo" class="form-control mr-2" style="width: 95%"/>
               </form>
          </td>
          </c:forEach>
      </tr>
          </c:forEach>
         
         
           <tr>
          <td>
          Add New Photo : 
          <img style="height: 180px;margin-bottom: 3px;" src="data:image/jpeg;base64,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" alt="No image is present">
                <form name="newGalleryForm"  action="gallery" method="post" enctype="multipart/form-data">
                     <input type="hidden"  name="gid" value="0">    
                      <input onchange="uploadFile('newGalleryForm');" type="file" name="photo" class="form-control mr-2" style="width: 95%"/>
               </form>
          </td>
      </tr>
         
    </thead>
    <tbody>
</tbody>
</table>
   
   <br/><br/>
   </section>
 
</body>
</html>