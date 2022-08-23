package com.hcl.ecommerce.service;

import java.util.List;

import com.hcl.ecommerce.entity.Product;

public interface ProductService {

	boolean addProduct(Product product);
	
	Product getProductById(Integer productId);
	
	void updateProduct(Product product);
	
	void deleteProduct(Integer productId);
	
	List<Product> getAllProducts();

}
