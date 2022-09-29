package com.hcl.ecommerce.controller;

import java.util.ArrayList;
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

import com.hcl.ecommerce.dto.CartItemDto;
import com.hcl.ecommerce.entity.CartItem;
import com.hcl.ecommerce.exception.AddEntityException;
import com.hcl.ecommerce.service.CartItemService;


@CrossOrigin(origins="https://ostrichmart.azurewebsites.net")


@RestController
public class CartItemController {
	
	@Autowired
	CartItemService cartItemService;
	
	@PostMapping("/cartitem")
	public ResponseEntity<CartItemDto> addCartItem(@RequestBody CartItemDto cartItemDto) {
		CartItem cartItem = null;
		try {
			cartItem = cartItemService.addCartItem(new CartItem(cartItemDto));
		} catch (AddEntityException e) {
			return new ResponseEntity<CartItemDto>((CartItemDto)null, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(cartItem.toDto() , HttpStatus.CREATED);
	}
	
	@GetMapping("/cartitem/{id}")
	public ResponseEntity<CartItemDto> getCartItemById(@PathVariable("id") Integer id) {
		CartItem cartItem = cartItemService.getCartItemById(id);
		return new ResponseEntity<>(cartItem.toDto(), HttpStatus.OK);
	}
	
	@PutMapping("/cartitem")
	public ResponseEntity<CartItemDto> updateCartItem(@RequestBody CartItemDto cartItemDto) {
		CartItem cartItem = cartItemService.updateCartItem(new CartItem(cartItemDto));
		return new ResponseEntity<>(cartItem.toDto(), HttpStatus.OK);
	}
	
	@DeleteMapping("/cartitem/{id}")
	public ResponseEntity<Void> deleteCartItem(@PathVariable("id") Integer id) {
		cartItemService.deleteCartItem(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/cartitems/{userid}")
	public ResponseEntity<List<CartItemDto>> getAllCartItemsByUserId(@PathVariable("userid") Integer userid) {
		List<CartItem> tempList = cartItemService.getAllCartItemsByUserId(userid);
		List<CartItemDto> list = new ArrayList<CartItemDto>();
		for(CartItem a : tempList) {
			list.add(a.toDto());
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
