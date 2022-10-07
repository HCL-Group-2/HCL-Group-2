package com.hcl.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.ecommerce.entity.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	@Query("select o from Order o order by o.orderDate desc")
	List<Order> getAllOrders();

}
