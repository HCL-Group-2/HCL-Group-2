package com.hcl.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.ecommerce.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findByName(String name);
	
	@Query("select p from Product p order by p.name")
	List<Product> getAllProducts();

	List<Product> findByNameContains(String name);

	List<Product> findByCategoryContains(String category);

}
