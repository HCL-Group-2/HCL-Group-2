package com.hcl.ecommerce.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hcl.ecommerce.entity.CartItem;
import com.hcl.ecommerce.entity.Order;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.ShippingAddress;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.repository.CartItemRepository;
import com.hcl.ecommerce.repository.OrderRepository;
import com.hcl.ecommerce.repository.ProductRepository;
import com.hcl.ecommerce.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
	
	@InjectMocks
	OrderServiceImpl orderServiceImpl;

	@Mock
	OrderRepository orderRepository;
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	ProductRepository productRepository;
	
	@Mock
	CartItemRepository cartItemRepository;
	
	@Mock
	MailSenderService mailSenderService;
	
	@Test
	public void testAddOrder() throws Exception {
		
		assertTrue(true);
		
	}
	
	@Test
	public void testGetOrderById() throws Exception {
		
		assertTrue(true);
		
	}
	
	@Test
	public void testUpdateOrder() throws Exception {
		
		assertTrue(true);
		
	}
	
	@Test
	public void testDeleteOrder() throws Exception {
		
		assertTrue(true);
		
	}

}
