package com.kuebiko.rest.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	
	
	@GetMapping("/generate")
	public void findCustomerCreditCard(@RequestParam String name,@RequestParam String email,HttpServletResponse response) throws IOException {
	   byte[] card=cardApplicationService.generatedCreditCard(name,email); 
	   response.setContentType("image/png");
	   ServletOutputStream outputStream=response.getOutputStream();
	   if(card!=null) {
		   outputStream.write(card);
	   }else {
		   outputStream.write(new byte[] {});
	   }
	   outputStream.flush();
	   outputStream.close();
	}
	
	
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
		cardApplicationService.save(creditCardDTO);
		AppResponse appResponse=new AppResponse();
		appResponse.setCode("12");
		appResponse.setMessage("Credit card application submitted successfully.");
		return appResponse;
	}

}
