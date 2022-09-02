package com.hcl.ecommerce.service;

import com.hcl.ecommerce.entity.OrderItem;
import com.hcl.ecommerce.exception.AddEntityException;

public interface OrderItemService {
	
	OrderItem addOrderItem(OrderItem orderItem) throws AddEntityException;

	OrderItem getOrderItemById(Integer orderItemId);

	void updateOrderItem(OrderItem orderItem);

	void deleteOrderItem(Integer orderItemId);

}
