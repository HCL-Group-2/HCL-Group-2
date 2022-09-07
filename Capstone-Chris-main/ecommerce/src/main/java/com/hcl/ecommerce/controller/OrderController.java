package com.hcl.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.entity.Order;
import com.hcl.ecommerce.exception.AddEntityException;
import com.hcl.ecommerce.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("/order")
	public ResponseEntity<Order> addOrder(@RequestBody Order order) {
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
	
	@DeleteMapping("/order/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable("id") Integer id) {
		orderService.deleteOrder(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
