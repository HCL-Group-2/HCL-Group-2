package com.hcl.ecommerce.controller;

import java.util.ArrayList;
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

import com.hcl.ecommerce.dto.ProductDto;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.exception.AddEntityException;
import com.hcl.ecommerce.service.ProductService;
@CrossOrigin(origins="https://ostrichmart.azurewebsites.net/")


@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/product")
	public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) {
		Product product = null;
		try {
			product = productService.addProduct(new Product(productDto));
		} catch (AddEntityException e) {
			return new ResponseEntity<>((ProductDto) null, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(product.toDto(), HttpStatus.CREATED);
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Integer id) {
		Product product = productService.getProductById(id);
		return new ResponseEntity<>(product.toDto(), HttpStatus.OK);
	}
	
	@PutMapping("/product")
	public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) {
		Product product = productService.updateProduct(new Product(productDto));
		return new ResponseEntity<>(product.toDto(), HttpStatus.OK);
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("id") Integer id) {
		productService.deleteProduct(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<ProductDto>> getAllProducts() {
		List<Product> list = productService.getAllProducts();
		List<ProductDto> dtoList = new ArrayList<>();
		for(Product p : list) {
			dtoList.add(p.toDto());
		}
		return new ResponseEntity<>(dtoList, HttpStatus.OK);
	}
	
	@GetMapping("/productsbyname")
	public ResponseEntity<List<ProductDto>> getAllProductsByName(@RequestParam String name) {
		List<Product> list = productService.getAllProductsByName(name);
		List<ProductDto> dtoList = new ArrayList<>();
		for(Product p : list) {
			dtoList.add(p.toDto());
		}
		return new ResponseEntity<>(dtoList, HttpStatus.OK);
	}
	
	@GetMapping("/productsbycategory")
	public ResponseEntity<List<ProductDto>> getAllProductsByCategory(@RequestParam String category) {
		List<Product> list = productService.getAllProductsByCategory(category);
		List<ProductDto> dtoList = new ArrayList<>();
		for(Product p : list) {
			dtoList.add(p.toDto());
		}
		return new ResponseEntity<>(dtoList, HttpStatus.OK);
	}
	
}
