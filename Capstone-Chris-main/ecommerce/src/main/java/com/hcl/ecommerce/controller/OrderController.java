package com.hcl.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.hcl.ecommerce.dto.OrderDto;
import com.hcl.ecommerce.entity.Order;
import com.hcl.ecommerce.service.OrderService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("/order")
	public ResponseEntity<Void> addOrder(@RequestBody OrderDto orderDto, UriComponentsBuilder builder) {
		boolean flag = orderService.addOrder(orderDto);
		if (!flag) return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/order/{id}").buildAndExpand(orderDto.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@GetMapping("/order/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable("id") Integer id) {
		Order order = orderService.getOrderById(id);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}
	
	@PutMapping("/order")
	public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
		orderService.updateOrder(order);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}
	
	@DeleteMapping("/order/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable("id") Integer id) {
		orderService.deleteOrder(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
