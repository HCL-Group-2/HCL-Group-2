package com.hcl.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.hcl.ecommerce.entity.CartItem;
import com.hcl.ecommerce.service.CartItemService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class CartItemController {
	
	@Autowired
	CartItemService cartItemService;
	
	@PostMapping("/cartitem")
	public ResponseEntity<Void> addCartItem(@RequestBody CartItem cartItem, UriComponentsBuilder builder) {
		boolean flag = cartItemService.addCartItem(cartItem);
		if (!flag) return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/cartitem/{id}").buildAndExpand(cartItem.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@GetMapping("/cartitem/{id}")
	public ResponseEntity<CartItem> getCartItemById(@PathVariable("id") Integer id) {
		CartItem cartItem = cartItemService.getCartItemById(id);
		return new ResponseEntity<CartItem>(cartItem, HttpStatus.OK);
	}
	
	@PutMapping("/cartitem")
	public ResponseEntity<CartItem> updateCartItem(@RequestBody CartItem cartItem) {
		cartItemService.updateCartItem(cartItem);
		return new ResponseEntity<CartItem>(cartItem, HttpStatus.OK);
	}
	
	@DeleteMapping("/cartitem/{id}")
	public ResponseEntity<Void> deleteCartItem(@PathVariable("id") Integer id) {
		cartItemService.deleteCartItem(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/cartitems/{userid}")
	public ResponseEntity<List<CartItem>> getAllCartItemsByUserId(@PathVariable("userid") Integer userid) {
		List<CartItem> list = cartItemService.getAllCartItemsByUserId(userid);
		return new ResponseEntity<List<CartItem>>(list, HttpStatus.OK);
	}

}
