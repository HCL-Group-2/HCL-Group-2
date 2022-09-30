package com.hcl.ecommerce.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hcl.ecommerce.entity.CartItem;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.exception.AddEntityException;
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
		
		User user = new User(1, "larry", "miller", "larry@email.com");
		
		Product product = new Product(1, "phone", "a phone", new BigDecimal(999.0), "image url", "phone", 300);
		
		CartItem mockCartItem = new CartItem(1, new BigDecimal(999.0), user, product);
		
		when(userRepository.findById(1)).thenReturn(Optional.of(user));
		
		when(productRepository.findById(1)).thenReturn(Optional.of(product));
		
		when(cartItemRepository.findById(1)).thenReturn(Optional.of(mockCartItem));
		
		when(cartItemRepository.save(any(CartItem.class))).thenReturn(mockCartItem);
		
		CartItem cartItem = cartItemServiceImpl.addCartItem(mockCartItem);
		
		verify(cartItemRepository).save(mockCartItem);
		
	}
	
	@Test
	public void testAddCartItemItemAlreadyExist() throws Exception {
		
		User user = new User(1, "larry", "miller", "larry@email.com", new ArrayList<CartItem>(), null);
		
		Product product = new Product(1, "phone", "a phone", new BigDecimal(999.0), "image url", "phone", 300);
		
		CartItem mockCartItem = new CartItem(1, 1, new BigDecimal(999.0), user, product);
		
		user.getCartItems().add(mockCartItem);
		
		when(userRepository.findById(1)).thenReturn(Optional.of(user));
		
		when(productRepository.findById(1)).thenReturn(Optional.of(product));
		
		when(cartItemRepository.findById(1)).thenReturn(Optional.of(mockCartItem));
		
		when(cartItemRepository.save(any(CartItem.class))).thenReturn(mockCartItem);
		
		CartItem cartItem = cartItemServiceImpl.addCartItem(mockCartItem);
		
		verify(cartItemRepository).save(mockCartItem);
		
	}
	
	@Test(expected = AddEntityException.class)
	public void testAddCartItemUserDoesNotExist() throws Exception {
		
		User user = new User(1, "larry", "miller", "larry@email.com");
		
		Product product = new Product(1, "phone", "a phone", new BigDecimal(999.0), "image url", "phone", 300);
		
		CartItem mockCartItem = new CartItem(1, new BigDecimal(999.0), user, product);
		
		when(userRepository.findById(1)).thenReturn(Optional.empty());
		
		cartItemServiceImpl.addCartItem(mockCartItem);
		
	}
	
	@Test(expected = AddEntityException.class)
	public void testAddCartItemProductDoesNotExist() throws Exception {
		
		User user = new User(1, "larry", "miller", "larry@email.com");
		
		Product product = new Product(1, "phone", "a phone", new BigDecimal(999.0), "image url", "phone", 300);
		
		CartItem mockCartItem = new CartItem(1, new BigDecimal(999.0), user, product);
		
		when(productRepository.findById(1)).thenReturn(Optional.empty());
		
		cartItemServiceImpl.addCartItem(mockCartItem);
		
	}
	
	@Test
	public void testGetCartItemById() throws Exception {
		
		CartItem mockCartItem = new CartItem(1, new BigDecimal(999.0), new User(), new Product());
		
		when(cartItemRepository.findById(1)).thenReturn(Optional.of(mockCartItem));
		
		CartItem cartItem = cartItemServiceImpl.getCartItemById(1);
		
		verify(cartItemRepository).findById(1);
		
	}
	
	@Test
	public void testUpdateCartItem() throws Exception {
		
		CartItem mockCartItem = new CartItem(1, 1, new BigDecimal(999.0), new User(), new Product());
		
		when(cartItemRepository.findById(1)).thenReturn(Optional.of(mockCartItem));
		
		when(cartItemRepository.save(any(CartItem.class))).thenReturn(mockCartItem);
		
		CartItem cartItem = cartItemServiceImpl.updateCartItem(mockCartItem);
		
		verify(cartItemRepository).save(mockCartItem);
		
	}
	
	@Test
	public void testDeleteCartItem() throws Exception {
		
		cartItemServiceImpl.deleteCartItem(1);
		
		verify(cartItemRepository).deleteById(1);
		
	}

}
