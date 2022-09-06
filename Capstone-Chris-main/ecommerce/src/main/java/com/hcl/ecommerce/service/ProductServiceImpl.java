package com.hcl.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.exception.AddEntityException;
import com.hcl.ecommerce.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public synchronized Product addProduct(Product product) throws AddEntityException {
		if (productRepository.findByName(product.getName()) != null) {
			throw new AddEntityException(
					"A Product with the Name: " + product.getName() + " already exists in the database");
		}
		productRepository.save(product);
		return product;
	}

	@Override
	public Product getProductById(Integer productId) {
		Optional<Product> product = productRepository.findById(productId);
		if (product.isPresent())
			return product.get();
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

	@Override
	public List<Product> getAllProductsByName(String name) {
		return productRepository.findByNameContains(name);
	}

	@Override
	public List<Product> getAllProductsByCategory(String category) {
		return productRepository.findByCategoryContains(category);
	}

}
