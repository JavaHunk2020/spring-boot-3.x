package com.kuebiko.rest.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
	public void findCustomerCreditCard(@RequestParam String name,@RequestParam String email,String applicationId,HttpServletResponse response) throws IOException {
		CreditCardDTO card=cardApplicationService.generatedCreditCard(name,email);
		card.setEmail(email);
		card.setApplicationId(applicationId);
	   cardApplicationService.saveCardDetails(card);
	   response.setContentType("image/png");
	   ServletOutputStream outputStream=response.getOutputStream();
	   if(card!=null) {
		   outputStream.write(card.getPhoto());
	   }else {
		   outputStream.write(new byte[] {});
	   }
	   outputStream.flush();
	   outputStream.close();
	}
	
	
	@GetMapping("/photo")
	public void loadImage(@RequestParam String applicationId,HttpServletResponse response) throws IOException {
		//Fetch photo
  	   byte[] photo = cardApplicationService.findCreditCardDetails(applicationId);
	   response.setContentType("image/png");
	   ServletOutputStream outputStream=response.getOutputStream();
	   if(photo!=null) {
		   outputStream.write(photo);
	   }else {
		   outputStream.write(new byte[] {});
	   }
	   outputStream.flush();
	   outputStream.close();
	}
	
	
	@GetMapping("/all/{email}")
	public List<ImageResponse> allCardImage(@PathVariable String email) {
		//Fetch photo
	   List<ImageResponse> list = cardApplicationService.findCreditCardsByEmail(email);
  	   return list;
	}
	
	@GetMapping("/cphoto")
	public ImageResponse cloadImage(@RequestParam String applicationId) {
		//Fetch photo
  	   byte[] photo = cardApplicationService.findCreditCardDetails(applicationId);
  	   ImageResponse imageResponse=new ImageResponse();
  	   imageResponse.setPhoto(photo);
  	   imageResponse.setCode(212);
  	   return imageResponse;
	}
	
	
	@PatchMapping("/status")
	public AppResponse findCreditcardDetails(@RequestBody PatchDTO patchDTO) {
		cardApplicationService.changeStatus(patchDTO);
		AppResponse appResponse=new AppResponse();
		appResponse.setCode("12");
		appResponse.setMessage("Credit card application status is updated successfully.");
		return appResponse;
	}
	
	@GetMapping("/details/{applicationId}")
	public CreditCardDTO findCreditcardDetails(@PathVariable String applicationId, Model model) {
		CreditCardDTO creditCardDTO = cardApplicationService.findByApplicationId(applicationId);
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
