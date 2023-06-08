package com.kuebiko.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kuebiko.dto.SignupDTO;
import com.kuebiko.service.SignupService;

@Controller
public class SignupController {
	
	@Autowired
	private SignupService signupService;
	
  @GetMapping("/deleteData")
	public String deleteSignup(@RequestParam int sid) {
		//WRITE LOGIC
	  signupService.deleteBySid(sid);
		return "redirect:/showData";
	}

		
		@GetMapping("/showData")
		public String showSignups(Model model) {
			//WRITE LOGIC
			List<SignupDTO>  signupDTOs=signupService.findAll();
			model.addAttribute("bananas", signupDTOs);
			 return "signups";
		}
	
	@PostMapping("/signup")
	public String createSignup(@RequestParam String username, 
			@RequestParam String gender,@RequestParam String email,Model model) {
		    //below method will save data inside database
		   signupService.persist(username, email, gender);
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
		Optional<SignupDTO> optional=signupService.findByName(username);
		if(optional.isPresent()) {
			return "redirect:/showData";
		}else {
			pravat.addAttribute("message", "Hmmm I hate you!");
		}
		  return "login";
	}
	
}
