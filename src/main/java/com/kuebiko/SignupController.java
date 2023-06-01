package com.kuebiko;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignupController {

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
	public String postLogin(@RequestParam String username, @RequestParam String password,Model model) {
		  if("jack".equalsIgnoreCase(username) && "jill".equals(password)) {
			  model.addAttribute("message","Ahahha username and password are correct!");
		  }else {
			  model.addAttribute("message","Sorry username and password are not correct!");
		  }
		  return "login";
	}
	
}
