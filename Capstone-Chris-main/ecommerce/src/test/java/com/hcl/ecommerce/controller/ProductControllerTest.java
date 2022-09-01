package com.hcl.ecommerce.controller;

import static org.junit.Assert.assertEquals;

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
	
//	@BeforeEach
//	public void setupMocks() {
//		
//	}
	
	@Test
	public void testAddProduct() throws Exception {
		
		Product product = new Product();
		product.setName("Test Product");
		product.setDescription("A test product.");
		product.setPrice(50.0);
		product.setImage("Test Image");
		product.setCategory("Test Category");
		product.setInventory(300);
		
		Mockito.when(productService.addProduct(product)).thenReturn(product);
		
		ResponseEntity<Product> prod = productController.addProduct(product);
		
		assertEquals(HttpStatus.CREATED.value(), prod.getStatusCodeValue());
		
		assertEquals(product, prod.getBody());
		
		
	}
	
	@Test
	public void testGetProductById() throws Exception {
		
		Product product = new Product();
		product.setId(1);
		product.setName("Test Product");
		product.setDescription("A test product.");
		product.setPrice(50.0);
		product.setImage("Test Image");
		product.setCategory("Test Category");
		product.setInventory(300);
		
		Mockito.when(productService.getProductById(1)).thenReturn(product);
		
		ResponseEntity<Product> prod = productController.getProductById(1);
		
		assertEquals(HttpStatus.OK.value(), prod.getStatusCodeValue());
		
		assertEquals(product, prod.getBody());
		
	}
	
	@Test
	public void testUpdateProduct() throws Exception {
		
		Product product = new Product();
		product.setId(1);
		product.setName("Test Product");
		product.setDescription("A test product.");
		product.setPrice(50.0);
		product.setImage("Test Image");
		product.setCategory("Test Category");
		product.setInventory(300);
		
		Mockito.when(productService.updateProduct(product)).thenReturn(product);
		
		ResponseEntity<Product> prod = productController.updateProduct(product);
		
		assertEquals(HttpStatus.OK.value(), prod.getStatusCodeValue());
		
		assertEquals(product, prod.getBody());
		
	}
	
	@Test
	public void testDeleteProduct() throws Exception {
		
		Product product = new Product();
		product.setId(1);
		product.setName("Test Product");
		product.setDescription("A test product.");
		product.setPrice(50.0);
		product.setImage("Test Image");
		product.setCategory("Test Category");
		product.setInventory(300);
		
//        Mockito.when(productService.deleteProduct(1));
		
		ResponseEntity<Void> prod = productController.deleteProduct(1);
		
		assertEquals(HttpStatus.NO_CONTENT.value(), prod.getStatusCodeValue());
		
		assertEquals(product, prod.getBody());
		
	}

}
