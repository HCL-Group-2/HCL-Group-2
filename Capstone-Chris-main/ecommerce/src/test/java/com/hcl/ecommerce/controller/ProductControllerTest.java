package com.hcl.ecommerce.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.util.List;

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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.service.ProductService;
import com.hcl.ecommerce.exception.AddEntityException;

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
		
		//test that it handles the exception thrown when inserting a duplicate product
		Mockito.when(productService.addProduct(any(Product.class))).thenThrow(AddEntityException.class);
		
		result = mockMvc.perform(requestBuilder).andReturn();
		response = result.getResponse();
		assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
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
	}
	
	@Test
	public void testGetAllProducts() throws Exception{
		String mockProductListJson = 
				"["
					+ "{"
						+ "\"id\":1,"
						+ "\"name\":\"Test Product one\","
						+ "\"description\":\"An updated test product1.\","
						+ "\"price\":50.0,"
						+ "\"image\":\"Test Image1\","
						+ "\"category\":\"Test Category1\","
						+ "\"inventory\":300"
					+ "},"
					+ "{"
						+ "\"id\":2,"
						+ "\"name\":\"Test Product two\","
						+ "\"description\":\"An updated test product2.\","
						+ "\"price\":75.0,"
						+ "\"image\":\"Test Image2\","
						+ "\"category\":\"Test Category2\","
						+ "\"inventory\":645"
					+ "},"
					+ "{"
						+ "\"id\":3,"
						+ "\"name\":\"Test Product three\","
						+ "\"description\":\"An updated test product3.\","
						+ "\"price\":21.86,"
						+ "\"image\":\"Test Image3\","
						+ "\"category\":\"Test Category3\","
						+ "\"inventory\":451"
					+ "}"
				+ "]";
		
		ObjectMapper mapper = mapperBuilder.build();
		List<Product> mockProductList = mapper.readValue(mockProductListJson, new TypeReference<List<Product>>() {});
		
		Mockito.when(productService.getAllProducts()).thenReturn(mockProductList);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/products").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(mockProductListJson, result.getResponse().getContentAsString());
	}
	
	@Test
	public void testGetAllProductsByName() throws Exception{
		String mockProductListJson = 
				"["
					+ "{"
						+ "\"id\":1,"
						+ "\"name\":\"Test Product one\","
						+ "\"description\":\"An updated test product1.\","
						+ "\"price\":50.0,"
						+ "\"image\":\"Test Image1\","
						+ "\"category\":\"Test Category1\","
						+ "\"inventory\":300"
					+ "},"
					+ "{"
						+ "\"id\":2,"
						+ "\"name\":\"Test Product two\","
						+ "\"description\":\"An updated test product2.\","
						+ "\"price\":75.0,"
						+ "\"image\":\"Test Image2\","
						+ "\"category\":\"Test Category2\","
						+ "\"inventory\":645"
					+ "},"
					+ "{"
						+ "\"id\":3,"
						+ "\"name\":\"Test Product three\","
						+ "\"description\":\"An updated test product3.\","
						+ "\"price\":21.86,"
						+ "\"image\":\"Test Image3\","
						+ "\"category\":\"Test Category3\","
						+ "\"inventory\":451"
					+ "}"
				+ "]";
		
		ObjectMapper mapper = mapperBuilder.build();
		List<Product> mockProductList = mapper.readValue(mockProductListJson, new TypeReference<List<Product>>() {});
		
		String requestParam = "test";
		
		Mockito.when(productService.getAllProductsByName(requestParam)).thenReturn(mockProductList);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/productsbyname?name=" + requestParam).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(mockProductListJson, result.getResponse().getContentAsString());
	}
	
	@Test
	public void testGetAllProductsByCategory() throws Exception{
		String mockProductListJson = 
				"["
					+ "{"
						+ "\"id\":1,"
						+ "\"name\":\"Test Product one\","
						+ "\"description\":\"An updated test product1.\","
						+ "\"price\":50.0,"
						+ "\"image\":\"Test Image1\","
						+ "\"category\":\"Test Category1\","
						+ "\"inventory\":300"
					+ "},"
					+ "{"
						+ "\"id\":2,"
						+ "\"name\":\"Test Product two\","
						+ "\"description\":\"An updated test product2.\","
						+ "\"price\":75.0,"
						+ "\"image\":\"Test Image2\","
						+ "\"category\":\"Test Category2\","
						+ "\"inventory\":645"
					+ "},"
					+ "{"
						+ "\"id\":3,"
						+ "\"name\":\"Test Product three\","
						+ "\"description\":\"An updated test product3.\","
						+ "\"price\":21.86,"
						+ "\"image\":\"Test Image3\","
						+ "\"category\":\"Test Category3\","
						+ "\"inventory\":451"
					+ "}"
				+ "]";
		
		ObjectMapper mapper = mapperBuilder.build();
		List<Product> mockProductList = mapper.readValue(mockProductListJson, new TypeReference<List<Product>>() {});
		
		String requestParam = "test";
		
		Mockito.when(productService.getAllProductsByCategory(requestParam)).thenReturn(mockProductList);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/productsbycategory?category=" + requestParam).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(mockProductListJson, result.getResponse().getContentAsString());
	}
}
