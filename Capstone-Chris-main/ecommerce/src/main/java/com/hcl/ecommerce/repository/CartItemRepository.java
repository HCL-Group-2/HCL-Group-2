package com.hcl.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.ecommerce.entity.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
	
//	Optional<CartItem> findByUserIdAndProductId(Integer userId, Integer productId);

	@Query("select i from CartItem i")
	List<CartItem> getAllCartItems();

}
