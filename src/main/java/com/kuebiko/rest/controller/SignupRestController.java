package com.kuebiko.rest.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kuebiko.dto.SignupDTO;
import com.kuebiko.service.SignupService;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*") // CORS - Cross CrossOrigin Resource Sharing
public class SignupRestController {
	
	@Autowired
	private SignupService signupService;
	
	
	
	@GetMapping("/signups")
	public List<SignupDTO> showSignups() {
		//WRITE LOGIC
			List<SignupDTO>  signupDTOs=signupService.findAll();
			return signupDTOs;
	}
	
	
	@PostMapping("/csignup")
	public AppResponse createSignup(@RequestParam String username, 
			@RequestParam String gender,@RequestParam String email) {
		    //below method will save data inside database
		   signupService.persist(username, email, gender);
			AppResponse appResponse=new AppResponse();
			appResponse.setCode("success");
			appResponse.setMessage("Signup is done successfully.");
			return appResponse;
	}
	
	
	@PostMapping("/cauth")
	public AppResponse  postLogin(@RequestBody SignupRequest signupRequest) {
		AppResponse appResponse=new AppResponse();
		Optional<SignupDTO> optional=signupService.findByName(signupRequest.getUsername());
		if(optional.isPresent()) {
			//Hey user is there
			//Create session object and add user details
			appResponse.setCode("success");
			appResponse.setMessage("Username and password are correct");
		}else {
			appResponse.setCode("fail");
			appResponse.setMessage("Hmmm I hate you!");
		}
		  return appResponse;
	}

}
