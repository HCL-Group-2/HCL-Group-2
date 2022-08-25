package com.hcl.ecommerce.controller;

import java.util.List;

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

import com.hcl.ecommerce.entity.CartItem;
import com.hcl.ecommerce.service.CartItemService;

@RestController
public class CartItemController {
	
	@Autowired
	CartItemService cartItemService;
	
	@PostMapping("/cartitem")
	public ResponseEntity<Void> addCartItem(@RequestBody CartItem cartItem) {
		boolean flag = cartItemService.addCartItem(cartItem);
		if (!flag) return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
//	@GetMapping("/cartitem/{userid}/{prodid}")
//	public ResponseEntity<CartItem> getCartItemByUserIdAndProductId(@PathVariable("userid") Integer userid, @PathVariable("prodid") Integer prodid) {
//		CartItem cartItem = cartItemService.getCartItemByUserIdAndProductId(userid, prodid);
//		return new ResponseEntity<CartItem>(cartItem, HttpStatus.OK);
//	}
	
	@GetMapping("/cartitem/{id}")
	public ResponseEntity<CartItem> getCartItemById(@PathVariable("id") Integer id) {
		CartItem cartItem = cartItemService.getCartItemById(id);
		return new ResponseEntity<CartItem>(cartItem, HttpStatus.OK);
	}
	
//	@PutMapping("/cartitem")
//	public ResponseEntity<CartItem> updateCartItem(@RequestBody CartItem cartItem) {
//		cartItemService.updateCartItem(cartItem);
//		return new ResponseEntity<CartItem>(cartItem, HttpStatus.OK);
//	}
	
	@PutMapping("/cartitem")
	public ResponseEntity<CartItem> updateOrderItem(@RequestBody CartItem cartItem) {
		cartItemService.updateCartItem(cartItem);
		return new ResponseEntity<CartItem>(cartItem, HttpStatus.OK);
	}
	
//	@DeleteMapping("/cartitem/{userid}/{prodid}")
//	public ResponseEntity<Void> deleteCartItem(@PathVariable("userid") Integer userid, @PathVariable("prodid") Integer prodid) {
//		cartItemService.deleteCartItem(userid, prodid);
//		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//	}
	
	@DeleteMapping("/cartitem/{id}")
	public ResponseEntity<Void> deleteCartItem(@PathVariable("id") Integer id) {
		cartItemService.deleteCartItem(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/cartitems")
	public ResponseEntity<List<CartItem>> getAllCartItems() {
		List<CartItem> list = cartItemService.getAllCartItems();
		return new ResponseEntity<List<CartItem>>(list, HttpStatus.OK);
	}

}
