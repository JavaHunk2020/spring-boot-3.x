package com.kuebiko.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kuebiko.dto.SignupDTO;
import com.kuebiko.service.SignupService;

@RestController
@RequestMapping("/pb")
public class SignupPbRestController {
	
	
	@Autowired
	private SignupService signupService;
	
	
	@PatchMapping("/csignup")  //attributeName=name , attributeValue= santosh  , sid = 12
	public AppResponse partialSignup(@RequestParam String attributeName,String attributeValue,int sid) {
		    AppResponse appResponse=new AppResponse();
		    appResponse.setMessage("Signup is updated partially.");
		    appResponse.setCode("C293");
			return appResponse;
	}
	
	
	@PutMapping("/csignup")
	public AppResponse updateSignup(@RequestBody SignupDTO dto) {
		    //below method will save data inside database
		    signupService.persist(dto);
		    AppResponse appResponse=new AppResponse();
		    appResponse.setMessage("Signup is update successfully.");
		    appResponse.setCode("C293");
			return appResponse;
	}
	
	
	//	//http://localhost:9999/pb/signups/nagendra.yadav.niit@gmail.com
	@DeleteMapping("/csignup/{email}")
	public AppResponse deleteSignup(@PathVariable("email") String email) {
		    //below method will save data inside database
		    signupService.deleteByEmail(email);
		    AppResponse appResponse=new AppResponse();
		    appResponse.setMessage("Signup is deleted successfully.");
		    appResponse.setCode("Cw293");
			return appResponse;
	}
	
	
	@PostMapping("/csignup")
	public AppResponse createSignup(@RequestBody SignupDTO dto) {
		    //below method will save data inside database
		    signupService.persist(dto);
		    AppResponse appResponse=new AppResponse();
		    appResponse.setMessage("Signup is done successfully.");
		    appResponse.setCode("C293");
			return appResponse;
	}
	
	
	//http://localhost:9999/pb/signups/nagendra.yadav.niit@gmail.com
	@GetMapping("/signups/{email}")
	public SignupDTO signupByEmail(@PathVariable("email") String email) {
	  	 Optional<SignupDTO> optional = signupService.findByEmail(email);
		 return optional.get();
	}
	

	@GetMapping("/signups")
	public List<SignupDTO> showSignups(@RequestParam String role, @RequestParam String email) {
		// WRITE LOGIC
		List<SignupDTO> signupDTOs = new ArrayList<>();
		if (role.equalsIgnoreCase("admin")) {
			signupDTOs = signupService.findAllByRole("customer");
		} else {
			Optional<SignupDTO> optional = signupService.findByEmail(email);
			if (optional.isPresent()) {
				signupDTOs.add(optional.get());
			}
		}
		return signupDTOs;
	}

}
