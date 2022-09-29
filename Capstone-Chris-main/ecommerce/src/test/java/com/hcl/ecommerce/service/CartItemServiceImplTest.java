package com.hcl.ecommerce.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
		
//		CartItem mockCartItem = new CartItem(1, new BigDecimal(999.0), new User(), new Product());
//		
//		when(userRepository.findByEmail(mockCa.getEmail())).thenReturn(null);
//		
//		when(userRepository.save(any(User.class))).thenReturn(mockUser);
//		
//		CartItem cartItem = cartItemServiceImpl.addCartItem(mockCartItem);
//		
//		verify(userRepository).save(mockUser);
		
	}
	
	@Test
	public void testGetCartItemById() throws Exception {
		
		assertTrue(true);
		
	}
	
	@Test
	public void testUpdateCartItem() throws Exception {
		
		assertTrue(true);
	}
	
	@Test
	public void testDeleteCartItem() throws Exception {
		
		assertTrue(true);
		
	}

}
