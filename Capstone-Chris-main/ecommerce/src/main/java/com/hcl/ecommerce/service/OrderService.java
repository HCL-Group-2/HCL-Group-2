package com.hcl.ecommerce.service;

import java.util.List;

import com.hcl.ecommerce.dto.OrderDto;
import com.hcl.ecommerce.entity.Address;
import com.hcl.ecommerce.entity.CartItem;
import com.hcl.ecommerce.entity.CreditCard;
import com.hcl.ecommerce.entity.Order;
import com.hcl.ecommerce.entity.OrderItem;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.User;

public interface OrderService {
	
	boolean addOrder(Order order);

	Order getOrderById(Integer orderId);

	void updateOrder(Order order);

	void deleteOrder(Integer orderId);
	
	Product getProductById(Integer productId);

	List<CartItem> getAllCartItemsByUserId(Integer userId);

}
