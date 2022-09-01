package com.hcl.ecommerce.service;

import com.hcl.ecommerce.entity.OrderItem;

public interface OrderItemService {
	
	boolean addOrderItem(OrderItem orderItem);

	OrderItem getOrderItemById(Integer orderItemId);

	void updateOrderItem(OrderItem orderItem);

	void deleteOrderItem(Integer orderItemId);

}
