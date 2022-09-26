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
		
		Product mockProduct = new Product();
		mockProduct.setId(1);
		mockProduct.setName("Test Product");
		mockProduct.setDescription("A test product.");
		mockProduct.setPrice(new BigDecimal(50.0));
		mockProduct.setImage("Test Image");
		mockProduct.setCategory("Test Category");
		mockProduct.setInventory(300);
		
		Mockito.when(productRepository.findByName(mockProduct.getName())).thenReturn(null);
		
		Mockito.when(productRepository.save(any(Product.class))).thenReturn(mockProduct);
		
		Product product = productServiceImpl.addProduct(mockProduct);
		
		assertNotNull(product);
		
		assertEquals("Test Product", product.getName());
		assertEquals("A test product.", product.getDescription());
		//assertEquals(50.0, product.getPrice(), 0.001);
		assertTrue(product.getPrice().equals(new BigDecimal(50.0)));
		assertEquals("Test Image", product.getImage());
		assertEquals("Test Category", product.getCategory());
		assertEquals(300, product.getInventory());
		
	}
	
	@Test
	public void testGetProductById() throws Exception {
		
		Product mockProduct = new Product();
		mockProduct.setId(1);
		mockProduct.setName("Test Product");
		mockProduct.setDescription("A test product.");
		mockProduct.setPrice(new BigDecimal(50.0));
		mockProduct.setImage("Test Image");
		mockProduct.setCategory("Test Category");
		mockProduct.setInventory(300);
		
		Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(mockProduct));
		
		Product product = productServiceImpl.getProductById(1);
		
		assertNotNull(product);
		
		assertEquals("Test Product", product.getName());
		assertEquals("A test product.", product.getDescription());
		//assertEquals(50.0, product.getPrice(), 0.001);
		assertTrue(product.getPrice().equals(new BigDecimal(50.0)));
		assertEquals("Test Image", product.getImage());
		assertEquals("Test Category", product.getCategory());
		assertEquals(300, product.getInventory());
		
	}
	
	@Test
	public void testUpdateProduct() throws Exception {
		
		Product mockProduct = new Product();
		mockProduct.setId(1);
		mockProduct.setName("Test Product");
		mockProduct.setDescription("A test product.");
		mockProduct.setPrice(new BigDecimal(50.0));
		mockProduct.setImage("Test Image");
		mockProduct.setCategory("Test Category");
		mockProduct.setInventory(300);
		
		Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(mockProduct));
		
		Mockito.when(productRepository.save(any(Product.class))).thenReturn(mockProduct);
		
		Product product = productServiceImpl.updateProduct(mockProduct);
		
		assertNotNull(product);
		
		assertEquals("Test Product", product.getName());
		assertEquals("A test product.", product.getDescription());
		//assertEquals(50.0, product.getPrice(), 0.001);
		assertTrue(product.getPrice().equals(new BigDecimal(50.0)));
		assertEquals("Test Image", product.getImage());
		assertEquals("Test Category", product.getCategory());
		assertEquals(300, product.getInventory());
		
	}
	
	@Test
	public void testDeleteProduct() throws Exception {
		
		Product mockProduct = new Product();
		mockProduct.setId(1);
		mockProduct.setName("Test Product");
		mockProduct.setDescription("A test product.");
		mockProduct.setPrice(new BigDecimal(50.0));
		mockProduct.setImage("Test Image");
		mockProduct.setCategory("Test Category");
		mockProduct.setInventory(300);
		
		productServiceImpl.deleteProduct(1);
		
		Mockito.verify(productRepository, times(1)).deleteById(1);
		
	}

}
