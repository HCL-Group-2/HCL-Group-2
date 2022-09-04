package com.hcl.ecommerce.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.hcl.ecommerce.entity.Order;
import com.hcl.ecommerce.entity.Payment;
import com.hcl.ecommerce.entity.ShippingAddress;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.service.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderContollerTest {
	
	@InjectMocks
	OrderController orderController;
	
	@Mock
	OrderService orderService;
	
	@Test
	public void testAddOrder() throws Exception {
		
		User user = new User();
		user.setId(1);
		user.setFirstName("Test");
		user.setLastName("User");
		user.setEmail("testuser@gmail.com");
		user.setPassword("test");
		
		ShippingAddress shippingAddress = new ShippingAddress();
		shippingAddress.setId(1);
		shippingAddress.setAddress1("123 Test Address");
		shippingAddress.setAddress2(null);
		shippingAddress.setCity("Frisco");
		shippingAddress.setState("Texas");
		shippingAddress.setZipCode("75034");
		
		Payment payment = new Payment();
		payment.setId(1);
		payment.setName("Test Name");
		payment.setCreditCardNumber("1234123412341234");
		payment.setExpirationDate("2024-01-01");
		
		Order mockOrder = new Order();
		mockOrder.setId(1);
		mockOrder.setOrderDate(LocalDate.now());
		mockOrder.setOrderTotal(50.0);
		mockOrder.setOrderStatus("");
		mockOrder.setUser(user);
		mockOrder.setShippingAddress(shippingAddress);
		mockOrder.setPayment(payment);
		
		Mockito.when(orderService.addOrder(any(Order.class))).thenReturn(mockOrder);
		
		ResponseEntity<Order> response = orderController.addOrder(mockOrder);
		
		Order order = response.getBody();
		
		assertEquals(HttpStatus.CREATED.value(), response.getStatusCodeValue());
		
		assertEquals(LocalDate.now(), order.getOrderDate());
		assertEquals(50.0, order.getOrderTotal(), 0.001);
		assertEquals("", order.getOrderStatus());
		
	}
	
	@Test
	public void testGetOrderById() throws Exception {
		
		User user = new User();
		user.setId(1);
		user.setFirstName("Test");
		user.setLastName("User");
		user.setEmail("testuser@gmail.com");
		user.setPassword("test");
		
		ShippingAddress shippingAddress = new ShippingAddress();
		shippingAddress.setId(1);
		shippingAddress.setAddress1("123 Test Address");
		shippingAddress.setAddress2(null);
		shippingAddress.setCity("Frisco");
		shippingAddress.setState("Texas");
		shippingAddress.setZipCode("75034");
		
		Payment payment = new Payment();
		payment.setId(1);
		payment.setName("Test Name");
		payment.setCreditCardNumber("1234123412341234");
		payment.setExpirationDate("2024-01-01");
		
		Order mockOrder = new Order();
		mockOrder.setId(1);
		mockOrder.setOrderDate(LocalDate.now());
		mockOrder.setOrderTotal(50.0);
		mockOrder.setOrderStatus("");
		mockOrder.setUser(user);
		mockOrder.setShippingAddress(shippingAddress);
		mockOrder.setPayment(payment);
		
		Mockito.when(orderService.getOrderById(1)).thenReturn(mockOrder);
		
		ResponseEntity<Order> response = orderController.getOrderById(1);
		
		Order order = response.getBody();
		
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		
		assertEquals(LocalDate.now(), order.getOrderDate());
		assertEquals(50.0, order.getOrderTotal(), 0.001);
		assertEquals("", order.getOrderStatus());
		
	}
	
	@Test
	public void testUpdateOrder() throws Exception {
		
		User user = new User();
		user.setId(1);
		user.setFirstName("Test");
		user.setLastName("User");
		user.setEmail("testuser@gmail.com");
		user.setPassword("test");
		
		ShippingAddress shippingAddress = new ShippingAddress();
		shippingAddress.setId(1);
		shippingAddress.setAddress1("123 Test Address");
		shippingAddress.setAddress2(null);
		shippingAddress.setCity("Frisco");
		shippingAddress.setState("Texas");
		shippingAddress.setZipCode("75034");
		
		Payment payment = new Payment();
		payment.setId(1);
		payment.setName("Test Name");
		payment.setCreditCardNumber("1234123412341234");
		payment.setExpirationDate("2024-01-01");
		
		Order mockOrder = new Order();
		mockOrder.setId(1);
		mockOrder.setOrderDate(LocalDate.now());
		mockOrder.setOrderTotal(50.0);
		mockOrder.setOrderStatus("");
		mockOrder.setUser(user);
		mockOrder.setShippingAddress(shippingAddress);
		mockOrder.setPayment(payment);
		
		Mockito.when(orderService.updateOrder(any(Order.class))).thenReturn(mockOrder);
		
		ResponseEntity<Order> response = orderController.updateOrder(mockOrder);
		
		Order order = response.getBody();
		
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		
		assertEquals(LocalDate.now(), order.getOrderDate());
		assertEquals(50.0, order.getOrderTotal(), 0.001);
		assertEquals("", order.getOrderStatus());
		
	}
	
	@Test
	public void testDeleteOrder() throws Exception {
		
		User user = new User();
		user.setId(1);
		user.setFirstName("Test");
		user.setLastName("User");
		user.setEmail("testuser@gmail.com");
		user.setPassword("test");
		
		ShippingAddress shippingAddress = new ShippingAddress();
		shippingAddress.setId(1);
		shippingAddress.setAddress1("123 Test Address");
		shippingAddress.setAddress2(null);
		shippingAddress.setCity("Frisco");
		shippingAddress.setState("Texas");
		shippingAddress.setZipCode("75034");
		
		Payment payment = new Payment();
		payment.setId(1);
		payment.setName("Test Name");
		payment.setCreditCardNumber("1234123412341234");
		payment.setExpirationDate("2024-01-01");
		
		Order mockOrder = new Order();
		mockOrder.setId(1);
		mockOrder.setOrderDate(LocalDate.now());
		mockOrder.setOrderTotal(50.0);
		mockOrder.setOrderStatus("");
		mockOrder.setUser(user);
		mockOrder.setShippingAddress(shippingAddress);
		mockOrder.setPayment(payment);
		
		Mockito.when(orderService.deleteOrder(1)).thenReturn("Success");
		
		ResponseEntity<String> response = orderController.deleteOrder(1);
		
		String str = response.getBody();
		
		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatusCodeValue());
		
		assertEquals("Success", str);
		
	}

}
