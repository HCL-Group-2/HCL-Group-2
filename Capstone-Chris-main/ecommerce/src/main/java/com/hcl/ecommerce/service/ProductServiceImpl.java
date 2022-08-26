package com.hcl.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.dto.ProductDto;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository productRepository;

//	@Override
//	public synchronized boolean addProduct(Product product) {
//		if (productRepository.findByName(product.getName()) != null) {
//			return false;
//		} else {
//			productRepository.save(product);
//			return true;
//		}
//	}
	
	@Override
	public synchronized boolean addProduct(ProductDto productDto) {
		if (productRepository.findByName(productDto.getName()) != null) {
			return false;
		} else {
			Product product = new Product();
			BeanUtils.copyProperties(productDto, product);
			productRepository.save(product);
			productDto.setId(product.getId());
			return true;
		}
	}
	
	@Override
	public Product getProductById(Integer productId) {
		Optional<Product> product = productRepository.findById(productId);
		if (product.isPresent()) return product.get();
		return null;
	}
	
	@Override
	public void updateProduct(Product product) {
		Product prod = getProductById(product.getId());
		prod.setName(product.getName());
		prod.setDescription(product.getDescription());
		prod.setPrice(product.getPrice());
		prod.setImage(product.getImage());
		prod.setCategory(product.getCategory());
		prod.setInventory(product.getInventory());
		productRepository.save(prod);
	}
	
	@Override
	public void deleteProduct(Integer productId) {
		productRepository.deleteById(productId);
	}
	
	@Override
	public List<Product> getAllProducts() {
		return productRepository.getAllProducts();
	}

}
