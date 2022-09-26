package com.hcl.ecommerce.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.dto.CreatePaymentResponse;
import com.hcl.ecommerce.dto.PaymentIntentDto;
import com.hcl.ecommerce.entity.Order;
import com.hcl.ecommerce.exception.AddEntityException;
import com.hcl.ecommerce.service.OrderService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;



@CrossOrigin(origins="https://ostrichmart.azurewebsites.net/")
@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@Value("${stripe.api.key}") 
    private String stripeSecretKey;
	
	@PostMapping("/create-intent")
	public ResponseEntity<CreatePaymentResponse> createIntent(@RequestBody PaymentIntentDto intentDto) {
		Stripe.apiKey = stripeSecretKey;
		
		//Convert cost to cents
		BigDecimal tempTotal = new BigDecimal(intentDto.getOrderTotal());
		tempTotal = tempTotal.multiply(new BigDecimal(100));
		Long totalAmount = tempTotal.longValue();
		
		CreatePaymentResponse paymentResponse = null;
		try {
			PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
					.setCurrency("usd")
					.setAmount(totalAmount)
					.build();

			PaymentIntent intent = PaymentIntent.create(createParams);
			paymentResponse = new CreatePaymentResponse(intent.getClientSecret());

		} catch (StripeException se) {
			se.printStackTrace();//DEBUG
			return new ResponseEntity<CreatePaymentResponse>((CreatePaymentResponse)null, HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<CreatePaymentResponse>(paymentResponse, HttpStatus.OK);
	}
	
	@PostMapping("/order")
	public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
		try {
			order = orderService.addOrder(order);
		} catch (AddEntityException e) {
			return new ResponseEntity<Order>(order, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Order>(order, HttpStatus.CREATED);
	}
	
	@GetMapping("/order/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable("id") Integer id) {
		Order order = orderService.getOrderById(id);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}
	
	@PutMapping("/order")
	public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
		order = orderService.updateOrder(order);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}
	
	@DeleteMapping("/order/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable("id") Integer id) {
		orderService.deleteOrder(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
