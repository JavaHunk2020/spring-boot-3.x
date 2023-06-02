package com.kuebiko;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignupController {
	
	@Value("${db.url}")
	private String url;
	
	@Value("${db.username}")
	private String dbusername;
	
	@Value("${db.password}")
	private String password;
	
	@Value("${db.driver}")
	private String driver;
	
	
	@GetMapping("/deleteData")
	public String deleteData(@RequestParam int sid) {
				 try {
					   //Loading the DRIVER
					    //Class is class
						Class.forName(driver);
						//Making connect to the database
						Connection conn=DriverManager.
								getConnection(url,
										dbusername,password);
						if(conn!=null) {
							System.out.println("I am connected with database");
						}
						//Creating the query
						String sql="delete  from  asignup_tbl where sid = ?";
						//Creating prepared statement with query
						PreparedStatement pstmt=conn.prepareStatement(sql);
						pstmt.setInt(1, sid);
						pstmt.executeUpdate();
						
				 }catch (Exception e) {
					 //It will print the problem in details
					 e.printStackTrace();
				}
				 return "redirect:/showData";
		}

	@GetMapping("/showData")
	public String showSignups(Model model) {
		
		 List<SignupDTO> signupDTOs=new ArrayList<SignupDTO>();  		 
		 //JDBC PROGRAMMING
		 try {
			 Class.forName(driver);
				//Making connect to the database
				Connection conn=DriverManager.
						getConnection(url,
								dbusername,password);
				if(conn!=null) {
					System.out.println("I am connected with database");
				}
				//Creating the query
				String sql="select sid,name,email,gender from  asignup_tbl";
				//Creating prepared statement with query
				PreparedStatement pstmt=conn.prepareStatement(sql);
				
				//fire the query
				ResultSet rs=pstmt.executeQuery();
				while(rs.next()) {
					int sid=rs.getInt("sid");
					String name=rs.getString("name");
					String email=rs.getString("email");
					String gender=rs.getString("gender");
					//1 row data goes inside one signup dto
					SignupDTO signupDTO=new SignupDTO(sid, name, email, gender);
					signupDTOs.add(signupDTO);
				}
			 
		 }catch (Exception e) {
			 //It will print the problem in details
			 e.printStackTrace();
		}
		 
		 model.addAttribute("bananas", signupDTOs);
		 return "signup";
	}
	
	
	@PostMapping("/signup")
	public String createSignup(@RequestParam String username, 
			@RequestParam String gender,@RequestParam String email,Model model) {
		 //  /WEB-INF/login.jsp
		
		 //JDBC PROGRAMMING
		 try {
			   //Loading the DRIVER
			    //Class is class
			 Class.forName(driver);
				//Making connect to the database
				Connection conn=DriverManager.
						getConnection(url,
								dbusername,password);
				if(conn!=null) {
					System.out.println("I am connected with database");
				}
				//Creating the query
				String sql="insert into asignup_tbl (name,email,gender) values(?,?,?)";
				
				//Creating prepared statement with query
				PreparedStatement pstmt=conn.prepareStatement(sql);
				//Now Setting value inside place holders
				pstmt.setString(1,username);
				pstmt.setString(2,email);
				pstmt.setString(3,gender);
				//fire the query
				pstmt.executeUpdate();
		 }catch (Exception e) {
			 //It will print the problem in details
			 e.printStackTrace();
		}
		
		  model.addAttribute("message","Ahaha DOne!!");
		  return "signup";
	}
	
	
	@GetMapping({"/signup","/"})
	public String showSignup() {
		 //  /WEB-INF/login.jsp
		  return "signup";
	}
	

	@GetMapping("/slogin")
	public String showLogin() {
		 //  /WEB-INF/login.jsp
		  return "login";
	}
	
	/**
	 *   <input type="text" name="username" class="form-control">
  	            <label>Password</label>
  	            <input type="password" name="password" class="form-control">
	 * @param username
	 * @param password
	 * @param model
	 * @return
	 */
	@PostMapping("/auth")
	public String postLogin(@RequestParam String username, @RequestParam String password,Model pravat) {
		  if("jack".equalsIgnoreCase(username) && "jill".equals(password)) {
			  pravat.addAttribute("message","Ahahha username and password are correct!");
		  }else {
			  pravat.addAttribute("message","Sorry username and password are not correct!");
		  }
		  return "login";
	}
	
}
