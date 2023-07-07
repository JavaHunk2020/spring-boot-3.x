package com.kuebiko.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kuebiko.dto.SignupDTO;

@RestController
@CrossOrigin("*") // CORS - Cross CrossOrigin Resource Sharing
@RequestMapping("/v1/creditcards")
public class MasterCreditCardRestController {
	
	
	@GetMapping("/available")
	public List<SignupDTO>findAvailableCards() {
	
	}



}
