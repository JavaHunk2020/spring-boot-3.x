package com.kuebiko.rest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kuebiko.dto.SignupDTO;
import com.kuebiko.service.SignupService;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*") // CORS - Cross CrossOrigin Resource Sharing
public class SignupRestController {
	
	@Autowired
	private SignupService signupService;
	
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
