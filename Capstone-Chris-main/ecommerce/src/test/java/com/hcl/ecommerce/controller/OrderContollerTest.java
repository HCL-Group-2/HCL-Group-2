package com.hcl.ecommerce.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.hcl.ecommerce.entity.Order;
import com.hcl.ecommerce.service.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderContollerTest {
	
	@InjectMocks
	OrderController orderController;
	
	@Mock
	OrderService orderService;
	
//	@BeforeEach
//	public void setupMocks() {
//		
//	}
	
	@Test
	public void testAddOrder() throws Exception {
		
		Order order = new Order();
		order.setOrderDate(LocalDate.now());
		order.setOrderTotal(300.0);
		order.setOrderStatus("In progress");
		
		Mockito.when(orderService.addOrder(order)).thenReturn(order);
		
		ResponseEntity<Order> ord = orderController.addOrder(order);
		
		assertEquals(HttpStatus.CREATED.value(), ord.getStatusCodeValue());
		
		assertEquals(order, ord.getBody());
		
	}
	
	@Test
	public void testGetOrderById() throws Exception {
		
		Order order = new Order();
		order.setId(1);
		order.setOrderDate(LocalDate.now());
		order.setOrderTotal(300.0);
		order.setOrderStatus("In progress");
		
		Mockito.when(orderService.getOrderById(1)).thenReturn(order);
		
		ResponseEntity<Order> ord = orderController.getOrderById(1);
		
		assertEquals(HttpStatus.OK.value(), ord.getStatusCodeValue());
		
		assertEquals(order, ord.getBody());
		
	}
	
	@Test
	public void testDeleteOrder() throws Exception {
		
		Order order = new Order();
		order.setId(1);
		order.setOrderDate(LocalDate.now());
		order.setOrderTotal(300.0);
		order.setOrderStatus("In progress");
		
		orderController.deleteOrder(1);
		
		Mockito.verify(orderService, times(1)).deleteOrder(1);
		
	}

}
