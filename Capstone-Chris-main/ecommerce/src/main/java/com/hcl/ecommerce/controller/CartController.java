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

import com.hcl.ecommerce.entity.Cart;
import com.hcl.ecommerce.service.CartService;

@RestController
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@PostMapping("/cart")
	public ResponseEntity<Void> addCart(@RequestBody Cart cart) {
		boolean flag = cartService.addCart(cart);
		if (!flag) return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@GetMapping("/cart/{id}")
	public ResponseEntity<Cart> getCartById(@PathVariable("id") Integer id) {
		Cart cart = cartService.getCartById(id);
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}
	
	@PutMapping("/cart")
	public ResponseEntity<Cart> updateCart(@RequestBody Cart cart) {
		cartService.updateCart(cart);
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
	}
	
	@DeleteMapping("/cart/{id}")
	public ResponseEntity<Void> deleteCart(@PathVariable("id") Integer id) {
		cartService.deleteCart(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
