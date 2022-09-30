package com.hcl.ecommerce.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

	@Test
	public void testAddOrder() throws Exception {

		User user = new User(1, "larry", "miller", "larry@email.com");

		Product product = new Product(1, "phone", "a phone", new BigDecimal(999.0), "image url", "phone", 300);

		CartItem cartItem = new CartItem(1, 1, new BigDecimal(999.0), user, product);

		List<CartItem> cartItems = new ArrayList<>(List.of(cartItem));
		user.setCartItems(cartItems);

		Order mockOrder = new Order(1, LocalDate.now(), new BigDecimal(999.0), "In progress", user,
				new ShippingAddress(1, "123 Test Address", null, "Frisco", "Texas", "75034"), null);

		when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

		when(cartItemRepository.getAllCartItemsByUserId(1)).thenReturn(cartItems);

		when(productRepository.findById(1)).thenReturn(Optional.of(product));

		when(productRepository.save(any(Product.class))).thenReturn(product);

		when(orderRepository.save(any(Order.class))).thenReturn(mockOrder);

		Order order = orderServiceImpl.addOrder(mockOrder);

		verify(orderRepository).save(mockOrder);

	}

	@Test
	public void testGetOrderById() throws Exception {
		
		User user = new User(1, "larry", "miller", "larry@email.com");

		Product product = new Product(1, "phone", "a phone", new BigDecimal(999.0), "image url", "phone", 300);

		Order mockOrder = new Order(1, LocalDate.now(), new BigDecimal(999.0), "In progress", user,
				new ShippingAddress(1, "123 Test Address", null, "Frisco", "Texas", "75034"), null);

		when(orderRepository.findById(1)).thenReturn(Optional.of(mockOrder));
		
		Order order = orderServiceImpl.getOrderById(1);
		
		verify(orderRepository).findById(1);

	}

	@Test
	public void testUpdateOrder() throws Exception {
		
		User user = new User(1, "larry", "miller", "larry@email.com");

		Product product = new Product(1, "phone", "a phone", new BigDecimal(999.0), "image url", "phone", 300);

		Order mockOrder = new Order(1, LocalDate.now(), new BigDecimal(999.0), "In progress", user,
				new ShippingAddress(1, "123 Test Address", null, "Frisco", "Texas", "75034"), null);

		when(orderRepository.findById(1)).thenReturn(Optional.of(mockOrder));
		
		when(orderRepository.save(any(Order.class))).thenReturn(mockOrder);
		
		Order orderItem = orderServiceImpl.updateOrder(mockOrder);
		
		verify(orderRepository).save(mockOrder);

	}

	@Test
	public void testDeleteOrder() throws Exception {

		orderServiceImpl.deleteOrder(1);
		
		verify(orderRepository).deleteById(1);

	}

}
