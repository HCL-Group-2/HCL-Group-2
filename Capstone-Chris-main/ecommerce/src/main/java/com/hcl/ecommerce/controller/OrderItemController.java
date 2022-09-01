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

import com.hcl.ecommerce.entity.OrderItem;
import com.hcl.ecommerce.service.OrderItemService;

@RestController
public class OrderItemController {
	
	@Autowired
	OrderItemService orderItemService;
	
	@PostMapping("/orderitem")
	public ResponseEntity<Void> addOrderItem(@RequestBody OrderItem orderItem) {
		boolean flag = orderItemService.addOrderItem(orderItem);
		if (!flag) return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@GetMapping("/orderitem/{id}")
	public ResponseEntity<OrderItem> getOrderItemById(@PathVariable("id") Integer id) {
		OrderItem orderItem = orderItemService.getOrderItemById(id);
		return new ResponseEntity<OrderItem>(orderItem, HttpStatus.OK);
	}
	
	@PutMapping("/orderitem")
	public ResponseEntity<OrderItem> updateOrderItem(@RequestBody OrderItem orderItem) {
		orderItemService.updateOrderItem(orderItem);
		return new ResponseEntity<OrderItem>(orderItem, HttpStatus.OK);
	}
	
	@DeleteMapping("/orderitem/{id}")
	public ResponseEntity<Void> deleteOrderItem(@PathVariable("id") Integer id) {
		orderItemService.deleteOrderItem(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
