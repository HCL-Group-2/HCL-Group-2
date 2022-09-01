package com.hcl.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.ecommerce.entity.OrderItem;
import com.hcl.ecommerce.repository.OrderItemRepository;

@Service
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Override
	public synchronized boolean addOrderItem(OrderItem orderItem) {
		orderItemRepository.save(orderItem);
		return true;
	}

	@Override
	public OrderItem getOrderItemById(Integer orderItemId) {
		Optional<OrderItem> orderItem = orderItemRepository.findById(orderItemId);
		if (orderItem.isPresent())
			return orderItem.get();
		return null;
	}
	
	@Override
	public void updateOrderItem(OrderItem orderItem) {
		OrderItem item = getOrderItemById(orderItem.getId());
		item.setQuantity(orderItem.getQuantity());
		orderItemRepository.save(item);
	}

	@Override
	public void deleteOrderItem(Integer orderItemId) {
		orderItemRepository.deleteById(orderItemId);
	}
	
}
