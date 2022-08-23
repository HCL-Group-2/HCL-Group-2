package com.hcl.ecommerce.controller;

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
import com.hcl.ecommerce.service.CreditCardService;

@RestController
public class CreditCardController {
	
	@Autowired
	CreditCardService creditCardService;
	
	@PostMapping("/creditcard")
	public ResponseEntity<Void> addCreditCard(@RequestBody CreditCard creditCard) {
		boolean flag = creditCardService.addCreditCard(creditCard);
		if (!flag) return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@GetMapping("/creditcard/{id}")
	public ResponseEntity<CreditCard> getCreditCardById(@PathVariable("id") Integer id) {
		CreditCard creditCard = creditCardService.getCreditCardById(id);
		return new ResponseEntity<CreditCard>(creditCard, HttpStatus.OK);
	}
	
	@PutMapping("/creditcard")
	public ResponseEntity<CreditCard> updateCreditCard(@RequestBody CreditCard creditCard) {
		creditCardService.updateCreditCard(creditCard);
		return new ResponseEntity<CreditCard>(creditCard, HttpStatus.OK);
	}
	
	@DeleteMapping("/creditcard/{id}")
	public ResponseEntity<Void> deleteCreditCard(@PathVariable("id") Integer id) {
		creditCardService.deleteCreditCard(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
