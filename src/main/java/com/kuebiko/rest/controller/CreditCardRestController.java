package com.kuebiko.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kuebiko.controller.dto.CreditCardDTO;
import com.kuebiko.dto.PatchDTO;
import com.kuebiko.service.CreditCardApplicationService;
import com.kuebiko.service.SignupService;
import com.kuebiko.utils.CreditCardStatus;

@RestController
@CrossOrigin("*") // CORS - Cross CrossOrigin Resource Sharing
@RequestMapping("/v1/creditcards")
public class CreditCardRestController {

	@Autowired
	private SignupService signupService;
	
	@Autowired
	private CreditCardApplicationService cardApplicationService;
	
	@PatchMapping("/status")
	public AppResponse findCreditcardDetails(@RequestBody PatchDTO patchDTO) {
		cardApplicationService.changeStatus(patchDTO);
		AppResponse appResponse=new AppResponse();
		appResponse.setCode("12");
		appResponse.setMessage("Credit card application status is updated successfully.");
		return appResponse;
	}
	
	@GetMapping("/details/{email}")
	public CreditCardDTO findCreditcardDetails(@PathVariable String email, Model model) {
		CreditCardDTO creditCardDTO = cardApplicationService.findByEmailId(email);
		return creditCardDTO;
	}

	@PostMapping("/apply")
	//{"name":"Nisha","email":"nisha@gmail.com","type":"MASTER","cardName":"SIGNATURE","nameOnCard":"PRATEEK RAJWANSHI"}
	public AppResponse createCardDetails(@RequestBody CreditCardDTO creditCardDTO, Model model) {
		System.out.println(creditCardDTO);
		creditCardDTO.setStatus(CreditCardStatus.PENDING.getValue());
		creditCardDTO.setApplicationId("ATO09192");
		AppResponse appResponse=new AppResponse();
		appResponse.setCode("12");
		appResponse.setMessage("Credit card application submitted successfully.");
		return appResponse;
	}

}
