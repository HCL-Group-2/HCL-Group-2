package com.hcl.ecommerce.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.User;
import com.hcl.ecommerce.exception.AddEntityException;
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
		
		Product mockProduct = new Product("phone", "a phone", new BigDecimal(999.0), "image url", "phone", 300);
		
		when(productRepository.findByName(mockProduct.getName())).thenReturn(null);
		
		when(productRepository.save(any(Product.class))).thenReturn(mockProduct);
		
		Product product = productServiceImpl.addProduct(mockProduct);
		
		verify(productRepository).save(mockProduct);
		
//		assertNotNull(product);
//		
//		assertEquals("phone", product.getName());
//		assertEquals("a phone", product.getDescription());
//		assertEquals(new BigDecimal(999.0), product.getPrice());
//		assertEquals("image url", product.getImage());
//		assertEquals("phone", product.getCategory());
//		assertEquals(300, product.getInventory());
		
	}
	
	@Test(expected = AddEntityException.class)
	public void testAddProductProductAlreadyExist() throws Exception {
		
		Product mockProduct = new Product("phone", "a phone", new BigDecimal(999.0), "image url", "phone", 300);
		
		when(productRepository.findByName(mockProduct.getName())).thenReturn(mockProduct);

		productServiceImpl.addProduct(mockProduct);
		
	}
	
	@Test
	public void testGetProductById() throws Exception {
		
		Product mockProduct = new Product("phone", "a phone", new BigDecimal(999.0), "image url", "phone", 300);
		
		when(productRepository.findById(1)).thenReturn(Optional.of(mockProduct));
		
		Product product = productServiceImpl.getProductById(1);
		
		verify(productRepository).findById(1);
		
	}
	
	@Test
	public void testUpdateProduct() throws Exception {
		
		Product mockProduct = new Product(1, "phone", "a phone", new BigDecimal(999.0), "image url", "phone", 300);
		
		when(productRepository.findById(1)).thenReturn(Optional.of(mockProduct));
		
		when(productRepository.save(any(Product.class))).thenReturn(mockProduct);
		
		Product product = productServiceImpl.updateProduct(mockProduct);
		
		verify(productRepository).save(mockProduct);
		
	}
	
	@Test
	public void testDeleteProduct() throws Exception {
		
		productServiceImpl.deleteProduct(1);
		
		verify(productRepository).deleteById(1);
		
	}

}
