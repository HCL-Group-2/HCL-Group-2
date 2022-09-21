package com.hcl.ecommerce.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hcl.ecommerce.entity.CartItem;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.repository.CartItemRepository;
import com.hcl.ecommerce.repository.ProductRepository;
import com.hcl.ecommerce.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartItemServiceImplTest {
	
	@InjectMocks
	CartItemServiceImpl cartItemServiceImpl;

	@Mock
	CartItemRepository cartItemRepository;
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	ProductRepository productRepository;
	
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
		product.setPrice(new BigDecimal(50.0));
		product.setImage("Test Image");
		product.setCategory("Test Category");
		product.setInventory(300);
		
		CartItem mockCartItem = new CartItem();
		mockCartItem.setId(1);
		mockCartItem.setQuantity(1);
		mockCartItem.setSubtotal(new BigDecimal(50.0));
		mockCartItem.setUser(user);
		mockCartItem.setProduct(product);
		
		Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
		
		Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(product));
		
		Mockito.when(cartItemRepository.save(any(CartItem.class))).thenReturn(mockCartItem);
		
		CartItem cartItem = cartItemServiceImpl.addCartItem(mockCartItem);
		
		assertNotNull(cartItem);
		
		assertEquals(1, cartItem.getQuantity());
		//assertEquals(50.0, cartItem.getSubtotal(), 0.001);
		assertTrue(cartItem.getSubtotal().equals(new BigDecimal(50.0)));
		
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
		product.setPrice(new BigDecimal(50.0));
		product.setImage("Test Image");
		product.setCategory("Test Category");
		product.setInventory(300);
		
		CartItem mockCartItem = new CartItem();
		mockCartItem.setId(1);
		mockCartItem.setQuantity(1);
		mockCartItem.setSubtotal(new BigDecimal(50.0));
		mockCartItem.setUser(user);
		mockCartItem.setProduct(product);
		
		Mockito.when(cartItemRepository.findById(1)).thenReturn(Optional.of(mockCartItem));
		
		CartItem cartItem = cartItemServiceImpl.getCartItemById(1);
		
		assertNotNull(cartItem);
		
		assertEquals(1, cartItem.getQuantity());
		//assertEquals(50.0, cartItem.getSubtotal(), 0.001);
		assertTrue(cartItem.getSubtotal().equals(new BigDecimal(50.0)));
		
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
		product.setPrice(new BigDecimal(50.0));
		product.setImage("Test Image");
		product.setCategory("Test Category");
		product.setInventory(300);
		
		CartItem mockCartItem = new CartItem();
		mockCartItem.setId(1);
		mockCartItem.setQuantity(1);
		mockCartItem.setSubtotal(new BigDecimal(50.0));
		mockCartItem.setUser(user);
		mockCartItem.setProduct(product);
		
		Mockito.when(cartItemRepository.findById(1)).thenReturn(Optional.of(mockCartItem));
		
		Mockito.when(cartItemRepository.save(any(CartItem.class))).thenReturn(mockCartItem);
		
		CartItem cartItem = cartItemServiceImpl.updateCartItem(mockCartItem);
		
		assertNotNull(cartItem);
		
		assertEquals(1, cartItem.getQuantity());
		//assertEquals(50.0, cartItem.getSubtotal(), 0.001);
		assertTrue(cartItem.getSubtotal().equals(new BigDecimal(50.0)));
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
		product.setPrice(new BigDecimal(50.0));
		product.setImage("Test Image");
		product.setCategory("Test Category");
		product.setInventory(300);
		
		CartItem mockCartItem = new CartItem();
		mockCartItem.setId(1);
		mockCartItem.setQuantity(1);
		mockCartItem.setSubtotal(new BigDecimal(50.0));
		mockCartItem.setUser(user);
		mockCartItem.setProduct(product);
		
		cartItemServiceImpl.deleteCartItem(1);
		
		Mockito.verify(cartItemRepository, times(1)).deleteById(1);
		
	}

}
