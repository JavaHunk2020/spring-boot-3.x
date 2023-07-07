package com.kuebiko.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CardController {
	
	
	@GetMapping("/showCards")
	public String showCards() {
		
		
		return "showCards";
	}

}
