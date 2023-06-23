package com.kuebiko.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

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
		public String showSignups(Model model,HttpSession session) {
			//WRITE LOGIC
			SignupDTO signupDTO=(SignupDTO)session.getAttribute("userLoggedIn");
			if(signupDTO==null) {
				 model.addAttribute("message","It seems like session is expired!");
				 return "login";
			}else {
				List<SignupDTO>  signupDTOs=signupService.findAll();
				model.addAttribute("bananas", signupDTOs);
				 return "signups";
			}
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
	
	@GetMapping("/logout")
	public String logout(HttpSession session,Model model) {
		  //Fetching signup dto from session
		  SignupDTO signupDTO=(SignupDTO)session.getAttribute("userLoggedIn");
		  
		   if(signupDTO!=null && signupDTO.getHid()!=0) {
			   //Fetching history id for that login
			   signupService.updateLogoutTime(signupDTO.getHid());   
		   }
		     session.invalidate();
			 model.addAttribute("message","Ahahah! you have been successfully! logout.");
		     return "login";
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
	public String postLogin(@RequestParam String username, @RequestParam String password,Model pravat,HttpSession session) {
		Optional<SignupDTO> optional=signupService.findByName(username);
		if(optional.isPresent()) {
			//Hey user is there
			//Create session object and add user details
			session.setMaxInactiveInterval(120);
			SignupDTO signupDTO =optional .get();
			session.setAttribute("userLoggedIn",signupDTO);
			int hid=signupService.saveLoginHistory(signupDTO.getSid());
			signupDTO.setHid(hid);
			return "redirect:/showData";
		}else {
			pravat.addAttribute("message", "Hmmm I hate you!");
		}
		  return "login";
	}
	
}
