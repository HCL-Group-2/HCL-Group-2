package com.hcl.ecommerce.service;

import com.hcl.ecommerce.entity.Order;

public interface OrderService {
	
	boolean addOrder(Order order);

	Order getOrderById(Integer orderId);

	void updateOrder(Order order);

	void deleteOrder(Integer orderId);

}
