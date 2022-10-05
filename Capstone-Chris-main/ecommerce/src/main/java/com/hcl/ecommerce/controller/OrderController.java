package com.hcl.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.hcl.ecommerce.dto.OrderDto;
import com.hcl.ecommerce.dto.PaymentInfoDTO;
import com.hcl.ecommerce.entity.Order;
import com.hcl.ecommerce.exception.AddEntityException;
import com.hcl.ecommerce.service.OrderService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;


@CrossOrigin(origins="https://ostrichmart.azurewebsites.net")
@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	
	@PostMapping("/payment-intent")
	public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfoDTO paymentInfo) throws StripeException {
	        PaymentIntent paymentIntent = orderService.createPaymentIntent(paymentInfo);
	        String paymentStr = paymentIntent.toJson();
	        return new ResponseEntity<>(paymentStr, HttpStatus.OK);
	}
	
	@PostMapping("/order")
	public ResponseEntity<OrderDto> placeOrder(@RequestBody OrderDto orderDto) {
		Order order = null;
		try {
			order = orderService.addOrder(new Order(orderDto));
		} catch (AddEntityException e) {
			return new ResponseEntity<>((OrderDto) null, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(order.toDto(), HttpStatus.CREATED);
	}
	
	@GetMapping("/order/{id}")
	public ResponseEntity<OrderDto> getOrderById(@PathVariable("id") Integer id) {
		Order order = orderService.getOrderById(id);
		return new ResponseEntity<>(order.toDto(), HttpStatus.OK);
	}
	
	@PutMapping("/order")
	public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto orderDto) {
		Order order = orderService.updateOrder(new Order(orderDto));
		return new ResponseEntity<>(order.toDto(), HttpStatus.OK);
	}
	
	@DeleteMapping("/order/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable("id") Integer id) {
		orderService.deleteOrder(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
