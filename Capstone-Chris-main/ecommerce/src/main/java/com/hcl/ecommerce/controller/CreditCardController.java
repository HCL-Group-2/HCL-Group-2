//package com.hcl.ecommerce.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import com.hcl.ecommerce.entity.CreditCard;
//import com.hcl.ecommerce.service.CreditCardService;
//
//@RestController
//public class CreditCardController {
//	
//	@Autowired
//	CreditCardService creditCardService;
//	
//	@PostMapping("/creditcard")
//	public ResponseEntity<Void> addCreditCard(@RequestBody CreditCard creditCard, UriComponentsBuilder builder) {
//		boolean flag = creditCardService.addCreditCard(creditCard);
//		if (!flag) return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(builder.path("/creditcard/{id}").buildAndExpand(creditCard.getId()).toUri());
//		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
//	}
//	
//	@GetMapping("/creditcard/{id}")
//	public ResponseEntity<CreditCard> getCreditCardById(@PathVariable("id") Integer id) {
//		CreditCard creditCard = creditCardService.getCreditCardById(id);
//		return new ResponseEntity<CreditCard>(creditCard, HttpStatus.OK);
//	}
//	
//	@PutMapping("/creditcard")
//	public ResponseEntity<CreditCard> updateCreditCard(@RequestBody CreditCard creditCard) {
//		creditCardService.updateCreditCard(creditCard);
//		return new ResponseEntity<CreditCard>(creditCard, HttpStatus.OK);
//	}
//	
//	@DeleteMapping("/creditcard/{id}")
//	public ResponseEntity<Void> deleteCreditCard(@PathVariable("id") Integer id) {
//		creditCardService.deleteCreditCard(id);
//		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//	}
//	
//	@GetMapping("/creditcards/{userid}")
//	public ResponseEntity<List<CreditCard>> getAllCreditCardsByUserId(@PathVariable("userid") Integer userid) {
//		List<CreditCard> list = creditCardService.getAllCreditCardsByUserId(userid);
//		return new ResponseEntity<List<CreditCard>>(list, HttpStatus.OK);
//	}
//
//}
