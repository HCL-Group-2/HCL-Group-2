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
public class CartItemControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testAddCartItem() throws Exception {
		testAddUser();
		testAddProduct();
		
		String mockCartItemJson = 
				"{\"quantity\": 1, \"user\": {\"id\": 1}, \"product\": {\"id\": 2}}";
		
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
		assertEquals("http://localhost/cartitem/3",
				response.getHeader(HttpHeaders.LOCATION));
		
	}
	
	@Test
	public void testGetCartItemById() throws Exception {
		testAddUser();
		testAddProduct();
		testAddCartItem();
		
		//Create a request
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/cartitem/3")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String expected = "{\"id\":3,\"quantity\":1,\"user\":{\"id\":1,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"janedoe@gmail.com\",\"password\":\"jane\"},\"product\":{\"id\": 2,\"name\":\"Test Product A\",\"description\":\"A test product.\",\"price\":50.0,\"image\":\"Test Image\",\"category\":\"Test Category\",\"inventory\":300}}";
		
		//Assert that response is what was expected
		assertEquals(expected, result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void testUpdateCartItem() throws Exception {
		testAddCartItem();
		
		String mockCartItemJson = 
				"{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"johndoe@gmail.com\",\"password\":\"john\"}";
		
		//Create a put request with an accept header for application\json
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/cartitem/")
				.accept(MediaType.APPLICATION_JSON).content(mockCartItemJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		String expected = "{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"johndoe@gmail.com\",\"password\":\"john\"}";
		
		//Assert that response is what was expected
		assertEquals(expected, result.getResponse().getContentAsString());
		
	}
	
	@Test
	public void testDeleteCartItem() throws Exception {
		testAddCartItem();
		
		String mockCartItemJson = 
				"{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"johndoe@gmail.com\",\"password\":\"john\"}";
		
		//Create a put request with an accept header for application\json
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/cartitem/1")
				.accept(MediaType.APPLICATION_JSON).content(mockCartItemJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		//Assert that the return status is 204 No Content
		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
		
	}
	
	@Test
	public void testAddUser() throws Exception {
		String mockUserJson = 
				"{\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"janedoe@gmail.com\",\"password\":\"jane\"}";
		
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
		assertEquals("http://localhost/product/2",
				response.getHeader(HttpHeaders.LOCATION));
		
	}

}