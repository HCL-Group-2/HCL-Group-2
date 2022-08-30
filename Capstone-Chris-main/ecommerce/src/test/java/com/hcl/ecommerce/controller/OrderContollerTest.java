package com.hcl.ecommerce.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderContollerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testAddOrder() throws Exception {
		testAddUser();
		testAddAddress();
		testAddCreditCard();
		testAddProduct();
		testAddCartItem();
		
		String mockOrderJson = 
				"{\"user\":{\"id\":1},\"address\":{\"id\":1},\"creditCard\":{\"id\":1}}";
		
		//Create a post request with an accept header for application\json
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/order/")
				.accept(MediaType.APPLICATION_JSON).content(mockOrderJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		//Assert that the return status is CREATED
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		
		//Get the location from response header and assert that it contains the URI of the created resource
		assertEquals("http://localhost/order/1",
				response.getHeader(HttpHeaders.LOCATION));
		
	}
	
	@Test
	public void testGetOrderById() throws Exception {
		testAddOrder();
		
		//Create a request
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/order/1")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String expected = "{\"id\":1,\"orderDate\":\"2022-08-30\",\"orderStatus\":\"Processing\",\"orderTotal\":50.0,\"user\":{\"id\":1,\"firstName\":\"Test\",\"lastName\":\"User\",\"email\":\"testuser@gmail.com\",\"password\":\"test\"},\"shippingAddress\":{\"id\":1,\"address1\":\"1234 Test Address\",\"address2\":null,\"city\":\"Frisco\",\"state\":\"Texas\",\"zipCode\":\"75034\"},\"payment\":{\"id\":1,\"creditCardNumber\":\"1234567812345678\",\"expirationDate\":\"2024-01-01\",\"verificationCode\":\"123\"},\"orderItems\":[{\"id\":1,\"quantity\":1,\"subtotal\":50.0,\"product\":{\"id\":1,\"name\":\"Test Product\",\"description\":\"A test product.\",\"price\":50.0,\"image\":\"Test Image\",\"category\":\"Test Category\",\"inventory\":299}}]}";
		
		//Assert that response is what was expected
		assertEquals(expected, result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void testUpdateOrder() throws Exception {
		testAddOrder();
		
		String mockOrderJson = 
				"{\"id\":1,\"orderDate\":\"2022-10-01\",\"orderTotal\":50.0,\"orderStatus\":\"Shipped\",\"user\":{\"id\":1},\"address\":{\"id\":1},\"creditCard\":{\"id\":1}}";
		
		//Create a put request with an accept header for application\json
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/order/")
				.accept(MediaType.APPLICATION_JSON).content(mockOrderJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String expected = "{\"id\":1,\"orderDate\":\"2022-10-01\",\"orderTotal\":50.0,\"orderStatus\":\"Shipped\",\"user\":{\"id\":1,\"firstName\":null,\"lastName\":null,\"email\":null,\"password\":null},\"shippingAddress\":null,\"payment\":null,\"orderItems\":[]}";
		
		//Assert that response is what was expected
		assertEquals(expected, result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void testDeleteOrder() throws Exception {
		testAddOrder();
		
		String mockOrderJson = 
				"{\"id\":1,\"quantity\":2,\"subtotal\":50.0,\"user\":{\"id\":1,\"firstName\":null,\"lastName\":null,\"email\":null,\"password\":null},\"product\":{\"id\":1,\"name\":null,\"description\":null,\"price\":0.0,\"image\":null,\"category\":null,\"inventory\":0}}";
		
		//Create a put request with an accept header for application\json
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/order/1")
				.accept(MediaType.APPLICATION_JSON).content(mockOrderJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		//Assert that the return status is 204 No Content
		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
		
	}
	
	@Test
	public void testAddUser() throws Exception {
		String mockUserJson = 
				"{\"id\":1,\"firstName\":\"Test\",\"lastName\":\"User\",\"email\":\"testuser@gmail.com\",\"password\":\"test\"}";
		
		//Create a post request with an accept header for application\json
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/user/")
				.accept(MediaType.APPLICATION_JSON).content(mockUserJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		//Assert that the return status is CREATED
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		
		//Get the location from response header and assert that it contains the URI of the created resource
		assertEquals("http://localhost/user/1",
				response.getHeader(HttpHeaders.LOCATION));
		
	}
	
	@Test
	public void testAddAddress() throws Exception {
//		testAddUser();
		
		String mockAddressJson = 
				"{\"address1\":\"1234 Test Address\",\"address2\":null,\"city\":\"Frisco\",\"state\":\"Texas\",\"zipCode\":\"75034\",\"user\":{\"id\":1}}";
		
		//Create a post request with an accept header for application\json
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/address/")
				.accept(MediaType.APPLICATION_JSON).content(mockAddressJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		//Assert that the return status is CREATED
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		
		//Get the location from response header and assert that it contains the URI of the created resource
		assertEquals("http://localhost/address/1",
				response.getHeader(HttpHeaders.LOCATION));
		
	}
	
	@Test
	public void testAddCreditCard() throws Exception {
//		testAddUser();
		
		String mockCreditCardJson = 
				"{\"name\":\"Test User\",\"creditCardNumber\":\"1234567812345678\",\"expirationDate\":\"2024-01-01\",\"verificationCode\":\"123\",\"user\":{\"id\":1}}";
		
		//Create a post request with an accept header for application\json
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/creditcard/")
				.accept(MediaType.APPLICATION_JSON).content(mockCreditCardJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		//Assert that the return status is CREATED
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		
		//Get the location from response header and assert that it contains the URI of the created resource
		assertEquals("http://localhost/creditcard/1",
				response.getHeader(HttpHeaders.LOCATION));
		
	}
	
	@Test
	public void testAddProduct() throws Exception {
		String mockProductJson = 
				"{\"name\":\"Test Product\",\"description\":\"A test product.\",\"price\":50.0,\"image\":\"Test Image\",\"category\":\"Test Category\",\"inventory\":300}";
		
		//Create a post request with an accept header for application\json
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/product/")
				.accept(MediaType.APPLICATION_JSON).content(mockProductJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		//Assert that the return status is CREATED
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		
		//Get the location from response header and assert that it contains the URI of the created resource
		assertEquals("http://localhost/product/1",
				response.getHeader(HttpHeaders.LOCATION));
		
	}
	
	@Test
	public void testAddCartItem() throws Exception {
//		testAddUser();
//		testAddProduct();
		
		String mockCartItemJson = 
				"{\"quantity\":1,\"user\":{\"id\":1},\"product\":{\"id\":1}}";
		
		//Create a post request with an accept header for application\json
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/cartitem/")
				.accept(MediaType.APPLICATION_JSON).content(mockCartItemJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		//Assert that the return status is CREATED
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		
		//Get the location from response header and assert that it contains the URI of the created resource
		assertEquals("http://localhost/cartitem/1",
				response.getHeader(HttpHeaders.LOCATION));
		
	}

}
