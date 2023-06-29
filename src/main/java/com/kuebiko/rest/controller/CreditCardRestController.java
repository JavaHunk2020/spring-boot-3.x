package com.kuebiko.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kuebiko.controller.dto.CreditCardDTO;
import com.kuebiko.service.SignupService;

@RestController
@CrossOrigin("*") // CORS - Cross CrossOrigin Resource Sharing
@RequestMapping("/v1/creditcards")
public class CreditCardRestController {

	@Autowired
	private SignupService signupService;

	@PostMapping("/apply")
	//{"name":"Nisha","email":"nisha@gmail.com","type":"MASTER","cardName":"SIGNATURE","nameOnCard":"PRATEEK RAJWANSHI"}
	public AppResponse createCardDetails(@RequestBody CreditCardDTO creditCardDTO, Model model) {
		System.out.println(creditCardDTO);
		AppResponse appResponse=new AppResponse();
		appResponse.setCode("12");
		appResponse.setMessage("Credit card application submitted successfully.");
		return appResponse;
	}

}
