package com.hcl.ecommerce.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerTest {
	
	@InjectMocks
	ProductController productController;
	
	@Mock
	ProductService productService;
	
	@Test
	public void testAddProduct() throws Exception {
		
		Product mockProduct = new Product();
		mockProduct.setId(1);
		mockProduct.setName("Test Product");
		mockProduct.setDescription("A test product.");
		mockProduct.setPrice(50.0);
		mockProduct.setImage("Test Image");
		mockProduct.setCategory("Test Category");
		mockProduct.setInventory(300);
		
		Mockito.when(productService.addProduct(any(Product.class))).thenReturn(mockProduct);
		
		ResponseEntity<Product> response = productController.addProduct(mockProduct);
		
		Product product = response.getBody();
		
		assertEquals(HttpStatus.CREATED.value(), response.getStatusCodeValue());
		
		assertEquals("Test Product", product.getName());
		assertEquals("A test product.", product.getDescription());
		assertEquals(50.0, product.getPrice(), 0.001);
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
		mockProduct.setPrice(50.0);
		mockProduct.setImage("Test Image");
		mockProduct.setCategory("Test Category");
		mockProduct.setInventory(300);
		
		Mockito.when(productService.getProductById(1)).thenReturn(mockProduct);
		
		ResponseEntity<Product> response = productController.getProductById(1);
		
		Product product = response.getBody();
		
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		
		assertEquals("Test Product", product.getName());
		assertEquals("A test product.", product.getDescription());
		assertEquals(50.0, product.getPrice(), 0.001);
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
		mockProduct.setPrice(50.0);
		mockProduct.setImage("Test Image");
		mockProduct.setCategory("Test Category");
		mockProduct.setInventory(300);
		
		Mockito.when(productService.updateProduct(any(Product.class))).thenReturn(mockProduct);
		
		ResponseEntity<Product> response = productController.updateProduct(mockProduct);
		
		Product product = response.getBody();
		
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		
		assertEquals("Test Product", product.getName());
		assertEquals("A test product.", product.getDescription());
		assertEquals(50.0, product.getPrice(), 0.001);
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
		mockProduct.setPrice(50.0);
		mockProduct.setImage("Test Image");
		mockProduct.setCategory("Test Category");
		mockProduct.setInventory(300);
		
		Mockito.when(productService.deleteProduct(1)).thenReturn("Success");
		
		ResponseEntity<String> response = productController.deleteProduct(1);
		
		String str = response.getBody();
		
		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatusCodeValue());
		
		assertEquals("Success", str);
		
	}

}
