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
          
          var options = {
                  series: [{
                  name: 'Marine Sprite',
                  data: [44, 55, 41, 37, 22, 43, 21]
                }, {
                  name: 'Striking Calf',
                  data: [53, 32, 33, 52, 13, 43, 32]
                }, {
                  name: 'Tank Picture',
                  data: [12, 17, 11, 9, 15, 11, 20]
                }, {
                  name: 'Bucket Slope',
                  data: [9, 7, 5, 8, 6, 9, 4]
                }, {
                  name: 'Reborn Kid',
                  data: [25, 12, 19, 32, 25, 24, 10]
                }],
                  chart: {
                  type: 'bar',
                  height: 350,
                  stacked: true,
                },
                plotOptions: {
                  bar: {
                    horizontal: true,
                    dataLabels: {
                      total: {
                        enabled: true,
                        offsetX: 0,
                        style: {
                          fontSize: '13px',
                          fontWeight: 900
                        }
                      }
                    }
                  },
                },
                stroke: {
                  width: 1,
                  colors: ['#fff']
                },
                title: {
                  text: 'Fiction Books Sales'
                },
                xaxis: {
                  categories: [2008, 2009, 2010, 2011, 2012, 2013, 2014],
                  labels: {
                    formatter: function (val) {
                      return val + "K"
                    }
                  }
                },
                yaxis: {
                  title: {
                    text: undefined
                  },
                },
                tooltip: {
                  y: {
                    formatter: function (val) {
                      return val + "K"
                    }
                  }
                },
                fill: {
                  opacity: 1
                },
                legend: {
                  position: 'top',
                  horizontalAlign: 'left',
                  offsetX: 40
                }
                };

                var chart = new ApexCharts(document.querySelector("#chart"), options);
                chart.render();
   
   </script>
</head>
<body> 
  <header style="height: 30px;background-color: #344ecf;">
  </header>
  
  <div class="container">
      <img src="https://www.iconpacks.net/icons/1/free-user-login-icon-305-thumb.png" class="img-thumbnail" style="height: 220px;">
      <img src="https://www.iconpacks.net/icons/1/free-user-login-icon-305-thumb.png" class="img-thumbnail" style="height: 220px;">
      <img src="https://www.iconpacks.net/icons/1/free-user-login-icon-305-thumb.png" class="img-thumbnail" style="height: 220px;">
      <hr/>
      
      <div id="chart">
      
      </div>
      
      <span style="color:red;font-size: 20px;font-weight: bold;border: 1px solid red">${message}</span>
      <br>
  	  <hr/>
  	  <form action="auth" method="post">
  	      <div style="width: 50%;">
  	            
  	            <label>Username/Email</label>
  	            <input type="text" name="username" class="form-control">
  	            <label>Password</label>
  	            <input type="password" name="password" class="form-control">
  	            <hr/> 
  	            <button type="submit" class="btn btn-primary">Login</button>
  	            
  	            <button type="reset" class="btn btn-danger">Reset</button>
  	            
  	             <a href="signup">
  	           		  <button type="button" class="btn btn-warning">Signup</button>
  	             </a>
  	             
  	              
  	             <a href="changePassword">
  	           		  <button type="button" class="btn btn-primary">Reset Password</button>
  	             </a>
  	  </div>
  	  </form>
  	  
  	  	
  </div>

</body>
</html>