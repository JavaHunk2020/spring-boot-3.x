package com.kuebiko.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kuebiko.dto.SignupDTO;
import com.kuebiko.service.DatabaseServiceLogic;

@Controller
public class SignupController {
	
	@Autowired
	private DatabaseServiceLogic  databaseServiceLogic;
	
		
		@GetMapping("/showData")
		public String showSignups(Model model) {
			
			//WRITE LOGIC
			List<SignupDTO>  signupDTOs=databaseServiceLogic.findAll();
			model.addAttribute("bananas", signupDTOs);
			 return "signups";
		}
	
	@PostMapping("/signup")
	public String createSignup(@RequestParam String username, 
			@RequestParam String gender,@RequestParam String email,Model model) {
		    //below method will save data inside database
		    databaseServiceLogic.save(username, email, gender);
			model.addAttribute("message","Ahaha DOne!!");
			return "signup";
	}
	
	
	@GetMapping({"/signup","/","/cool","/fool"})
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
