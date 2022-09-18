package com.hcl.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.exception.AddEntityException;
import com.hcl.ecommerce.service.ProductService;
@CrossOrigin(origins="https://ostrichmart.azurewebsites.net")
@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/product")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		try {
			product = productService.addProduct(product);
		} catch (AddEntityException e) {
			return new ResponseEntity<Product>(product, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id) {
		Product product = productService.getProductById(id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@PutMapping("/product")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		product = productService.updateProduct(product);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("id") Integer id) {
		productService.deleteProduct(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> list = productService.getAllProducts();
		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/productsbyname")
	public ResponseEntity<List<Product>> getAllProductsByName(@RequestParam String name) {
		List<Product> list = productService.getAllProductsByName(name);
		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/productsbycategory")
	public ResponseEntity<List<Product>> getAllProductsByCategory(@RequestParam String category) {
		List<Product> list = productService.getAllProductsByCategory(category);
		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}
	
}
