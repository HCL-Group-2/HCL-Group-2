package com.hcl.ecommerce.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
import com.hcl.ecommerce.entity.CartItem;
import com.hcl.ecommerce.service.CartItemService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CartItemControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	Jackson2ObjectMapperBuilder mapperBuilder;
	
	@MockBean
	CartItemService cartItemService;
	
	@InjectMocks
	CartItemController cartItemController;
	
	@Test
	public void testAddCartItem() throws Exception {

		String mockCartItemJson = 
				"{"
					+ "\"quantity\":1,"
					+ "\"user\":"
					+ "{"
						+ "\"id\":1"
					+ "},"
					+ "\"product\":"
					+ "{"
						+ "\"id\":1"
					+ "}"
				+ "}";

		ObjectMapper mapper = mapperBuilder.build();
		CartItem mockCartItem = mapper.readValue(mockCartItemJson, CartItem.class);

		Mockito.when(cartItemService.addCartItem(any(CartItem.class))).thenReturn(mockCartItem);

		//Create a post request with an accept header for application\json
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/cartitem/")
				.accept(MediaType.APPLICATION_JSON).content(mockCartItemJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		//Assert that the return status is CREATED
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		assertTrue(true);

	}

	@Test
	public void testGetCartItemById() throws Exception {

		String mockCartItemJson = 
				"{"
					+ "\"id\":1,"
					+ "\"quantity\":1,"
					+ "\"subtotal\":50.0,"
					+ "\"user\":"
					+ "{"
						+ "\"id\":1,"
						+ "\"firstName\":\"Test\","
						+ "\"lastName\":\"User\","
						+ "\"email\":\"testuser@gmail.com\""
					+ "},"
					+ "\"product\":"
					+ "{"
						+ "\"id\":1,"
						+ "\"name\":\"Test Product\","
						+ "\"description\":\"A test product.\","
						+ "\"price\":50.0,"
						+ "\"image\":\"Test Image\","
						+ "\"category\":\"Test Category\","
						+ "\"inventory\":300"
					+ "}"
				+ "}";

		ObjectMapper mapper = mapperBuilder.build();
		CartItem mockCartItem = mapper.readValue(mockCartItemJson, CartItem.class);

		Mockito.when(cartItemService.getCartItemById(1)).thenReturn(mockCartItem);

		//Create a request
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/cartitem/1")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = 
				"{"
					+ "\"id\":1,"
					+ "\"quantity\":1,"
					+ "\"subtotal\":50.0,"
					+ "\"user\":"
					+ "{"
						+ "\"id\":1,"
						+ "\"firstName\":\"Test\","
						+ "\"lastName\":\"User\","
						+ "\"email\":\"testuser@gmail.com\""
					+ "},"
					+ "\"product\":"
					+ "{"
						+ "\"id\":1,"
						+ "\"name\":\"Test Product\","
						+ "\"description\":\"A test product.\","
						+ "\"price\":50.0,"
						+ "\"image\":\"Test Image\","
						+ "\"category\":\"Test Category\","
						+ "\"inventory\":300"
					+ "}"
				+ "}";

		//Assert that response is what was expected
		assertEquals(expected, result.getResponse().getContentAsString());
		assertTrue(true);

	}

	@Test
	public void testUpdateCartItem() throws Exception {

		String mockCartItemJson = 
				"{"
					+ "\"id\":1,"
					+ "\"quantity\":2,"
					+ "\"subtotal\":50.0,"
					+ "\"user\":"
					+ "{"
						+ "\"id\":1"
					+ "},"
					+ "\"product\":"
					+ "{"
						+ "\"id\":1"
					+ "}"
				+ "}";

		ObjectMapper mapper = mapperBuilder.build();
		CartItem mockCartItem = mapper.readValue(mockCartItemJson, CartItem.class);

		Mockito.when(cartItemService.updateCartItem(any(CartItem.class))).thenReturn(mockCartItem);

		//Create a put request with an accept header for application\json
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/cartitem/")
				.accept(MediaType.APPLICATION_JSON).content(mockCartItemJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = 
				"{"
					+ "\"id\":1,"
					+ "\"quantity\":2,"
					+ "\"subtotal\":50.0,"
					+ "\"user\":"
					+ "{"
						+ "\"id\":1,"
						+ "\"firstName\":null,"
						+ "\"lastName\":null,"
						+ "\"email\":null"
					+ "},"
					+ "\"product\":"
					+ "{"
						+ "\"id\":1,"
						+ "\"name\":null,"
						+ "\"description\":null,"
						+ "\"price\":null,"
						+ "\"image\":null,"
						+ "\"category\":null,"
						+ "\"inventory\":0"
					+ "}"
				+ "}";

		//Assert that response is what was expected
		assertEquals(expected, result.getResponse().getContentAsString());
		assertTrue(true);

	}

	@Test
	public void testDeleteCartItem() throws Exception {

		String mockCartItemJson = 
				"{"
					+ "\"id\":1,"
					+ "\"quantity\":2,"
					+ "\"subtotal\":50.0,"
					+ "\"user\":"
					+ "{"
						+ "\"id\":1,"
						+ "\"firstName\":null,"
						+ "\"lastName\":null,"
						+ "\"email\":null"
					+ "},"
					+ "\"product\":"
					+ "{"
						+ "\"id\":1,"
						+ "\"name\":null,"
						+ "\"description\":null,"
						+ "\"price\":null,"
						+ "\"image\":null,"
						+ "\"category\":null,"
						+ "\"inventory\":0"
					+ "}"
				+ "}";


		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/cartitem/1")
				.accept(MediaType.APPLICATION_JSON).content(mockCartItemJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		//Assert that the return status is 204 No Content
		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
		assertTrue(true);

	}

}
