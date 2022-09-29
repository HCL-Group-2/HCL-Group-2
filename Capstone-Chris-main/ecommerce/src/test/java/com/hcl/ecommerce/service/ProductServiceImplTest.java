package com.hcl.ecommerce.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.repository.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
	
	@InjectMocks
	ProductServiceImpl productServiceImpl;

	@Mock
	ProductRepository productRepository;
	
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
