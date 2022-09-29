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
		
		assertTrue(true);
		
	}
	
	@Test
	public void testGetProductById() throws Exception {
		
		assertTrue(true);
		
	}
	
	@Test
	public void testUpdateProduct() throws Exception {
		
		assertTrue(true);
		
	}
	
	@Test
	public void testDeleteProduct() throws Exception {
		
		assertTrue(true);
		
	}

}
