package com.hcl.ecommerce.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

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
import com.hcl.ecommerce.entity.Payment;
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
		
		CartItem cartItem = new CartItem();
		cartItem.setId(1);
		cartItem.setQuantity(1);
		cartItem.setSubtotal(50.0);
		cartItem.setUser(user);
		cartItem.setProduct(product);
		
		List<CartItem> cartItems = new ArrayList<>();
		cartItems.add(cartItem);
		user.setCartItems(cartItems);
		
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
		payment.setExpirationDate("01-24");
		
		Order mockOrder = new Order();
		mockOrder.setId(1);
		mockOrder.setOrderDate(LocalDate.now());
		mockOrder.setOrderTotal(50.0);
		mockOrder.setOrderStatus("");
		mockOrder.setUser(user);
		mockOrder.setShippingAddress(shippingAddress);
		mockOrder.setPayment(payment);
		
		Mockito.when(userRepository.findByEmail(mockOrder.getUser().getEmail())).thenReturn(user);
		
		Mockito.when(cartItemRepository.getAllCartItemsByUserId(1)).thenReturn(cartItems);
		
		Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(product));
		
		Mockito.when(productRepository.save(any(Product.class))).thenReturn(product);
		
		Mockito.when(orderRepository.save(any(Order.class))).thenReturn(mockOrder);
		
		Mockito.doNothing().when(mailSenderService).sendEmail(null);
		
		Mockito.doNothing().when(mailSenderService).sendEmailWithAttachment(null, null);
		
		Order order = orderServiceImpl.addOrder(mockOrder);
		
		assertNotNull(order);
		
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
		
		Product product = new Product();
		product.setId(1);
		product.setName("Test Product");
		product.setDescription("A test product.");
		product.setPrice(50.0);
		product.setImage("Test Image");
		product.setCategory("Test Category");
		product.setInventory(300);
		
		CartItem cartItem = new CartItem();
		cartItem.setId(1);
		cartItem.setQuantity(1);
		cartItem.setSubtotal(50.0);
		cartItem.setUser(user);
		cartItem.setProduct(product);
		
		List<CartItem> cartItems = new ArrayList<>();
		cartItems.add(cartItem);
		user.setCartItems(cartItems);
		
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
		payment.setExpirationDate("01-24");
		
		Order mockOrder = new Order();
		mockOrder.setId(1);
		mockOrder.setOrderDate(LocalDate.now());
		mockOrder.setOrderTotal(50.0);
		mockOrder.setOrderStatus("");
		mockOrder.setUser(user);
		mockOrder.setShippingAddress(shippingAddress);
		mockOrder.setPayment(payment);
		
		Mockito.when(orderRepository.findById(1)).thenReturn(Optional.of(mockOrder));
		
		Order order = orderServiceImpl.getOrderById(1);
		
		assertNotNull(order);
		
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
		
		Product product = new Product();
		product.setId(1);
		product.setName("Test Product");
		product.setDescription("A test product.");
		product.setPrice(50.0);
		product.setImage("Test Image");
		product.setCategory("Test Category");
		product.setInventory(300);
		
		CartItem cartItem = new CartItem();
		cartItem.setId(1);
		cartItem.setQuantity(1);
		cartItem.setSubtotal(50.0);
		cartItem.setUser(user);
		cartItem.setProduct(product);
		
		List<CartItem> cartItems = new ArrayList<>();
		cartItems.add(cartItem);
		user.setCartItems(cartItems);
		
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
		payment.setExpirationDate("01-24");
		
		Order mockOrder = new Order();
		mockOrder.setId(1);
		mockOrder.setOrderDate(LocalDate.now());
		mockOrder.setOrderTotal(50.0);
		mockOrder.setOrderStatus("");
		mockOrder.setUser(user);
		mockOrder.setShippingAddress(shippingAddress);
		mockOrder.setPayment(payment);
		
		Mockito.when(orderRepository.findById(1)).thenReturn(Optional.of(mockOrder));
		
		Mockito.when(orderRepository.save(any(Order.class))).thenReturn(mockOrder);
		
		Order order = orderServiceImpl.updateOrder(mockOrder);
		
		assertNotNull(order);
		
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
		
		Product product = new Product();
		product.setId(1);
		product.setName("Test Product");
		product.setDescription("A test product.");
		product.setPrice(50.0);
		product.setImage("Test Image");
		product.setCategory("Test Category");
		product.setInventory(300);
		
		CartItem cartItem = new CartItem();
		cartItem.setId(1);
		cartItem.setQuantity(1);
		cartItem.setSubtotal(50.0);
		cartItem.setUser(user);
		cartItem.setProduct(product);
		
		List<CartItem> cartItems = new ArrayList<>();
		cartItems.add(cartItem);
		user.setCartItems(cartItems);
		
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
		payment.setExpirationDate("01-24");
		
		Order mockOrder = new Order();
		mockOrder.setId(1);
		mockOrder.setOrderDate(LocalDate.now());
		mockOrder.setOrderTotal(50.0);
		mockOrder.setOrderStatus("");
		mockOrder.setUser(user);
		mockOrder.setShippingAddress(shippingAddress);
		mockOrder.setPayment(payment);
		
		orderServiceImpl.deleteOrder(1);
		
		Mockito.verify(orderRepository, times(1)).deleteById(1);
		
	}

}
