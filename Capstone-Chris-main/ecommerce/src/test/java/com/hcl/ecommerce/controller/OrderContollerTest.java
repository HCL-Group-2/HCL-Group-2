package com.hcl.ecommerce.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.ecommerce.entity.Order;
import com.hcl.ecommerce.service.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderContollerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	Jackson2ObjectMapperBuilder mapperBuilder;
	
	@MockBean
	OrderService orderService;
	
	@InjectMocks
	OrderController orderController;
	
	@Test
	public void testAddOrder() throws Exception {
		
		String mockOrderJson = 
				"{\"user\":{\"firstName\":\"Test\",\"lastName\":\"User\",\"email\":\"testuser@gmail.com\"},\"shippingAddress\": {\"address1\":\"123 Test Address A\",\"address2\":null,\"city\":\"Frisco\",\"state\":\"Texas\",\"zipCode\":\"75034\"},\"payment\":{\"name\":\"Test Name\",\"creditCardNumber\":\"1234123412341234\",\"expirationDate\":\"01-24\"}}";
		
		ObjectMapper mapper = mapperBuilder.build();
        Order mockOrder = mapper.readValue(mockOrderJson, Order.class);
		
		Mockito.when(orderService.addOrder(any(Order.class))).thenReturn(mockOrder);
		
		//Create a post request with an accept header for application\json
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/order/")
				.accept(MediaType.APPLICATION_JSON).content(mockOrderJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		//Assert that the return status is CREATED
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		
	}
	
	@Test
	public void testGetOrderById() throws Exception {
		
		String mockOrderJson = "{\"id\":1,\"orderDate\":\"2022-01-01\",\"orderTotal\":50.0,\"orderStatus\":\"Processing\",\"user\":{\"id\":1,\"firstName\":\"Test\",\"lastName\":\"User\",\"email\":\"testuser@gmail.com\",\"password\":\"test\"},\"shippingAddress\":{\"id\":1,\"address1\":\"123 Test Address\",\"address2\":null,\"city\":\"Frisco\",\"state\":\"Texas\",\"zipCode\":\"75034\"},\"payment\":{\"id\":1,\"name\":null,\"creditCardNumber\":\"123412341234\",\"expirationDate\":\"01-24\"},\"orderItems\":[{\"id\":1,\"quantity\":1,\"subtotal\":50.0,\"product\":{\"id\":1,\"name\":\"Test Product\",\"description\":\"A test product.\",\"price\":50.0,\"image\":\"Test Image\",\"category\":\"Test Category\",\"inventory\":300}}]}";
		
		ObjectMapper mapper = mapperBuilder.build();
        Order mockOrder = mapper.readValue(mockOrderJson, Order.class);
		
		Mockito.when(orderService.getOrderById(1)).thenReturn(mockOrder);
		
		//Create a request
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/order/1")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String expected = "{\"id\":1,\"orderDate\":\"2022-01-01\",\"orderTotal\":50.0,\"orderStatus\":\"Processing\",\"user\":{\"id\":1,\"firstName\":\"Test\",\"lastName\":\"User\",\"email\":\"testuser@gmail.com\",\"password\":\"test\"},\"shippingAddress\":{\"id\":1,\"address1\":\"123 Test Address\",\"address2\":null,\"city\":\"Frisco\",\"state\":\"Texas\",\"zipCode\":\"75034\"},\"payment\":{\"id\":1,\"name\":null,\"creditCardNumber\":\"123412341234\",\"expirationDate\":\"01-24\"},\"orderItems\":[{\"id\":1,\"quantity\":1,\"subtotal\":50.0,\"product\":{\"id\":1,\"name\":\"Test Product\",\"description\":\"A test product.\",\"price\":50.0,\"image\":\"Test Image\",\"category\":\"Test Category\",\"inventory\":300}}]}";
		
		//Assert that response is what was expected
		assertEquals(expected, result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void testUpdateOrder() throws Exception {
		
		String mockOrderJson = 
				"{\"id\":1,\"orderDate\":\"2022-01-01\",\"orderTotal\":50.0,\"orderStatus\":\"Processing\",\"user\":{\"id\":1,\"firstName\":\"Test\",\"lastName\":\"User\",\"email\":\"testuser@gmail.com\",\"password\":\"test\"},\"shippingAddress\":{\"id\":1,\"address1\":\"123 Test Address\",\"address2\":null,\"city\":\"Frisco\",\"state\":\"Texas\",\"zipCode\":\"75034\"},\"payment\":{\"id\":1,\"name\":null,\"creditCardNumber\":\"123412341234\",\"expirationDate\":\"01-24\"},\"orderItems\":[{\"id\":1,\"quantity\":1,\"subtotal\":50.0,\"product\":{\"id\":1,\"name\":\"Test Product\",\"description\":\"A test product.\",\"price\":50.0,\"image\":\"Test Image\",\"category\":\"Test Category\",\"inventory\":300}}]}";
		
		ObjectMapper mapper = mapperBuilder.build();
        Order mockOrder = mapper.readValue(mockOrderJson, Order.class);
		
		Mockito.when(orderService.updateOrder(any(Order.class))).thenReturn(mockOrder);
		
		//Create a put request with an accept header for application\json
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/order/")
				.accept(MediaType.APPLICATION_JSON).content(mockOrderJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String expected = "{\"id\":1,\"orderDate\":\"2022-01-01\",\"orderTotal\":50.0,\"orderStatus\":\"Processing\",\"user\":{\"id\":1,\"firstName\":\"Test\",\"lastName\":\"User\",\"email\":\"testuser@gmail.com\",\"password\":\"test\"},\"shippingAddress\":{\"id\":1,\"address1\":\"123 Test Address\",\"address2\":null,\"city\":\"Frisco\",\"state\":\"Texas\",\"zipCode\":\"75034\"},\"payment\":{\"id\":1,\"name\":null,\"creditCardNumber\":\"123412341234\",\"expirationDate\":\"01-24\"},\"orderItems\":[{\"id\":1,\"quantity\":1,\"subtotal\":50.0,\"product\":{\"id\":1,\"name\":\"Test Product\",\"description\":\"A test product.\",\"price\":50.0,\"image\":\"Test Image\",\"category\":\"Test Category\",\"inventory\":300}}]}";
		
		//Assert that response is what was expected
		assertEquals(expected, result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void testDeleteOrder() throws Exception {
		
		String mockOrderJson = 
				"{\"id\":1,\"orderDate\":\"2022-01-01\",\"orderTotal\":50.0,\"orderStatus\":\"Processing\",\"user\":{\"id\":1,\"firstName\":\"Test\",\"lastName\":\"User\",\"email\":\"testuser@gmail.com\",\"password\":\"test\"},\"shippingAddress\":{\"id\":1,\"address1\":\"123 Test Address\",\"address2\":null,\"city\":\"Frisco\",\"state\":\"Texas\",\"zipCode\":\"75034\"},\"payment\":{\"id\":1,\"name\":null,\"creditCardNumber\":\"123412341234\",\"expirationDate\":\"01-24\"},\"orderItems\":[{\"id\":1,\"quantity\":1,\"subtotal\":50.0,\"product\":{\"id\":1,\"name\":\"Test Product\",\"description\":\"A test product.\",\"price\":50.0,\"image\":\"Test Image\",\"category\":\"Test Category\",\"inventory\":300}}]}";
		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/order/1")
				.accept(MediaType.APPLICATION_JSON).content(mockOrderJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		//Assert that the return status is 204 No Content
		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
		
	}

}
