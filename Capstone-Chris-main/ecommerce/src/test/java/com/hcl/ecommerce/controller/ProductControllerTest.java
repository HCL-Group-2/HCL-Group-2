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
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	Jackson2ObjectMapperBuilder mapperBuilder;
	
	@MockBean
	ProductService productService;
	
	@InjectMocks
	ProductController productController;
	
	@Test
	public void testAddProduct() throws Exception {

		String mockProductJson = 
				"{"
					+ "\"name\":\"Test Product\","
					+ "\"description\":\"A test product.\","
					+ "\"price\":50.0,"
					+ "\"image\":\"Test Image\","
					+ "\"category\":\"Test Category\","
					+ "\"inventory\":300"
				+ "}";

		ObjectMapper mapper = mapperBuilder.build();
		Product mockProduct = mapper.readValue(mockProductJson, Product.class);

		Mockito.when(productService.addProduct(any(Product.class))).thenReturn(mockProduct);

		//Create a post request with an accept header for application\json
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/product/")
				.accept(MediaType.APPLICATION_JSON).content(mockProductJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		//Assert that the return status is CREATED
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		assertTrue(true);

	}

	@Test
	public void testGetProductById() throws Exception {

		String mockProductJson = 
				"{"
					+ "\"id\":1,"
					+ "\"name\":\"Test Product\","
					+ "\"description\":\"A test product.\","
					+ "\"price\":50.0,"
					+ "\"image\":\"Test Image\","
					+ "\"category\":\"Test Category\","
					+ "\"inventory\":300"
				+ "}";

		ObjectMapper mapper = mapperBuilder.build();
		Product mockProduct = mapper.readValue(mockProductJson, Product.class);

        Mockito.when(productService.getProductById(1)).thenReturn(mockProduct);

		//Create a request
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/product/1")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected =
				"{"
					+ "\"id\":1,"
					+ "\"name\":\"Test Product\","
					+ "\"description\":\"A test product.\","
					+ "\"price\":50.0,"
					+ "\"image\":\"Test Image\","
					+ "\"category\":\"Test Category\","
					+ "\"inventory\":300"
				+ "}";

		//Assert that response is what was expected
		assertEquals(expected, result.getResponse().getContentAsString());
		assertTrue(true);

	}

	@Test
	public void testUpdateProduct() throws Exception {

		String mockProductJson = 
				"{"
					+ "\"id\":1,"
					+ "\"name\":\"Test Product Updated\","
					+ "\"description\":\"An updated test product.\","
					+ "\"price\":50.0,"
					+ "\"image\":\"Test Image\","
					+ "\"category\":\"Test Category\","
					+ "\"inventory\":300"
				+ "}";

		ObjectMapper mapper = mapperBuilder.build();
		Product mockProduct = mapper.readValue(mockProductJson, Product.class);

		Mockito.when(productService.updateProduct(any(Product.class))).thenReturn(mockProduct);

		//Create a put request with an accept header for application\json
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/product/")
				.accept(MediaType.APPLICATION_JSON).content(mockProductJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = 
				"{"
					+ "\"id\":1,"
					+ "\"name\":\"Test Product Updated\","
					+ "\"description\":\"An updated test product.\","
					+ "\"price\":50.0,"
					+ "\"image\":\"Test Image\","
					+ "\"category\":\"Test Category\","
					+ "\"inventory\":300"
				+ "}";

		//Assert that response is what was expected
		assertEquals(expected, result.getResponse().getContentAsString());
		assertTrue(true);

	}

	@Test
	public void testDeleteProduct() throws Exception {

		String mockProductJson = 
				"{"
					+ "\"id\":1,"
					+ "\"name\":\"Test Product Updated\","
					+ "\"description\":\"An updated test product.\","
					+ "\"price\":50.0,"
					+ "\"image\":\"Test Image\","
					+ "\"category\":\"Test Category\","
					+ "\"inventory\":300"
				+ "}";


		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/product/1")
				.accept(MediaType.APPLICATION_JSON).content(mockProductJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		//Assert that the return status is 204 No Content
		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
		assertTrue(true);

	}

}
