package com.hcl.ecommerce.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.hcl.ecommerce.entity.CartItem;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.service.CartItemService;
import com.hcl.ecommerce.service.ProductService;

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
		
		ResponseEntity<CartItem> cart = cartItemController.addCartItem(cartItem);
		
		assertEquals(HttpStatus.CREATED.value(), cart.getStatusCodeValue());
		
		assertEquals(cartItem, cart.getBody());
		
	}
	
	@Test
	public void testGetCartItemById() throws Exception {
		
		
		
	}
	
	@Test
	public void testUpdateCartItem() throws Exception {
		
		
		
	}
	
	@Test
	public void testDeleteCartItem() throws Exception {
		
		
		
	}

}
