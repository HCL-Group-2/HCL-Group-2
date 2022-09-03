package com.hcl.ecommerce.service;

import java.util.List;

import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.exception.AddEntityException;

public interface ProductService {
	
	Product addProduct(Product product) throws AddEntityException;
	
	Product getProductById(Integer productId);
	
	Product updateProduct(Product product);
	
	String deleteProduct(Integer productId);
	
	List<Product> getAllProducts();

	List<Product> getAllProductsByName(String name);
	
	List<Product> getAllProductsByCategory(String category);

}
