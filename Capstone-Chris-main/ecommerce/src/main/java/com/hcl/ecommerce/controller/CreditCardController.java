package com.hcl.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.entity.CreditCard;
import com.hcl.ecommerce.exception.AddEntityException;
import com.hcl.ecommerce.service.CreditCardService;

@RestController
public class CreditCardController {
	
	@Autowired
	CreditCardService creditCardService;
	
	@PostMapping("/creditcard")
	public ResponseEntity<CreditCard> addCreditCard(@RequestBody CreditCard creditCard) {
		try {
			creditCard = creditCardService.addCreditCard(creditCard);
		} catch (AddEntityException e) {
			return new ResponseEntity<CreditCard>(creditCard, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<CreditCard>(creditCard, HttpStatus.CREATED);
	}
	
	@GetMapping("/creditcard/{id}")
	public ResponseEntity<CreditCard> getCreditCardById(@PathVariable("id") Integer id) {
		CreditCard creditCard = creditCardService.getCreditCardById(id);
		return new ResponseEntity<CreditCard>(creditCard, HttpStatus.OK);
	}
	
	@PutMapping("/creditcard")
	public ResponseEntity<CreditCard> updateCreditCard(@RequestBody CreditCard creditCard) {
		creditCard = creditCardService.updateCreditCard(creditCard);
		return new ResponseEntity<CreditCard>(creditCard, HttpStatus.OK);
	}
	
	@DeleteMapping("/creditcard/{id}")
	public ResponseEntity<String> deleteCreditCard(@PathVariable("id") Integer id) {
		String result = "";
		try {
			creditCardService.deleteCreditCard(id);
			result = "Success";
		} catch (Exception e) {
			result = "Failed";
		}
		return new ResponseEntity<String>(result, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/creditcards/{userid}")
	public ResponseEntity<List<CreditCard>> getAllCreditCardsByUserId(@PathVariable("userid") Integer userid) {
		List<CreditCard> list = creditCardService.getAllCreditCardsByUserId(userid);
		return new ResponseEntity<List<CreditCard>>(list, HttpStatus.OK);
	}

}
