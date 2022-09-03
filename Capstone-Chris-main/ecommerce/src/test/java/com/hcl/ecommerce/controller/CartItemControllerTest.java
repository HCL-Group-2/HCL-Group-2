package com.hcl.ecommerce.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.hcl.ecommerce.entity.CartItem;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.service.CartItemService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartItemControllerTest {
	
	@InjectMocks
	CartItemController cartItemController;
	
	@Mock
	CartItemService cartItemService;
	
	@Test
	public void testAddCartItem() throws Exception {
		
		User user = new User();
		user.setId(1);
		user.setFirstName("Test");
		user.setLastName("User");
		user.setEmail("testuser@gmail.com");
		user.setPassword("test");
		
		Product product = new Product();
		product.setId(1);
		product.setName("Test Product");
		product.setDescription("A test product.");
		product.setPrice(50.0);
		product.setImage("Test Image");
		product.setCategory("Test Category");
		product.setInventory(300);
		
		CartItem mockCartItem = new CartItem();
		mockCartItem.setId(1);
		mockCartItem.setQuantity(1);
		mockCartItem.setSubtotal(50.0);
		mockCartItem.setUser(user);
		mockCartItem.setProduct(product);
		
		Mockito.when(cartItemService.addCartItem(any(CartItem.class))).thenReturn(mockCartItem);
		
		ResponseEntity<CartItem> response = cartItemController.addCartItem(mockCartItem);
		
		CartItem cartItem = response.getBody();
		
		assertEquals(HttpStatus.CREATED.value(), response.getStatusCodeValue());
		
		assertEquals(1, cartItem.getQuantity());
		assertEquals(50.0, cartItem.getSubtotal(), 0.001);
		
	}
	
	@Test
	public void testGetCartItemById() throws Exception {
		
		User user = new User();
		user.setId(1);
		user.setFirstName("Test");
		user.setLastName("User");
		user.setEmail("testuser@gmail.com");
		user.setPassword("test");
		
		Product product = new Product();
		product.setId(1);
		product.setName("Test Product");
		product.setDescription("A test product.");
		product.setPrice(50.0);
		product.setImage("Test Image");
		product.setCategory("Test Category");
		product.setInventory(300);
		
		CartItem mockCartItem = new CartItem();
		mockCartItem.setQuantity(1);
		mockCartItem.setSubtotal(50.0);
		mockCartItem.setUser(user);
		mockCartItem.setProduct(product);
		
		Mockito.when(cartItemService.getCartItemById(1)).thenReturn(mockCartItem);
		
		ResponseEntity<CartItem> response = cartItemController.getCartItemById(1);
		
		CartItem cartItem = response.getBody();
		
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		
		assertEquals(1, cartItem.getQuantity());
		assertEquals(50.0, cartItem.getSubtotal(), 0.001);
		
	}
	
	@Test
	public void testUpdateCartItem() throws Exception {
		
		User user = new User();
		user.setId(1);
		user.setFirstName("Test");
		user.setLastName("User");
		user.setEmail("testuser@gmail.com");
		user.setPassword("test");
		
		Product product = new Product();
		product.setId(1);
		product.setName("Test Product");
		product.setDescription("A test product.");
		product.setPrice(50.0);
		product.setImage("Test Image");
		product.setCategory("Test Category");
		product.setInventory(300);
		
		CartItem mockCartItem = new CartItem();
		mockCartItem.setQuantity(1);
		mockCartItem.setSubtotal(50.0);
		mockCartItem.setUser(user);
		mockCartItem.setProduct(product);
		
		Mockito.when(cartItemService.updateCartItem(any(CartItem.class))).thenReturn(mockCartItem);
		
		ResponseEntity<CartItem> response = cartItemController.updateCartItem(mockCartItem);
		
		CartItem cartItem = response.getBody();
		
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		
		assertEquals(1, cartItem.getQuantity());
		assertEquals(50.0, cartItem.getSubtotal(), 0.001);
		
	}
	
	@Test
	public void testDeleteCartItem() throws Exception {
		
		User user = new User();
		user.setId(1);
		user.setFirstName("Test");
		user.setLastName("User");
		user.setEmail("testuser@gmail.com");
		user.setPassword("test");
		
		Product product = new Product();
		product.setId(1);
		product.setName("Test Product");
		product.setDescription("A test product.");
		product.setPrice(50.0);
		product.setImage("Test Image");
		product.setCategory("Test Category");
		product.setInventory(300);
		
		CartItem mockCartItem = new CartItem();
		mockCartItem.setQuantity(1);
		mockCartItem.setSubtotal(50.0);
		mockCartItem.setUser(user);
		mockCartItem.setProduct(product);
		
		Mockito.when(cartItemService.deleteCartItem(1)).thenReturn("Success");
		
		ResponseEntity<String> response = cartItemController.deleteCartItem(1);
		
		String str = response.getBody();
		
		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatusCodeValue());
		
		assertEquals("Success", str);
		
	}

}
