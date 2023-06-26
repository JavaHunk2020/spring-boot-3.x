package com.kuebiko.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kuebiko.dto.PassportDTO;
import com.kuebiko.service.PassportService;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*") // CORS - Cross CrossOrigin Resource Sharing
public class PassportRestController {

	@Autowired
	private PassportService passportService;

	@GetMapping("/signups/{sid}/passport")
	public PassportDTO getPassport(@PathVariable("sid") int sid) {
		// WRITE LOGIC
		PassportDTO passportDTO = passportService.findBySignupId(sid);
		return passportDTO;
	}

}
