package com.hcl.ecommerce.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.hcl.ecommerce.entity.CartItem;
import com.hcl.ecommerce.service.CartItemService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CartItemControllerTest {
	
	@InjectMocks
	CartItemController cartItemController;
	
	@Mock
	CartItemService cartItemService;
	
	@BeforeEach
	public void setupMocks() {
		
	}
	
	@Test
	public void testAddCartItem() throws Exception {
		
		CartItem cartItem = new CartItem();
		cartItem.setQuantity(0);
		cartItem.setSubtotal(0);
		
		Mockito.when(cartItemService.addCartItem(cartItem)).thenReturn(cartItem);
		
		ResponseEntity<CartItem> item = cartItemController.addCartItem(cartItem);
		
		assertEquals(HttpStatus.CREATED.value(), item.getStatusCodeValue());
		
		assertEquals(cartItem, item.getBody());
		
	}
	
	@Test
	public void testGetCartItemById() throws Exception {
		
		CartItem cartItem = new CartItem();
		cartItem.setId(1);
		cartItem.setQuantity(1);
		cartItem.setSubtotal(50.0);
		
		Mockito.when(cartItemService.getCartItemById(1)).thenReturn(cartItem);
		
		ResponseEntity<CartItem> item = cartItemController.getCartItemById(1);
		
		assertEquals(HttpStatus.OK.value(), item.getStatusCodeValue());
		
		assertEquals(cartItem, item.getBody());
		
	}
	
	@Test
	public void testUpdateCartItem() throws Exception {
		
		CartItem cartItem = new CartItem();
		cartItem.setId(1);
		cartItem.setQuantity(1);
		cartItem.setSubtotal(50.0);
		
		Mockito.when(cartItemService.updateCartItem(cartItem)).thenReturn(cartItem);
		
		ResponseEntity<CartItem> item = cartItemController.updateCartItem(cartItem);
		
		assertEquals(HttpStatus.OK.value(), item.getStatusCodeValue());
		
		assertEquals(cartItem, item.getBody());
		
	}
	
	@Test
	public void testDeleteCartItem() throws Exception {
		
		CartItem cartItem = new CartItem();
		cartItem.setId(1);
		cartItem.setQuantity(1);
		cartItem.setSubtotal(50.0);
		
		cartItemController.deleteCartItem(1);
		
		Mockito.verify(cartItemService, times(1)).deleteCartItem(1);
		
	}

}
