package com.hcl.ecommerce.controller;

import java.util.List;

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

import com.hcl.ecommerce.entity.CartItem;
import com.hcl.ecommerce.exception.AddEntityException;
import com.hcl.ecommerce.service.CartItemService;

@CrossOrigin(origins="https://ostrichmart.azurewebsites.net")

@RestController
public class CartItemController {
	
	@Autowired
	CartItemService cartItemService;
	
	@PostMapping("/cartitem")
	public ResponseEntity<CartItem> addCartItem(@RequestBody CartItem cartItem) {
		try {
			cartItem = cartItemService.addCartItem(cartItem);
		} catch (AddEntityException e) {
			return new ResponseEntity<CartItem>(cartItem, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<CartItem>(cartItem, HttpStatus.CREATED);
	}
	
	@GetMapping("/cartitem/{id}")
	public ResponseEntity<CartItem> getCartItemById(@PathVariable("id") Integer id) {
		CartItem cartItem = cartItemService.getCartItemById(id);
		return new ResponseEntity<CartItem>(cartItem, HttpStatus.OK);
	}
	
	@PutMapping("/cartitem")
	public ResponseEntity<CartItem> updateCartItem(@RequestBody CartItem cartItem) {
		cartItem = cartItemService.updateCartItem(cartItem);
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
